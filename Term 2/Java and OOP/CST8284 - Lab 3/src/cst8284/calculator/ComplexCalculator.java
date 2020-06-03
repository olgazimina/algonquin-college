package cst8284.calculator;

public class ComplexCalculator {
	
	private java.util.Scanner op = new java.util.Scanner(System.in);
	private Complex c;  // stores result of current calculation 
	
	public ComplexCalculator(Complex c1, Complex c2){
		
		System.out.println("Which math operation do you wish to perform?  Enter +, -, *, /");
				
		switch (op.nextLine().charAt(0)){
		   case '+':
		      setComplexResult(add(c1, c2));  
		      break;
		   case '-':
			  //TODO: Call the subtraction method here and use setComplexResult() to save it
		   case '*':
			  //TODO: Call the multiplication method here and use setComplexResult() to save it
		   case '/':
		      //TODO: Call the division method here and use setComplexResult() to save it
		   default:
			  System.out.println("Unknown operation requested");
		}		
	}
	
	public ComplexCalculator() {}  // Needed for Lab 4; do not change
	
	public Complex add(Complex c1, Complex c2){
		double real = c1.getReal() + c2.getReal();  // As per the Lab Appendix
		double imag = c1.getImag() + c2.getImag();
		return(new Complex(real, imag));
	}

   // TODO Uncomment the following block of code and write the code needed to perform the other three
   // math operations indicated, as outlined in the appendix.  The add() method above 
   // indicates the general format of the result, consistent with the equations in the Lab 3 Appendix,
   // which you'll need to consult in order to code subtract(), multiply() and divide().
	
/*	public Complex subtract(Complex c1, Complex c2){

	}
	
	public Complex multiply(Complex c1, Complex c2){

	}
	
	public Complex divide(Complex c1, Complex c2){		
	   //TODO: check for possible division by 0 and output an error message to the screen
	   //return a constructor with value 0 + 0i);
	}   */
	
    // If attempting Bonus C, comment out the above divide() method, which must use
	// the calculation given in the Lab 03 document--this must be included for marks--and
	// add a new divide() method here that employs the complex conjugate in the Complex
	// class, as described in the BONUS MARKS section of the Lab 3 document.
	
	public void setComplexResult(Complex c) {
		// TODO: save the value of c passed as a parameter to this.c, declared at the start
		// of this class
	}

	public Complex getComplexResult(){
	    //TODO: return the result of the calculation stored in the Complex c variable declared
		// at the start of this class.  
	}

	public String toString(){
		// TODO: return the String value of the Complex Result of the calculation, 
		// using c's toString() method, i.e. chain using the existing toString() method
	}
	
}
