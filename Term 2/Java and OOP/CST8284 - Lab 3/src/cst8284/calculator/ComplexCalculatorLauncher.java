package cst8284.calculator;
import java.util.Scanner;

public class ComplexCalculatorLauncher {
	
   private static Scanner input = new Scanner(System.in);
   private static int choice = 0;
   private static ComplexCalculator calc;
   
   private static final int SINGLE_STRING = 1, DOUBLE_STRING = 2, TWO_INTS = 3;
   
   //Code execution starts here
   public static void main(String[] args) {
	   
      System.out.println("Welcome to the Complex Calculator\n");
      
      do {   //loop until 'Q' entered by user, below
    	 Complex c1 = ComplexCalculatorLauncher.getComplexNumber();  // read in the 1st complex number
    	 Complex c2 = ComplexCalculatorLauncher.getComplexNumber();  // read in the 2nd complex number
    	 if ((c1==null)|| (c2==null)) break;
    	 calc = new ComplexCalculator(c1, c2);                       // perform the calculation
         System.out.println("The result is: " + calc.toString());    // output the result
         System.out.println ("Do you wish to continue? Enter Q to quit or any other char to continue");
      } while (input.next().charAt(0)!='Q');
      System.out.println("Exiting Complex Calculator");
   }
   
   // Static method requests and returns a complex number from user
   private static Complex getComplexNumber(){
	   
   	   choice = ComplexCalculatorLauncher.displayTypeInputMenu();
   	   input.nextLine();  // Remove CRLF from input buffer
       System.out.print("Enter a complex number ");  // Optional TODO: If attempting Bonus B,
       					// write code that distinguishes 1st Complex number input from 2nd Complex 
       					// number input (one line ONLY).  See the Lab 3 document for details.
       switch(choice){
       
          case SINGLE_STRING:						  // Single string input
         	 System.out.println("as a single string, e.g. 4-3i");
        	 String strComplex = input.next();
    	     return(new Complex(strComplex));         // Call 1-String constructor
    	     
          case DOUBLE_STRING:						  // Two string input
         	 System.out.println("as two separate strings, with a space in between, e.g. 4 -3i");
        	 String strReal = input.next(), strImag = input.next();
    	     return(new Complex(strReal, strImag));   //Call 2-String constructor
    	     
          case TWO_INTS:							  // Two int input
         	 System.out.println("as two integers, where the 2nd int is the imaginary number, e.g. 4 3");
        	 int iReal = input.nextInt(), iImag = input.nextInt();
    	     return(new Complex(iReal, iImag));      //Call 2-integer constructor
    	     
          default:
        	 System.out.println("Bad input choice; exiting\n");
        	 return(null);    						//used to signal exit
       }
   }
   
   //Standard menu method; prompts user to enter input type for Complex constructor
   private static int displayTypeInputMenu(){
      System.out.printf("%s\n\t%s\n\t%s\n\t%s", 
    	 "Enter the format of the complex number to be entered:", 
  		 SINGLE_STRING + ". A single string",
         DOUBLE_STRING + ". Two strings",
         TWO_INTS + ". Two integer values\n");
      return(input.nextInt());
   }
   
}
