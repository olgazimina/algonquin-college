package cst8284.asgmt2.room;
/*  Course Name: CST8284
    Author: Olga Zimina
    Class name: Boardroom.java
    Date: February 28, 2020
*/

public final class Boardroom extends Room {
	private int seats;

	public Boardroom() {
		this.seats = 16;
	}

	protected int getSeats() {
		return seats;
	}

	protected String getRoomType() {
		return "board room";
	}

	protected String getDetails() {
		return "conference call enabled";
	}

}
