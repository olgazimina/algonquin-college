package cst8284.arrays;

public class ArrayLauncher {
	public static void main(String[] args) {
		display1DArrayOfPrimitives();
		display2DArrayOfPrimitives();
		display1DArrayOfObjects();
		display2DArrayOfObjects();
	}
	
	public static void main() {
		return;
	}

	
	public static void display1DArrayOfPrimitives() {
		System.out.println("Loading 1D array of primitives...");

		int[] oneDimensionalArray; // note that array object is null initially
		oneDimensionalArray = new int[] { 1, 2, 3 }; // assign new array object to variable
								  // and load with 1, 2, 3
		System.out.println("*** Finished loading 1D array of primitives***");
		
		System.out.println("1D array contents are ");
		for (int i=0; i<oneDimensionalArray.length; i++)
			System.out.print(i + "    ");
	    // TODO: Task 1: Insert a breakpoint in the line above, execute in debug 
		// until you reach that line, and then use 'Step Into' until the array is
		// fully loaded.  Note the integer value stored in the array during each loop by
		// monitoring the contents of the Variables view pane (usually found in the
		// top right corner when debug is running), and the draw the memory map of the 
		// fully-loaded array, according to the instructions provided in the
		// lab 2 document
	}
	
	
	public static void display2DArrayOfPrimitives() {
		System.out.println("\n\nLoading 2D array of primitives...");

		int[][] twoDimensionalArray = new int[][] { { 1, 2, 3 }, { 2, 4, 6 } };
		System.out.println("*** Finished loading 2D array of primitive values ***");
		
		// Output the values
		System.out.print("2D array contents are \n");
		for (int[] row : twoDimensionalArray) {
			for (int column : row)
				System.out.print(column + "    ");
			System.out.println();  // add space after output
		} // TODO: Task 2: Insert a breakpoint at the second (i.e. inner) for() loop
		// in the code above.  Run the code in Debug to that line, and then 'Step Over' 
		// each line until the array is fully loaded with integers, paying attention to 
		// the values stored in row and column in the Variable view pane.  Draw the 
		// memory map of the twoDimensionalArray of primitives according to the 
		// instructions provided in the lab 2 document
	}

	
	public static void display1DArrayOfObjects() {
		System.out.println("\nLoading 1D array of objects...");
		
		Student[] oneDimensionalArrayOfRefs = new Student[3];
		for (int studentNum = 0; studentNum < oneDimensionalArrayOfRefs.length; studentNum++) // gs is each element of the array
			oneDimensionalArrayOfRefs[studentNum] = new Student(studentNum + 1001);
		System.out.println("*** Finished loading 1D array of objects ***");
		// TODO: Task 3: Insert a breakpoint in the line above, execute in Debug to that
		// line, and draw the memory map of the oneDimensionalArrayOfRefs containing   
		// three objects, according to the instructions provided in the lab 2 document
		
		System.out.print("1D array contents are \n");
		for (Student s : oneDimensionalArrayOfRefs)
			System.out.print(s.getStudentNumber() + "   ");
	}

	
	public static void display2DArrayOfObjects() {
		System.out.println("\n\nLoading 2D array of objects...");
		Student[][] twoDimensionalArrayOfObjects = new Student[][] { 
			{ new Student("Robin", "Gander", 1010), new Student("Vijay", "Singh", 1020) },
		    { new Student("Hassan", "Ahmed", 1011), new Student("Howard", "Rosenblum", 1021) }, 
			{ new Student("Sheila", "Zhang", 1012), new Student("Chris", "Obotu", 1022) } 
		};
		System.out.println("*** Finished loading 2D array of objects ***");
		// TODO: Task 4: Insert a breakpoint in the line above, execute in Debug to 
		// that line, and draw the memory map of the 2DArrayOfObjects containing six
		// Student objects according to the instructions provided in the lab 2 document

		System.out.print("2D array contents are \n");
		for (Student[] row : twoDimensionalArrayOfObjects) {
			for (Student element : row) 
				System.out.print("\t" + element.getFirstName() + " " + element.getLastName() +
				"  Student#: " + element.getStudentNumber() + "   ");	
			System.out.println();  
		}

	}
}
