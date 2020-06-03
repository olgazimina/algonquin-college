
public class Car {
	private String make;
	private String model;
	private int year;
	private String color;
	private String vin;
	private double price;

	public Car() {

	}

	public Car(String make, String model, String color, String vin, double price) {
		this.make = make;
		this.model = model;
		this.color = color;
		this.vin = vin;
		this.price = price;
	}

	public int getAge(int currentYear) {
		return currentYear - this.year;

	}

	public double getPrice() {
		return this.price;

	}

	public boolean overTenTousand() {
		if (price > 10000) {
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		return "";
	}

}
