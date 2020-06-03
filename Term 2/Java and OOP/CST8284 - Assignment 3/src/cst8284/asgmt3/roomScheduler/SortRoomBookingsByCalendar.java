/*
  @author Olga Zimina
  @course CST8284
  @date March, 26 2020
  @class SortRoomBookingsByCalendar.java
  @version 1.0.0
*/

package cst8284.asgmt3.roomScheduler;

import java.util.Comparator;

/**
 * Class which implements Comparator interface to sort array list of RoomBooking Objects
 */
public class SortRoomBookingsByCalendar implements Comparator<RoomBooking> {

	/**
	 * Custom implementation of Comparator interface. Requires for proper Collections.binarySearch() work
	 *
	 * @param o1 object of type RoomBooking to compare
	 * @param o2 object of type RoomBooking to compare
	 *
	 * @return int value (-1, 0, 1) if the comparing object before, equal or after the current one
	 */
	@Override
	public int compare(RoomBooking o1, RoomBooking o2) {
		return o1.getTimeBlock().getStartTime().compareTo(o2.getTimeBlock().getStartTime());
	}
}
