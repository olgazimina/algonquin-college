/*
  @author Olga Zimina
  @course CST8284
  @date March, 26 2020
  @class ComputerLab.java
  @version 1.0.0
*/

package cst8284.asgmt4.room;

/**
 * POJO describing Room type of Computer Lab
 */
public final class ComputerLab extends Room {

	/**
	 * Default number of seats
	 */
	private static final int DEFAULT_SEATS = 30;

	/**
	 * Current number of seats
	 */
	private int seats;

	/**
	 * Default constructor
	 */
	public ComputerLab() {
		this.seats = DEFAULT_SEATS;
	}

	public ComputerLab(String str) {
		super(str);
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
		return "computer lab";
	}

	/**
	 * Getter to return value of details variable
	 *
	 * @return String with details about the room
	 */
	protected String getDetails() {
		return "contains outlets for 30 laptops";
	}
}
