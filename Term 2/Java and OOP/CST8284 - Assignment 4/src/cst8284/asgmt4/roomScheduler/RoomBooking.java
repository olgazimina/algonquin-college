/*
  @author Olga Zimina
  @course CST8284
  @date March, 26 2020
  @class RoomBooking.java
  @version 1.0.0
*/

package cst8284.asgmt4.roomScheduler;

import java.io.Serializable;

/**
 * Main data structure containing all information about any booking
 */
public class RoomBooking implements Serializable, Comparable {
	/**
	 * as noted in the assignment
	 */
	public static final long serialVersionUID = 1L;

	/**
	 * POJO class setting information about the contact for even from RoomBooking
	 */
	private ContactInfo contactInfo;

	/**
	 * POJO describing implementation of current activity for RoomBooking
	 */
	private Activity activity;

	/**
	 * POJO providing data about start, end time of the event as well as its duration
	 */
	private TimeBlock timeBlock;

	/**
	 * Parameterized constructor
	 *
	 * @param contactInfo information about the contact for event
	 * @param activity    implementation of current activity
	 * @param timeBlock   data about start, end time of the event as well as its duration
	 */
	public RoomBooking(ContactInfo contactInfo, Activity activity, TimeBlock timeBlock) {
		setContactInfo(contactInfo);
		setActivity(activity);
		setTimeBlock(timeBlock);
	}

	/**
	 * Default constructor
	 */
	public RoomBooking() {}


	/**
	 * Setter to set value of contact's last name variable
	 *
	 * @param contactInfo Object with information about the contact for event
	 */
	public void setContactInfo(ContactInfo contactInfo) {this.contactInfo = contactInfo;}

	/**
	 * Getter to return value of contact's last name variable
	 *
	 * @return ContactInfo Object with information about the contact for event
	 */
	public ContactInfo getContactInfo() {return contactInfo;}

	/**
	 * Setter to set value of contact's last name variable
	 *
	 * @param activity Object with implementation of current activity
	 */
	public void setActivity(Activity activity) {this.activity = activity;}

	/**
	 * Getter to return value of contact's last name variable
	 *
	 * @return Activity Object with implementation of current activity
	 */
	public Activity getActivity() {return activity;}

	/**
	 * Setter to set value of contact's last name variable
	 *
	 * @param timeBlock Object with data about start, end time of the event as well as its duration
	 */
	public void setTimeBlock(TimeBlock timeBlock) {this.timeBlock = timeBlock;}

	/**
	 * Getter to return value of contact's last name variable
	 *
	 * @return TimeBlock Object with data about start, end time of the event as well as its duration
	 */
	public TimeBlock getTimeBlock() {return timeBlock;}

	/**
	 * Overwritten standard method to stringify object parameters
	 *
	 * @return Stringified Object parameters
	 */
	@Override
	public String toString() {
		return (getTimeBlock().toString() + getActivity().toString() + getContactInfo().toString());
	}

	/**
	 * Custom implementation of Comparable interface. Requires for proper Collections.binarySearch() work
	 *
	 * @param o object of type RoomBooking to compare with
	 *
	 * @return int value (-1, 0, 1) if the comparing object before, equal or after the current one
	 */
	@Override
	public int compareTo(Object o) {
		return this.getTimeBlock().getStartTime().compareTo(((RoomBooking) o).getTimeBlock().getStartTime());
	}
}
