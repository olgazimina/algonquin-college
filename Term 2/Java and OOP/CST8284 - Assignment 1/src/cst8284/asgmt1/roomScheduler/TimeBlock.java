/*
 * Course Name: 20W_CST8284_310 Object Oriented Programming (Java)
 * Student Name: Olga Zimina
 * Class Name: TimeBlock
 * Date: Feb. 9, 2020
 */
package cst8284.asgmt1.roomScheduler;

import java.util.Calendar;

public class TimeBlock {
    private Calendar startTime;
    private Calendar endTime;

    /**
     * no-arg TimeBlock constructor should instantiate a RoomBooking starttime from 8 a.m. to 10 p.m.
     * (i.e. endTime = 23) on the day the program is executed (which is the default for a Calendar
     * object whenever a new Calendar is instantiated).
     */
    public TimeBlock() {
        Calendar st = Calendar.getInstance();
        st.clear();
        this.setStartTime(st);

        Calendar et = Calendar.getInstance();
        et.clear();
        this.setEndTime(et);
    }

    public TimeBlock(Calendar startTime, Calendar endTime) {
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.SECOND, 0);
        startTime.set(Calendar.MILLISECOND, 0);
        this.setStartTime(startTime);

        endTime.set(Calendar.MINUTE, 0);
        endTime.set(Calendar.SECOND, 0);
        endTime.set(Calendar.MILLISECOND, 0);
        this.setEndTime(endTime);
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    /**
     * Calendar has a method to compare it with a different calendar object.
     * https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html
     * https://javatutorialhq.com/java/util/calendar-class-tutorial/compareto-method-example/
     *
     * @param newBlock block to compare with
     *
     * @return true if object overlaps, false otherwise
     */
    public boolean overlaps(TimeBlock newBlock) {
        if ((this.getStartTime().compareTo(newBlock.getStartTime()) <= 0
             && this.getEndTime().compareTo(newBlock.getEndTime()) >= 0)) {
            return true;
        }
        return false;
    }

    /**
     * Returns the span of hours the room booking is for.
     * Thus if startTime is 9 and endTime is 17, the duration of the booking is for 8 hours.
     *
     * @return
     */
    public int duration() {
        return this.getEndTime().get(Calendar.HOUR_OF_DAY) - this.getStartTime().get(Calendar.HOUR_OF_DAY);
    }

    @Override
    /**
     * Returns data in format of: 8:00 - 14:00
     */
    public String toString() {
        return String.format("%02d:%02d - %02d:%02d",
            this.getStartTime().get(Calendar.HOUR_OF_DAY),
            this.getStartTime().get(Calendar.MINUTE),
            this.getEndTime().get(Calendar.HOUR_OF_DAY),
            this.getEndTime().get(Calendar.MINUTE));
    }
}
