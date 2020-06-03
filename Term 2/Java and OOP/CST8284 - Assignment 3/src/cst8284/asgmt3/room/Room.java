/*
  @author David Houtman
  @course CST8284
  @date March, 26 2020
  @class Room.java
  @version 1.0.0
*/

package cst8284.asgmt3.room;

/**
 * Abstract POJO to describe the general room of any types - BoardRoom, Computer Lab and Class Room
 */
public abstract class Room {

	/**
	 * Default room number
	 */
	private static final String DEFAULT_ROOM_NUMBER = "unknown room number";

	/**
	 * Current room number
	 */
	private String roomNumber;

	/**
	 * Default constructor
	 */
	protected Room() {this(DEFAULT_ROOM_NUMBER);}

	/**
	 * Parameterized constructor
	 *
	 * @param roomNum String with the room number
	 */
	protected Room(String roomNum) { setRoomNumber(roomNum); }

	/**
	 * Setter to set value of roomNum variable
	 *
	 * @param roomNum String with the room number
	 */
	public void setRoomNumber(String roomNum) {roomNumber = roomNum;}

	/**
	 * Getter to get value of roomNum variable
	 *
	 * @return String with the room number
	 */
	public String getRoomNumber() {return roomNumber;}

	/**
	 * Abstract method to get current room type
	 *
	 * @return String with room type
	 */
	protected abstract String getRoomType();

	/**
	 * Abstract method to get current number of seats in the room
	 *
	 * @return int with number of seats in the room
	 */
	protected abstract int getSeats();

	/**
	 * Abstract method to get details about current room
	 *
	 * @return String details about current room
	 */
	protected abstract String getDetails();

	/**
	 * Overwritten standard method to stringify object parameters
	 *
	 * @return Stringified Object parameters
	 */
	public String toString() {
		return getRoomNumber() + " is a " +
		       getRoomType() + " with " + getSeats() + " seats; " + getDetails() + "\n";
	}
}
