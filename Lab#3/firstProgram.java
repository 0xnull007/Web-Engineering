import javax.swing.*;

public class firstProgram {
	public static void main(String[] args) {
		String shape = args[0];
		double[] dimensions = new double[args.length - 1];
		for (int i = 0; i < dimensions.length; i++) {
			dimensions[i] = Double.parseDouble(args[i + 1]);
		}

		double area = calculateArea(shape, dimensions);

		if (area != -1) {
			JOptionPane.showMessageDialog(null, "Area of " + shape + " = " + area);
		} else {
			JOptionPane.showMessageDialog(null, "Invalid Shape or Invalid Number of Arguments!");
		}
	}

	public static double calculateArea(String shape, double[] dimensions) {
		if (shape.equals("Square") && dimensions.length == 1) {
			return dimensions[0] * dimensions[0];
		} else if (shape.equals("Rectangle") && dimensions.length == 2) {
			return dimensions[0] * dimensions[1];
		} else if (shape.equals("Parallelogram") && dimensions.length == 2) {
			return dimensions[0] * dimensions[1];
		} else if (shape.equals("Trapezoid") && dimensions.length == 3) {
			return ((dimensions[0] + dimensions[1]) / 2) * dimensions[2];
		} else if (shape.equals("Triangle") && dimensions.length == 2) {
			return 0.5 * dimensions[0] * dimensions[1];
		} else if (shape.equals("Circle") && dimensions.length == 1) {
			return 3.1416 * dimensions[0] * dimensions[0];
		} else if (shape.equals("Ellipse") && dimensions.length == 2) {
			return 3.1416 * dimensions[0] * dimensions[1];
		} else {
			return -1;
		}
	}
}
