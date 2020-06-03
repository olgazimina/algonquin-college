package cst8284.shape;

public class Circle extends BasicShape {

	public Circle() {
		this(1.0);
	}

	public Circle(double width) {
		super(width);
	}

	public Circle(Circle circle) {
		super(circle.getWidth());
	}

	/**
	 * This method is inherited from BasicShape class and must be overridden
	 * because it is abstract. Implementation is different for Circle.
	 *
	 * @return Double area
	 */
	@Override
	public double getArea() {
		return Math.PI * (Math.pow(this.getWidth(), 2) / 4);
	}

	/**
	 * This method is inherited from BasicShape class and must be overridden
	 * because it is abstract. Implementation is different for Circle.
	 *
	 * @return Double perimeter
	 */
	@Override
	public double getPerimeter() {
		return Math.PI * this.getWidth();
	}

	@Override
	public String toString() {
		return "Circle extends" + super.toString();
	}

	/**
	 * This method is inherited from BasicShape class and must be overridden
	 * because it is abstract. Implementation is different for Circle.
	 * <p>
	 * At first we have to compare type using [instanceof] operator. if [obj] is not of
	 * type [Circle] then we cannot compare objects - they're already different.
	 * If [obj] is of type [Circle] then we need to compare width of [this] and [obj]
	 * If it is equal - we can say that the [obj] then equals to [this]
	 *
	 * @return true if equal, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		return 
				obj instanceof Circle 
				&& this.getWidth() == ((Circle) obj).getWidth();
	}
}
