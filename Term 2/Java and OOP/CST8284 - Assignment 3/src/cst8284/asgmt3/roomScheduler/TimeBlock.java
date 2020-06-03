/*
  @author Olga Zimina
  @course CST8284
  @date March, 26 2020
  @class TimeBlock.java
  @version 1.0.0
*/

package cst8284.asgmt3.roomScheduler;

import cst8284.asgmt3.exceptions.BadRoomBookingException;

import java.io.Serializable;
import java.util.Calendar;

/**
 * POJO providing data about start, end time of the event as well as its duration
 */
public class TimeBlock implements Serializable {
	/**
	 * Contains information about start date and time
	 */
	private Calendar startTime;

	/**
	 * Contains information about end date and time
	 */
	private Calendar endTime;

	/**
	 * Default constructor. Uses to set up any date (usually start of Unix Epoch)
	 * together with different time for start and end
	 */
	public TimeBlock() {
		this(new Calendar.Builder().set(Calendar.HOUR_OF_DAY, 8).build(),
			new Calendar.Builder().set(Calendar.HOUR_OF_DAY, 24).build());
	}

	/**
	 * Parameterized constructor
	 *
	 * @param start information about start date and time
	 * @param end   information about end date and time
	 */
	public TimeBlock(Calendar start, Calendar end) {
		setStartTime(start);
		setEndTime(end);
	}

	/**
	 * Overwritten standard method to stringify object parameters
	 *
	 * @return Stringified Object parameters
	 */
	@Override
	public String toString() {
		return getStartTime().get(Calendar.HOUR_OF_DAY) + ":00 - " + getEndTime().get(Calendar.HOUR_OF_DAY) + ":00\n";
	}

	/**
	 * Setter to set Calendar Object with information about start date and time
	 *
	 * @param startTime Calendar Object with information about start date and time
	 */
	public void setStartTime(Calendar startTime) {this.startTime = startTime;}

	/**
	 * Getter to return Calendar Object with information about start date and time
	 *
	 * @return Calendar Object with information about start date and time
	 */
	public Calendar getStartTime() {return startTime;}

	/**
	 * Setter to set Calendar Object with information about end date and time
	 * At the time of assigning instance variables perform 2 checks -
	 * end time should not be equals or before the start time
	 *
	 * @param endTime Calendar Object with information about end date and time
	 */
	public void setEndTime(Calendar endTime) {
		if (endTime.before(getStartTime())) {
			throw new BadRoomBookingException("End time precedes start time",
				"The room booking start time must occur before the end time of the room booking.");
		} else if (endTime.equals(getStartTime())) {
			throw new BadRoomBookingException("Zero time room booking",
				"Start and end time of the room booking are the same. The minimum time for a room booking is one hour.");
		}
		this.endTime = endTime;
	}

	/**
	 * Getter to return Calendar Object with information about end date and time
	 *
	 * @return Calendar Object with information about end date and time
	 */
	public Calendar getEndTime() {return endTime;}

	/**
	 * Return the duration of the even in hours
	 *
	 * @return int amount of hours for requested event
	 */
	public int duration() {
		return (getEndTime().get(Calendar.HOUR_OF_DAY) - getStartTime().get(Calendar.HOUR_OF_DAY));
	}

	/**
	 * Compare 2 TimeBlock objects to check whether they overlaps each other or not
	 *
	 * @param tb instance of the TimeBlock Object to compare with
	 *
	 * @return true if time overlaps, else otherwise
	 */
	public boolean overlaps(TimeBlock tb) {
		if ((tb.getStartTime().get(Calendar.DAY_OF_YEAR) != this.getStartTime().get(Calendar.DAY_OF_YEAR))
		    || (tb.getStartTime().get(Calendar.YEAR) != this.getStartTime().get(Calendar.YEAR))) {
			return false;
		}
		return ((tb.getStartTime().get(Calendar.HOUR_OF_DAY) < getEndTime().get(Calendar.HOUR_OF_DAY))
		        && (tb.getEndTime().get(Calendar.HOUR_OF_DAY) > getStartTime().get(Calendar.HOUR_OF_DAY)));
	}

}
