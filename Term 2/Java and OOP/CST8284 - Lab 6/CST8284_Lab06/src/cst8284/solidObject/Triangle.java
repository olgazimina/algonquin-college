package cst8284.solidObject;

import cst8284.shape.BasicShape;

public class Triangle extends BasicShape {
	public Triangle() {
		this(1.0);
	}

	public Triangle(double width) {
		super(width);
	}

	public Triangle(Triangle Triangle) {
		super(Triangle.getWidth());
	}

	@Override
	public double getArea() {
		return Math.sqrt(3.0) / 4.0 * Math.pow(this.getWidth(), 2);
	}

	@Override
	public double getPerimeter() {
		return 3 * this.getWidth();
	}

	@Override
	public String toString() {
		return "Triangle extends" + super.toString();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Triangle && this.getWidth() == ((Triangle) obj).getWidth();
	}
}
