/*
  @author Olga Zimina
  @course CST8284
  @date March, 26 2020
  @class Classroom.java
  @version 1.0.0
*/

package cst8284.asgmt3.room;

/**
 * POJO describing Room type of Class Room
 */
public final class Classroom extends Room {

	/**
	 * Default number of seats
	 */
	private static final int DEFAULT_SEATS = 120;

	/**
	 * Current number of seats
	 */
	private int seats;

	/**
	 * Default constructor
	 */
	public Classroom() {
		this.seats = DEFAULT_SEATS;
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
		return "class room";
	}

	/**
	 * Getter to return value of details variable
	 *
	 * @return String with details about the room
	 */
	protected String getDetails() {
		return "contains overhead projector";
	}
}
