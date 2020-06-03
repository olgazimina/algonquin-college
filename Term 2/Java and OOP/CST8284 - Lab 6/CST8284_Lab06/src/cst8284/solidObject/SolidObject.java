package cst8284.solidObject;

import cst8284.shape.BasicShape;

/**
 * This class is generic and can accept as a parameter to its
 * constructor any other objects which inherit BasicShape class. 
 * @author olgazimina
 *
 * @param <T>
 */
public class SolidObject<T extends BasicShape> {
	private T      shape;
	private double depth;

	public double getDepth() {
		return depth;
	}

	public void setDepth(double depth) {
		this.depth = depth;
	}

	public T getBasicShape() {
		return shape;
	}

	public void setBasicShape(T shape) {
		this.shape = shape;
	}

	/**
	 * 1-arg constructor should use the width of T as its depth.
	 *
	 * @param shape BasicShape
	 */
	protected SolidObject(T shape) {
		this(shape, shape.getWidth());
	}

	/**
	 * 2 args constructor
	 *
	 * @param shape
	 * @param depth
	 */
	protected SolidObject(T shape, double depth) {
		this.setBasicShape(shape);
		this.setDepth(depth);
	}

	/**
	 * the volume is just the cross-sectional area of the 2D shape, multiplied by its depth
	 *
	 * @return Double value
	 */
	public double getVolume() {
		return this.getBasicShape().getArea() * this.getDepth();
	}

	public double getSurfaceArea() {
		return this.getBasicShape().getPerimeter() * this.getDepth() + 2 * this.getBasicShape().getArea();
	}

	// the toString() method is provided for you -- do not remove
	public String toString() {
		String solidTypeEquivalent = "unknown   ";
		switch (getBasicShape().getClass().getSimpleName()) {
		   case "Circle": solidTypeEquivalent = "cylinder "; break;
		   case "Square": solidTypeEquivalent = "block     "; break;
		   case "Triangle": solidTypeEquivalent = "prism    "; break;
		}
		return solidTypeEquivalent;
	}

}
