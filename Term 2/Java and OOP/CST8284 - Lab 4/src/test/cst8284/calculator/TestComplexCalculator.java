package test.cst8284.calculator;

import static org.junit.Assert.*;

import org.junit.Test;

import cst8284.calculator.ComplexCalculator;

public class TestComplexCalculator extends ComplexCalculator {

	@Test
	/**
	 * Question # 1. Use assertNotNull() to test the ComplexCalculator() i.e. no-arg
	 * constructor (1 mark)
	 */
	public void testComplexCalculator() {
		ComplexCalculator cc = new ComplexCalculator();
		assertNotNull(cc);
	}
}
