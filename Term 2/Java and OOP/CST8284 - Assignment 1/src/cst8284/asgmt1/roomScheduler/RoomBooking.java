/*
 * Course Name: 20W_CST8284_310 Object Oriented Programming (Java)
 * Student Name: Olga Zimina
 * Class Name: RoomBooking
 * Date: Feb. 9, 2020
 */
package cst8284.asgmt1.roomScheduler;

public class RoomBooking {
    private ContactInfo contactInfo;
    private Activity activity;
    private TimeBlock timeBlock;

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public TimeBlock getTimeBlock() {
        return timeBlock;
    }

    public void setTimeBlock(TimeBlock timeBlock) {
        this.timeBlock = timeBlock;
    }

    public RoomBooking(TimeBlock timeBlock, ContactInfo contactInfo, Activity activity) {
        this.timeBlock = timeBlock;
        this.contactInfo = contactInfo;
        this.activity = activity;
    }

    @Override
    public String toString() {
        return String.format("---------------\n%s\n%s\n%s\n---------------",
            this.getTimeBlock(), this.getActivity(), this.getContactInfo());
    }
}
