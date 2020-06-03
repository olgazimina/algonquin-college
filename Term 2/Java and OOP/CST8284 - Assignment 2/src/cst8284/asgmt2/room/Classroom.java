package cst8284.asgmt2.room;
/*  Course Name: CST8284
    Author: Olga Zimina
    Class name: Classroom.java
    Date: February 28, 2020
*/

public final class Classroom extends Room {
	private static final int DEFAULT_SEATS = 120;
	private              int seats;

	public Classroom() {
		this.seats = DEFAULT_SEATS;
	}

	protected int getSeats() {
		return seats;
	}

	protected String getRoomType() {
		return "class room";
	}

	protected String getDetails() {
		return "contains overhead projector";
	}

}
