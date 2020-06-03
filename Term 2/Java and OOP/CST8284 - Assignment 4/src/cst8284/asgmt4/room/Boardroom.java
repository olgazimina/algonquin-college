/*
 * @author Olga Zimina
 * @course CST8284
 * @date March, 26 2020
 * @class Boardroom.java
 * @version 1.0.0
 */

package cst8284.asgmt4.room;

/**
 * POJO describing Room type of Board Room
 */
public final class Boardroom extends Room {

	/**
	 * Current number of seats
	 */
	private int seats;

	/**
	 * Default constructor
	 */
	public Boardroom() {
		this.seats = 16;
	}

	/**
	 * Getter to return value of seats variable
	 *
	 * @return int containing number of seats
	 */
	protected int getSeats() {
		return seats;
	}

	/**
	 * Getter to return value of roomType variable
	 *
	 * @return String containing the type of the room
	 */
	protected String getRoomType() {
		return "board room";
	}

	/**
	 * Getter to return value of details variable
	 *
	 * @return String with details about the room
	 */
	protected String getDetails() {
		return "conference call enabled";
	}

}
