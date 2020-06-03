package cst8284.genericTable;

import cst8284.shape.BasicShape;
import cst8284.solidObject.Circle;
import cst8284.solidObject.Square;
import cst8284.solidObject.Triangle;

import java.util.Arrays;
import java.util.List;

public class GenericTableLauncher {
	public static void main(String[] args) {
		List<Integer> ints    = Arrays.asList(1, 2, 3);
		List<String>  strings = Arrays.asList("Larry", "Moe", "Curly");
		List<BasicShape> geo = Arrays.asList(new Circle(5.64189583547756),
			new Square(4.0),
			new Triangle(6.078685485127),
			new Square(5.0));

		Table.outputTable(ints);
		Table.outputTable(strings);
		Table.outputTable(geo);
	}
}
