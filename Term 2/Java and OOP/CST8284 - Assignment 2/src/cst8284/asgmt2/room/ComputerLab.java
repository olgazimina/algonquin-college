package cst8284.asgmt2.room;
/*  Course Name: CST8284
    Author: Olga Zimina
    Class name: ComputerLab.java
    Date: February 28, 2020
*/

public final class ComputerLab extends Room {
	private static final int DEFAULT_SEATS = 30;
	private              int seats;

	public ComputerLab() {
		this.seats = DEFAULT_SEATS;
	}

	protected int getSeats() {
		return seats;
	}

	protected String getRoomType() {
		return "computer lab";
	}

	protected String getDetails() {
		return "contains outlets for 30 laptops";
	}

}
