package test.cst8284.calculator;

import static org.junit.Assert.*;

import org.junit.Test;

import cst8284.calculator.Complex;
import cst8284.calculator.ComplexCalculator;

public class TestComplex {
	
	@Test
	/**
	 * Test toString() Method
	 */
	public void testToString() {
		Complex c = new Complex(3, 2);
		assertTrue(c.toString().equals("3.0 + 2.0i"));
	}

	@Test
	/**
	 * Question # 2. Use assertEquals(double, double, double) to test if getReal()
	 * returns the correct value set using the Complex(String[]) constructor (1
	 * mark)
	 */
	public void testComplexGetReal() {
		// create delta
		Double delta = 0.00000001;
		// create the empty String[] array
		String[] cStr = new String[] {"2", "3i"};
	    // create new Complex Number
		Complex c = new Complex(cStr);

		// compare value "3.0" and "c.getReal()" with maximum difference by "delta"
		assertEquals(2.0, c.getReal(), delta);
	}

	@Test
	/**
	 * Question # 3. Use assertEquals(double, double, double) to test if setReal()
	 * correctly resets the value set using the Complex(int, int) constructor (1
	 * mark)
	 */
	public void testComplexSetReal() {
		// create delta
		Double delta = 0.000000001;

		// create new Complex Number with real and imag
		Complex complex = new Complex(3, 4);
		// now using setter for real - set new real
		complex.setReal(7);

		// compare value "7.0" and "complex.getReal()" with maximum difference by "delta"
		assertEquals(7.0, complex.getReal(), delta);
	}

	@Test
	/**
	 * Question # 4. Use the no-arg Complex() constructor, and then use the real and
	 * imaginary setters to set new integer values. Then use assertTrue(boolean) to
	 * test the validity of your newly-added equals(String) method (1 mark)
	 */
	public void testComplexBothIntSetters() {
		// create complex using no-arg constructor
		// initial values are "0+0i"
		Complex complex = new Complex();

		// now using setters for real and imaginary parts to set
		// new Complex of "3+4i"
		complex.setReal(3);
		complex.setImag(4);

		// now we need to compare newly created "complex" (3+4i) with the same
		// expression described by the string value
		assertTrue(complex.equals("3+4i"));
	}

	@Test
	/**
	 * Question # 5. Using assertTrue(boolean), test that two Complex numbers
	 * created using the Complex(String, String) constructor and the Complex(String,
	 * String) constructor give the correct result using the divide() method. Use
	 * equals(Complex) to compare the actual and expected result (1 mark)
	 */
	public void testComplexDivision() {
		// create ethalon value (result of division)
		Complex ethalon = new Complex("3+1i");

		// create 2 Complex Numbers to divide one to another
		Complex complex1 = new Complex("2", "4i");
		Complex complex2 = new Complex("1", "1i");

		// create complex Calculator
		ComplexCalculator complexCalculator = new ComplexCalculator();
		// using complex calculator - make division
		Complex complex3 = complexCalculator.divide(complex1, complex2);

		// now compare the result of the division with ethalon
		// using method "equals" of class Complex
		assertTrue(ethalon.equals(complex3));
	}

	@Test
	/**
	 * Question # 6. Using the Complex(String) constructor, test to see if your code
	 * is robust enough to handle extra spaces in the test String, e.g. '3 + 2i '
	 * with a varying number of spaces (Hint: use one or more for() loops) (3 marks)
	 */
	public void testComplexExtraSpaces() {
		assertTrue((new Complex("  3 +  2   i ")).toString().equals("3.0 + 2.0i"));
		
	}		
}


