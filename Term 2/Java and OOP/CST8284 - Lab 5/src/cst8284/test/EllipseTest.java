package cst8284.test;

import cst8284.shape.Circle;
import cst8284.shape.Ellipse;

import static org.junit.Assert.*;

import org.junit.Test;

public class EllipseTest extends Circle {

	@Test
	public void comparePerimeters() {
		Circle  circle  = new Circle(10);
		Ellipse ellipse = new Ellipse(10, 10);

		assertEquals(circle.getPerimeter(), ellipse.getPerimeter(), 1e-12);
	}

	@Test
	public void compareAreas() {
		
		Circle  circle  = new Circle();
		Ellipse ellipse = new Ellipse();
		
		assertEquals(circle.getArea(), ellipse.getArea(), 1e-12);
	}
}
