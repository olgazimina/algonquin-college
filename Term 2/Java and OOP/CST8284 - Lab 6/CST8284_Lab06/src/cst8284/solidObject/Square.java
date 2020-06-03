package cst8284.solidObject;

import cst8284.shape.BasicShape;

public class Square extends BasicShape {
	public Square() {
		this(1.0);
	}

	public Square(double width) {
		super(width);
	}

	public Square(Square Square) {
		super(Square.getWidth());
	}

	@Override
	public double getArea() {
		return Math.pow(this.getWidth(), 2);
	}

	@Override
	public double getPerimeter() {
		return 4 * this.getWidth();
	}

	@Override
	public String toString() {
		return "Square extends" + super.toString();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Square && this.getWidth() == ((Square) obj).getWidth();
	}
}
