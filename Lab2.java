import java.util.*;

interface TwoDimensionalShape
{
	double getArea();
	void input();
}

class Circle implements TwoDimensionalShape
{
	double radius;
	public double getArea()
	{	
		return 3.1416 * radius * radius;
	}

	public void input()
	{
		Scanner in1 = new Scanner(System.in);

		System.out.println("Enter radius of circle");
		this.radius = in1.nextDouble();

		in1.close();
	}

	public String toString()
	{	
		String detail = "Radius  = " + radius;
		return detail;
	}

}

class Square implements TwoDimensionalShape
{
	double side;
	public double getArea()
	{
		return  side * side;
	}



	public void input()
	{
		Scanner in = new Scanner(System.in);

		System.out.println("Enter length of side of the Square");
		this.side = in.nextDouble();

		in.close();
	}

	public String toString()
	{	
		String detail = ("Length of side of the Square  = " + side);
		return detail;
	}

}


class Triangle implements TwoDimensionalShape
{
	double base;
	double height;

	public double getArea()
	{
		return  0.5 * base * height;
	}

	public void input()
	{
		Scanner in = new Scanner(System.in);

		System.out.println("Enter base of Triangle");
		this.base = in.nextDouble();

		System.out.println("Enter height of Triangle");
		this.height = in.nextDouble();

		in.close();
	}

	public String toString()
	{	
		String detail = ("Base = " + base + "	Height  = " + height);
		return detail;
	}


}

public class Lab2
{
	public static void main(String args[])
	{
		TwoDimensionalShape[] arr = {new Circle(), new Square(), new Triangle()};
		
	
		Square sq = new Square();
		sq.input();

		Circle c = new Circle();
		c.input();

		System.out.println(sq);
	
	}
}

