import java.io.*;
import java.util.*;

class Employee {
	int id;
	String name;
	double salary;
	String rank;

	Employee(int i, String n, double s, String r) {
		id = i;
		name = n;
		salary = s;
		rank = r;
	}

	void print() {
		System.out.println(id + "  " + name + "  " + salary + "  " + rank);
	}
}

class EmployeeData {

	ArrayList<Employee> employees;

	EmployeeData() {
		employees = new ArrayList<Employee>();
		BufferedReader br = null;
		try {
			String s = "";
			br = new BufferedReader(new FileReader("EmployeeData.csv"));
			while ((s = br.readLine()) != null) {
				String[] tokens = s.split(",");
				int id = Integer.parseInt(tokens[0]);
				double sal = Double.parseDouble(tokens[2]);

				addObject(id, tokens[1], sal, tokens[3]);
			}
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}

	void addEmployee() {
		String name, rank;
		double salary;
		int id;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter id: ");
		id = sc.nextInt();

		int alreadyExits = searchObject(id);
		if (alreadyExits >= 0) {

			System.out.println("Employee with id " + id + " alraedy exits!");
			System.out.println("Try AGAIN with a different ID!");
			addEmployee();

			return; // to avoid calling addObject(), in case of invalidEmployeeException
		}

		sc.nextLine(); // to ignore '\n', already in inputStream
		System.out.print("Enter name: ");
		name = sc.nextLine();

		System.out.print("Enter salary: ");
		salary = sc.nextDouble();

		sc.nextLine();
		System.out.print("Enter rank: ");
		rank = sc.nextLine();

		addObject(id, name, salary, rank);
		System.out.println("Employee added successfully");
	}

	private void addObject(int i, String n, double s, String r) {
		employees.add(new Employee(i, n, s, r));
	}

	private int searchObject(int id) {
		int index = -1;
		for (int i = 0; i < employees.size(); i++)
			if (employees.get(i).id == id)
				return i;
		return index;
	}

	void removeEmployee() {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter id: ");
		int id = sc.nextInt();

		int index = searchObject(id);

		if (index == -1) {
			System.out.println("No Employee with id " + id + " found!");
			return;
		}

		employees.remove(index);
		System.out.println("Employee removed successfully");
	}

	void searchEmployee() {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter id: ");
		int id = sc.nextInt();

		int index = searchObject(id);

		if (index == -1) {
			System.out.println("No Employee with id " + id + " found!");
			return;
		}

		employees.get(index).print();
	}

	void writeToFile() {
		BufferedWriter bw = null;
		try {
			String s = "";
			bw = new BufferedWriter(new FileWriter("EmployeeData.csv"));
			for (Employee e : employees) {
				s = e.id + "," + e.name + "," + e.salary + "," + e.rank + "\n";
				bw.write(s);
			}
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				bw.close();
				System.out.println("File saved successfully!");
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	void printEmployees() {
		for (Employee e : employees) {
			e.print();
		}
	}
}

public class Driver {

	static void init() {
		EmployeeData employees = new EmployeeData();

		Scanner sc = new Scanner(System.in);
		int choice = 0;
		System.out.println("Enter 1 to ADD a new Employee.");
		System.out.println("Enter 2 to REMOVE an Employee.");
		System.out.println("Enter 3 to SEARCH an Employee.");
		System.out.println("Enter 4 to DISPLAY all Employee's details.");
		System.out.println("Enter 5 to SAVE.");
		System.out.println("Enter 6 to SAVE and EXIT.");
		System.out.println("Enter 7 to DISCARD current CHANGES and EXIT.");
		while (true) {
			System.out.print("\nYour choice :");
			try {
				choice = sc.nextInt();

			} catch (NumberFormatException e) {
				System.out.println("Invalid choice!");
				init();

				break;
			}

			switch (choice) {
				case 1:
					employees.addEmployee();
					break;
				case 2:
					employees.removeEmployee();
					break;
				case 3:
					employees.searchEmployee();
					break;
				case 4:
					employees.printEmployees();
					break;
				case 5:
					employees.writeToFile();
					break;
				case 6:
					employees.writeToFile();
				case 7:
					System.out.println("Exiting .......");
					System.exit(0);
			}
		}
	}

	public static void main(String[] args) {
		init();
	}
}
