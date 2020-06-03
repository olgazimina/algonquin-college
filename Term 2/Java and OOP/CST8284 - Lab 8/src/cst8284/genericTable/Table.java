package cst8284.genericTable;

import java.util.List;

public class Table {
	public static <E extends Comparable<E>> void outputTable(List<E> arList) {

		// Print out column header
		System.out.println("Compare the objects' volumes.  Are they equal?");
		for (E columnHeader : arList) { System.out.print("\t\t" + columnHeader); }

		// Print out each row,starting with the name of the object
		for (E solidObjRow : arList) {
			System.out.println(); // Next line
			System.out.print(solidObjRow);
			for (E solidObjColumn : arList) { System.out.print("\t\t" + compareResults(solidObjColumn, solidObjRow)); }
		}
		System.out.println("\n");
	}

	public static <E extends Comparable<E>> String compareResults(E o1, E o2) {
		return o1.compareTo(o2) < 0 ? "<" : o1.compareTo(o2) > 0 ? ">" : "=";
	}
}
