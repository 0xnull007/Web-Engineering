import java.util.ArrayList;

class DuplicateCustomerException extends Exception {
    private String id;

    DuplicateCustomerException(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Exception: Customer with id " + id + " already exists!";
    }
}

class InvalidAgeException extends Exception {
    private int age;

    InvalidAgeException(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Exception: Invalid age! Age must be between 18 - 65. Age: " + age;
    }
}

class LowBalanceException extends Exception {
    private double balance;

    LowBalanceException(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Exception: Invalid balance! Balance must be greater than 0. Balance: " + balance;
    }
}

class InvalidCustomerIdException extends Exception {
    private String id;

    InvalidCustomerIdException(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Exception: Invalid customer ID: " + id + ". No such customer found.";
    }
}

class Customer {
    private String name;
    private String id;
    private int age;
    private double balance;

    Customer(String name, String id, int age, double balance) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return name + " " + id + " " + age + " " + balance;
    }

    public String getId() {
        return id;
    }
}

class Bank {
    private ArrayList<Customer> customers;

    Bank() {
        customers = new ArrayList<Customer>();
    }

    public boolean customerExists(String id) {
        for (Customer cust : customers) {
            if (cust.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void searchCustomer(String id) throws InvalidCustomerIdException {
        boolean customerFound = false;
        for (Customer cust : customers) {
            if (cust.getId().equals(id)) {
                customerFound = true;
                System.out.println(cust);
            }
        }

        if (!customerFound) {
            throw new InvalidCustomerIdException(id);
        }
    }

    public void addCustomer(Customer cust) throws DuplicateCustomerException, InvalidAgeException, LowBalanceException {
        if (customerExists(cust.getId())) {
            throw new DuplicateCustomerException(cust.getId());
        }
        if (cust.getAge() < 18 || cust.getAge() > 65) {
            throw new InvalidAgeException(cust.getAge());
        }
        if (cust.getBalance() <= 0) {
            throw new LowBalanceException(cust.getBalance());
        }
        customers.add(cust);
        System.out.println("Customer added successfully.");
    }

    public void showCustomers() {
        for (Customer cust : customers) {
            System.out.println(cust);
        }
    }
}

public class Lab3 {
    public static void main(String[] args) {
        Customer c1 = new Customer("Abdul Qadir", "100", 20, 2000);
        Customer c2 = new Customer("Babar Ali", "1", 10, 1000);

        Bank bank = new Bank();

        try {
            bank.addCustomer(c1);
            bank.addCustomer(c2);
        } catch (DuplicateCustomerException | InvalidAgeException | LowBalanceException e) {
            System.out.println(e);
       }
}

