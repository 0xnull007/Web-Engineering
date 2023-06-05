import java.sql.*;
import java.util.*;

class AddressBook{
	Connection con;
	Statement st;
	
	AddressBook()
	{
		try {

			String url = "jdbc:mysql://127.0.0.1/AddressBook";
    			con=DriverManager.getConnection(url,"root","root");
			//System.out.println("Connection Succeded.");
			st=con.createStatement();

		} catch(SQLException e) {
			System.out.println(e);
		}
	
    		
	}

	void addPerson() throws SQLException 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your name");
		String n = sc.nextLine();
		
		System.out.println("Enter your phone number");
		String p = sc.nextLine();
	
		System.out.println("Enter your city");
		String c = sc.nextLine();

		System.out.println("Enter your address");
		String add = sc.nextLine();

		String query= "insert into info(name,address,city,phone)values('"+ n +"','" + add + "','"+c+ "','" +p+ "')";
		//System.out.println(query);
		int rs = this.st.executeUpdate( query );
   		
     		if(rs > 0){
	    		System.out.println("Record inserted successfully.");
	  	} else{
    	 		System.out.println("Record could not inserted.");
         	}
	}

	void deletePerson() throws SQLException
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your name");
		String n = sc.nextLine();
		
		String query= "delete from info where name=" + "'"+ n + "'";
		System.out.println(query);
		int rs = this.st.executeUpdate( query );
   		
     		if(rs > 0){
	    		System.out.println("Record deleted successfully.");
	  	} else{
    	 		System.out.println("Record could not deleted.");
         	}	
	}
	
	void searchPerson() throws SQLException
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your name");
		String n = sc.nextLine();
		
		String query= "select * from info where name=" + "'"+ n + "'";
		System.out.println(query);
		 ResultSet rs = st.executeQuery(query);

        if (rs.next()) {
            String name = rs.getString("name");
            String phone = rs.getString("phone");
		String city = rs.getString("city");
		String add = rs.getString("address");

            System.out.println("Name: " + name + "\tcity: " + city + "\tphone: " + phone + "\taddress: " + add);

        } else {
            System.out.println("No record found");
        }
	
	}
}

public class Driver
{
	static void init() throws SQLException
	{	
		AddressBook obj = new AddressBook();

		Scanner sc = new Scanner(System.in);
		int choice = 0;

		System.out.println("Enter 1 to ADD a new Employee.");
		System.out.println("Enter 2 to REMOVE an Employee.");
		System.out.println("Enter 3 to SEARCH an Employee.");
		System.out.println("Enter 4 to Exit.");
		
		while (true) {
			System.out.print("\nYour choice :");
			try {
				choice = sc.nextInt();

			} catch (InputMismatchException e) {
				System.out.println("Invalid choice!\n");
				init();
			}

			switch (choice) {
				case 1:
					obj.addPerson();
					break;
				case 2:
					obj.deletePerson();
					break;
				case 3:
					obj.searchPerson();
					break;
				case 4:
					System.out.println("Exiting .......");
					System.exit(0);
				default:
					System.out.println("Invalid choice!");
			}
		}

	}

	public static void main(String[] args) throws Exception {
   
    		Class.forName("com.mysql.cj.jdbc.Driver");
		init();
    	}
}











