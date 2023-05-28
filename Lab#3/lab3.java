import java.util.*;

class duplicateCustomerException extends Exception
{
	private String id;
	duplicateCustomerException(String i)
	{
		this.id = i;
	}

	@Override
	public String toString()
	{	
		return "Exception:: Customer with id " + id + " already exits!";
	}
}

class invalidAgeException extends Exception 
{
	private int age;
	invalidAgeException (int a)
	{
		this.age = a;
	}

	@Override
	public String toString()
	{	
		return "Exception:: Age must be between 18 - 65. Age: " + age;
	}
}

class lowBalanceException extends Exception 
{
	private double balance;
	lowBalanceException (double b)
	{
		this.balance = b;
	}

	@Override
	public String toString()
	{	
		return "Exception:: Balance must be greater than 0";
	}
}


class invalidCustomerIDException extends Exception 
{
	private String id;
	invalidCustomerIDException (String id)
	{
		this.id = id;
	}

	@Override
	public String toString()
	{	
		return "Invalid ID:" + id + ". No Such customer found";
	}
}


class Customer
{
	String name, id;
	int age;
	double balance;

	Customer(String name, String id, int age, double balance)
	{
		this.name = name;
		this.id = id;
		this.age = age;
		this.balance  = balance;
	}

	@Override
	public String toString()
	{
		String detail = name + "  " +  id + "  " + age + "  " + balance;
		return detail;
	}
}

class Bank
{
	ArrayList<Customer> Customers;

	Bank()
	{
		Customers = new ArrayList<Customer>();
	}
	
	boolean CustomerExists(String id)
	{
		for(Customer cust : Customers)
			if(cust.id.equals(id))
				return true;
		return false;
	}

	void searchCustomer(String id)
	{
		try{
		
			for(Customer cust : Customers)
				if(cust.id.equals(id))
					throw new invalidCustomerIDException(id); 
		}
		catch(invalidCustomerIDException ex)
		{
			System.out.println(ex);
		}
	}

	void addCustomer(Customer cust)
	{
		try
		{
			if(CustomerExists(cust.id)) throw new duplicateCustomerException(cust.id);
			if(cust.age<18 || cust.age>65)	throw new invalidAgeException(cust.age);
			if(cust.balance <= 0)	throw new lowBalanceException(cust.balance);
			Customers.add(cust);
		}
	//	catch(duplicateCustomerException ex)
	//	{
	//		System.out.println(ex);
	//	}
	//	catch(invalidAgeException ex)
	//	{
	//		System.out.println(ex);
	//	}
	//	catch(lowBalanceException ex)
	//	{
	//		System.out.println(ex);
	//	}
		catch(duplicateCustomerException | lowBalanceException | invalidAgeException ex)
		{
			System.out.println(ex);
		}

	
	}

	void showCustomers()
	{
			for(Customer cust : Customers)
				System.out.println(cust);
	}
}

public class lab3
{
	public static void main(String[] args)
	{
		Customer c1 = new Customer("Abdul Qadir", "1", 20 , 200.30);

		Customer c2 = new Customer("Ali Hassan", "2", 20 , 0);

		Bank bank = new Bank();
		bank.addCustomer(c1);
		bank.addCustomer(c2);
		bank.showCustomers();

	}
}
