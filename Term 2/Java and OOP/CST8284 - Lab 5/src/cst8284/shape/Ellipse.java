package cst8284.shape;

public class Ellipse extends Circle {
	
	private double height;

	/**
	 * default constructor to set default height
	 */
	public Ellipse() {
		this(1.0);
	}

	/**
	 * parameterized constructor. specifies height only, because weight will be
	 * specified by default value of the superclass - Circle
	 * 
	 * @param height - new height
	 */
	public Ellipse(double height) {
		this.setHeight(height);
	}

	/**
	 * parameterized constructor. specifies both height and weight
	 * 
	 * @param width  - new weight
	 * @param height - new height
	 */
	public Ellipse(double width, double height) {
		super.setWidth(width);
		this.setHeight(height);
	}

	/**
	 * copy constructor - create another object of type Ellipse, based on parameters
	 * from other object, came as a parameter
	 * 
	 * @param ellipse - another object to be copied
	 */
	public Ellipse(Ellipse ellipse) {
		super.setWidth(ellipse.getWidth());
		this.setHeight(ellipse.getHeight());
	}

	/** 
	 * standard getter for height
	 * @return height as double
	 */
	public double getHeight() {
		return height;
	}

	/**
	 *  standard setter for height
	 * @param height as double
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	// overridden method from super class
	@Override
	public double getArea() {
		return (Math.PI / 4) * getWidth() * getHeight();
	}

	// overridden method from super class
	@Override
	public double getPerimeter() {
		return Math.PI * Math.sqrt((getWidth() * getWidth() + getHeight() * getHeight()) / 2);
	}

	// overridden method from super class
	@Override
	public String toString() {
		return "Ellipse extends" + super.toString();
	}

	/**
	 * At first we can inherit the superclass method of "equals", because 
	 * both Ellipse Objects also inherit type of the superclass (Circle) and
	 * method of "getWidth()" - that's why we do not need to write them again.
	 * 
	 * But then - in addition to the superclass method "equals()" we also need to 
	 * compare heights of Ellipses - because superclass doesn't have this comparison.
	 * 
	 * And before checking "getHeight()" we need to be sure that both Objects are 
	 * Ellipses - because Circles doesn't have "getHeight()" method. We can do that
	 * using "instanceof".
	 *
	 * @return true if equal, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		return 
				// checking superclass and "getWidth()" first
				super.equals(obj)
				// then checking the type - must be Ellipse, because
				// other classes do not have method "getHeight()"
				// only Ellipse has it
				&& obj instanceof Ellipse
				// and finally compare heights 
				&& this.getHeight() == ((Ellipse) obj).getHeight();
	}
}
