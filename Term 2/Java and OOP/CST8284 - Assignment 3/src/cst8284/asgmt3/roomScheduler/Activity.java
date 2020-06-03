/*
  @author Olga Zimina
  @course CST8284
  @date March, 26 2020
  @class Activity.java
  @version 1.0.0
  Few methods are borrowed from original source code provided by prof. D. Houtman.
*/

package cst8284.asgmt3.roomScheduler;

import java.io.Serializable;

/**
 * POJO describing implementation of current activity for RoomBooking. Actually contains
 * two things - event category and detailed description of the event
 */
public class Activity implements Serializable {

	/**
	 * Describes type of the event
	 */
	private String category;

	/**
	 * Provides detailed description of the event
	 */
	private String description;

	/**
	 * @param category    String which describes type of the event
	 * @param description String which provides detailed description of the event
	 */
	public Activity(String category, String description) {
		setCategory(category);
		setDescription(description);
	}

	/**
	 * Getter to return value of detailed description variable
	 *
	 * @return String with detailed description of the event
	 */
	public String getDescription() {return description;}

	/**
	 * Setter to set value of detailed description variable
	 *
	 * @param description String with detailed description of the event
	 */
	public void setDescription(String description) {this.description = description;}

	/**
	 * Getter to return value of type of the event variable
	 *
	 * @return String with type of the event
	 */
	public String getCategory() {return category;}

	/**
	 * Setter to set value of type of the event variable
	 *
	 * @param category String with type of the event
	 */
	public void setCategory(String category) {this.category = category;}

	/**
	 * Overwritten standard method to stringify object parameters
	 *
	 * @return Stringified Object parameters
	 */
	@Override
	public String toString() {
		return "Event: " + getCategory() + "\n" +
		       ((getDescription() != "") ? "Description: " + getDescription() : "") + "\n";
	}
}
