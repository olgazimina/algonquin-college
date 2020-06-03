package cst8284.asgmt2.roomScheduler;
/*  Course Name: CST8284
    Author: Olga Zimina
    Class name: RoomBooking.java
    Date: February 28, 2020
    Few methods are borrowed from original source code provided by prof. D. Houtman.
*/

import java.io.Serializable;

public class RoomBooking implements Serializable {
	// as noted in the assignment
	public static final long serialVersionUID = 1L;

	private ContactInfo contactInfo;
	private Activity    activity;
	private TimeBlock   timeBlock;

	public RoomBooking(ContactInfo contactInfo, Activity activity, TimeBlock timeBlock) {
		setContactInfo(contactInfo);
		setActivity(activity);
		setTimeBlock(timeBlock);
	}

	public RoomBooking()                                {}

	public void setContactInfo(ContactInfo contactInfo) {this.contactInfo = contactInfo;}

	public ContactInfo getContactInfo()                 {return contactInfo;}

	public void setActivity(Activity activity)          {this.activity = activity;}

	public Activity getActivity()                       {return activity;}

	public void setTimeBlock(TimeBlock timeBlock)       {this.timeBlock = timeBlock;}

	public TimeBlock getTimeBlock()                     {return timeBlock;}

	@Override
	public String toString() {
		return (getTimeBlock().toString() + getActivity().toString() + getContactInfo().toString());
	}

}
