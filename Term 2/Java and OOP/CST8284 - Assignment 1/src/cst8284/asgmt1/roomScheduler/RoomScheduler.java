/*
 * Course Name: 20W_CST8284_310 Object Oriented Programming (Java)
 * Student Name: Olga Zimina
 * Class Name: RoomScheduler
 * Date: Feb. 9, 2020
 */
package cst8284.asgmt1.roomScheduler;

import java.util.Calendar;
import java.util.Scanner;

public class RoomScheduler {
    private static Scanner       scan         = new Scanner(System.in);
    private        RoomBooking[] roomBookings = new RoomBooking[80];
    private        int           lastBookingIndex;

    private static final int ENTER_ROOM_BOOKING   = 1;
    private static final int DISPLAY_BOOKING      = 2;
    private static final int DISPLAY_DAY_BOOKINGS = 3;
    private static final int EXIT                 = 0;

    public RoomScheduler() {
        setBookingIndex(0);
    }

    /**
     * This is the main launcher for the menu.
     */
    public void launch() {
        // "userChoice" - is what user enters as his/her choice
        // by default we can set anything, because it will be overwritten
        int userChoice = EXIT;

        // we have to use "do ... while" to draw the menu at least once
        do {
            userChoice = displayMenu();
            executeMenuItem(userChoice);
        } while (userChoice != EXIT);
    }

    /**
     * This method displays menu and obtain customers's choice.
     *
     * @return int - userChoice as integer value
     */
    private int displayMenu() {
        System.out.println();
        System.out.println("1. Enter a room booking");
        System.out.println("2. Display a booking");
        System.out.println("3. Display room bookings for the whole day");
        System.out.println("0. Exit program");

        // this is a variable which is used to return user's choice
        int userChoice = scan.nextInt();
        // since next int scans only numbers, we need to use this up to  "\n"
        scan.nextLine();

        // return the value of the user choice
        return userChoice;
    }

    /**
     * this method is called by displayMenu() and given a value what user chose, execute it
     * how to use switch - https://www.tutorialspoint.com/java/switch_statement_in_java.htm
     *
     * @param choice user choice
     */
    private void executeMenuItem(int choice) {
        // using switch instead of if ... else
        switch (choice) {

            // when user want to create new booking
            case ENTER_ROOM_BOOKING:
                // first of all we need to make RoomBooking instance from user
                // when we leave the scope this variable (booking) will be destroyed by garbage collector
                RoomBooking booking = this.makeBookingFromUserInput();

                // then we need to pass it to the method which adds it to the RoomBooking array
                // This "saveRoomBooking" method returns TRUE if saving was successful
                // And returns FALSE if anything goes wrong
                if (this.saveRoomBooking(booking) == true) {

                    // After we saved (added RoomBooking Object into array of RoomBooking)
                    // and this operation finished successfully - we need to increment index
                    this.setBookingIndex(getBookingIndex() + 1);
                    // produce successful message
                    System.out.println("Booking time and date saved.");
                }
                break;

            // when user wants to list one specific booking
            case DISPLAY_BOOKING:
                // to display one booking we need to create Calendar object which tells what exactly to display
                Calendar calendar = this.makeCalendarFromUserInput(null, true);
                // and then we call this "displayBooking" method which will show us all information about the booking
                // or produce a note that there is no any bookings at that time
                displayBooking(calendar);
                break;

            // when user wants to list all bookings for chosen day
            case DISPLAY_DAY_BOOKINGS:
                // in this case we would like to show all bookings for the whole day. That means we do not need
                // to ask user about entering time, but we need to ask him/her about day only. That's why we need
                // to pass second parameter to "makeCalendarFromUserInput" as FALSE - because it is used for asking time
                Calendar wholeDay = this.makeCalendarFromUserInput(null, false);
                // getting the above Calendar Object ready - we need to pass it into  "displayDayBookings()" method
                // to show all information for the whole day
                displayDayBookings(wholeDay);
                break;

            // we have nothing to do with EXIT - thus it's default
            default:
                break;
        }
    }

    /**
     * This method creates an instance of RoomBooking Object to save later into array of RoomBookings.
     * Each RoomBooking Object contains 3 other Objects inside it of types: TimeBlock - responsible for
     * "startTime" and "endTime" of the event, ContactInfo - contains all contact information about the person,
     * and Activity - contains 2 fields (event and description).
     * <p>
     * Here we need to get user input and use the data to create Objects: TimeBlock, ContactInfo and Activity
     * After creation of them - we call constructor of RoomBooking Class to create new Object and return it
     *
     * @return new RoomBooking Object
     */
    private RoomBooking makeBookingFromUserInput() {

        // Requiring user to enter contact information
        // we need to use "trim()" methods to remove empty spaces before entered data and after eneterd data
        // here is the example of using TRIM() - https://www.geeksforgeeks.org/java-string-trim-method-example/
        String clientNames = RoomScheduler.getResponseTo("Enter Client Name (as FirstName LastName): ").trim();
        // then we need to use "split()" to create first name and last name. How to use split() method I read here:
        // https://beginnersbook.com/2013/12/java-string-split-method-example/
        // As a result split() will make an array with firstname as the 1 element and last name as 2 element
        String[] firstLastNames = clientNames.split(" ");
        String   clientPhone    = RoomScheduler.getResponseTo("Phone Number (e.g. 613-555-1212): ").trim();
        // Now we can fill in the ContactInfo, because we have all the data and can use the parameterized
        // constructor of ContactInfo Object to create it. In this case "firstLastNames[0]" is the first name
        // and "firstLastNames[1]" is the last name.
        ContactInfo contactInfo = new ContactInfo(firstLastNames[0], firstLastNames[1], clientPhone);

        // Then we need to ask user for organization.
        String organization = RoomScheduler.getResponseTo("Organization (optional): ").trim();
        // If user entered the name of organization (its length will be more than 0 then),
        // then we need to add it to the ContactInfo Object using setter
        if (organization.length() > 0) {
            contactInfo.setOrganization(organization);
        }

        // next step - is to create the Activity Object which we need as well in RoomBoking instance
        String eventCategory    = RoomScheduler.getResponseTo("Enter event category: ").trim();
        String eventDescription = RoomScheduler.getResponseTo("Enter detailed description of event: ").trim();
        // after user entered data - we need to call parameterized constructor of Activity Class
        Activity activity = new Activity(eventDescription, eventCategory);

        // last step is to create Start Time and End Time. We have to use TimeBlock Object for that, which
        // have 2 Calendar Objects inside - 1 for Start time and 2 - for End Time
        TimeBlock timeBlock = new TimeBlock();
        // To set up StartTime we need to create Calendar Object which will store Start Time. We have to use
        // makeCalendarFromUserInput() method which returns to us fully ready Calendar. If we set second parameter
        // of this method to TRUE (and it is true now) that method asks us about DAY and START TIME of the event
        Calendar startTime = this.makeCalendarFromUserInput(null, true);
        // now use setter for Start Time to set the Start Time
        timeBlock.setStartTime(startTime);

        // endTime always must be larger than startTime. It is necessary to pay attention to minutes, seconds, milliseconds
        // because they're taking part in comparison of 2 Calendar objects.
        do {
            // We then need to create another Calendar Object to save End Time of the event. However here we do not ask
            // user to enter the DAY when it ends, because all events are one-day-long only. That's why we need to
            // use Start Time, which we created previously and send it into the method of "makeCalendarFromUserInput"
            // as the first parameter to be used.
            Calendar endTime = this.makeCalendarFromUserInput(startTime, true);
            // then we need to set End Time as a part of the TimeBlock Object using its internal setter
            timeBlock.setEndTime(endTime);
        } while (timeBlock.getEndTime().compareTo(timeBlock.getStartTime()) <= 0);

        // After we get all 3 objects ready: TimeBlock, ContactInfo and Activity - we can create RoomBooking Object
        RoomBooking roomBooking = new RoomBooking(timeBlock, contactInfo, activity);
        // and then return that new RoomBooking Object
        return roomBooking;
    }

    /**
     * This method is used to create Calendar instance from user input.
     * Examples are takedn from https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html
     * and from https://javatutorialhq.com/java/util/calendar-class-tutorial/set-method-example/
     *
     * @param cal         input given Calendar. Can be null
     * @param requestHour whether we need to setup time or not
     *
     * @return Calendar instance
     */
    private Calendar makeCalendarFromUserInput(Calendar cal, boolean requestHour) {
        // instantiate new calendar
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        // in case nothing has been booked yet we need to ask for the full date to enter.
        // we also would like to ask about Start Time ONLY!!!
        if (cal == null) {
            // We have to ask user about date and remove preceding and trailing whitespaces
            String date = RoomScheduler.getResponseTo("Event Date (entered as DDMMYYYY): ").trim();
            // convert date String representation into int values for Calendar setters
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date.substring(0, 2)));
            calendar.set(Calendar.MONTH, Integer.parseInt(date.substring(2, 4)) - 1);
            calendar.set(Calendar.YEAR, Integer.parseInt(date.substring(4, 8)));

            if (requestHour) {
                String rawTime = RoomScheduler.getResponseTo("Start Time: ").trim();
                int    hour    = processTimeString(rawTime);
                calendar.set(Calendar.HOUR_OF_DAY, hour);
            }
            // to prevent user entering End Time by overwriting Start Time we need to exit and call
            // this method again, but pass this Calendar Object as a first parameter now - then this method
            // will ask only about End Time
            return calendar;
        }

        // in case "requestHour" is set to false we have to suppress user input
        if (requestHour) {
            calendar.setTime(cal.getTime());
            String rawTime = RoomScheduler.getResponseTo("End Time: ").trim();
            int    hour    = processTimeString(rawTime);
            calendar.set(Calendar.HOUR_OF_DAY, hour);
        }
        return calendar;
    }

    /**
     * Transform time from String to int representation and extract HOUR.
     * String.split() examples are taken https://www.tutorialspoint.com/java/java_string_split.htm
     * String.contains() examples are taken at: https://www.guru99.com/string-contains-method-java.html
     * and https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#contains-java.lang.CharSequence-
     *
     * @param t time entered by user as a String
     *
     * @return value of HOUR converted to int and extracted from input parameter
     */
    private static int processTimeString(String t) {
        String[] timeArray;
        // check time format with "contains()" method of String for "am - pm". If the current string "t"
        // contains any combinations of the "am, a.m., pm and p.m" - then we need to use space (" ")
        // to split Numbers from letters
        // we also use here toLowerCase() method because it makes all letters small and we do not need to check
        // CAPITAL LETTERS like "AM, A.M., PM, P.M"
        if (t.toLowerCase().contains("am")
            || t.toLowerCase().contains("pm")
            || t.toLowerCase().contains("a.m.")
            || t.toLowerCase().contains("p.m.")) {
            // in this case HOUR is separated by space only so we can use space as a delimiter
            timeArray = t.split(" ");
        } else {
            // otherwise time is entered in 24-hours format and then a delimiter is ":"
            timeArray = t.split(":");
        }

        // before returning HOURs value we need to correct it for 12 hours in case it is "p.m." time
        // to keep case insensitive data  - we need to use toLowerCase() method from String
        if (timeArray[1].toLowerCase().contains("p") && Integer.parseInt(timeArray[0]) != 12) {
            return (Integer.parseInt(timeArray[0]) + 12);
        }
        // in all other case we need to return unmodified value of timeArray[0]
        return Integer.parseInt(timeArray[0]);
    }

    /**
     * This method adds RoomBooking Object into RoomBooking Array
     *
     * @param booking RoomBooking Object
     *
     * @return true if the RoomBooking Object was saved
     *          or false if it could not be saved
     */
    private boolean saveRoomBooking(RoomBooking booking) {
        // we need to check "booking" Object if it is null or not
        if (booking != null
            // and check if that's possible to find a place to book time
            // or is it already taken by someone else
            && this.findBooking(booking.getTimeBlock().getStartTime()) != null) {

            // in this case (time taken or no RoomBooking Object) return false and exit from this method
            return false;
        }

        // if we're here at this step - it means time is available and RoomBooking Object is OK
        // so we can save it, by adding into array of RoomBookings
        this.roomBookings[this.getBookingIndex()] = booking;
        // and increase Booking Index to +1 for next Object
        this.setBookingIndex(this.getBookingIndex() + 1);
        return true;
    }

    /**
     * Display one BOOKING (if found) or special note (if time is not booked)
     *
     * @param cal calendar, containing time
     *
     * @return BOOKING if exists or NULL.
     */
    private RoomBooking displayBooking(Calendar cal) {
        // since Calendar contains time - we need to
        // search to check what is booked for that time
        RoomBooking booking = this.findBooking(cal);

        // if something is booked - we have to show it on display
        if (booking != null) {
            // print it using "toString" method of RoomBooking class
            System.out.println(booking);
            // and finally returning booking
            return booking;
        }

        // in case nothing was booked for that time
        // we need to simply show a note about that
        // that's why we need to setup the calendar - to show a time
        int startHour = cal.get(Calendar.HOUR_OF_DAY);
        // and calculate last hour, by adding +1
        int endHour = startHour + 1;
        // checking for midnight
        if (endHour > 23) { endHour = 0; }
        // and printing the message
        System.out.printf("No booking scheduled between %2d:00 and %2d:00\n", startHour, endHour);

        return null;
    }

    /**
     * This method display all bookings for one day starting from 08:00 up to 23:00
     *
     * @param cal
     */
    private void displayDayBookings(Calendar cal) {
        // initiate a loop for changing time of search from 08:00 to 23:00
        for (int hour = 8; hour < 23; hour++) {
            // we have to set up HOUR manually to check if anything exists for that time
            cal.set(Calendar.HOUR_OF_DAY, hour);
            // doing search, which returns us either NULL or BOOKING
            RoomBooking booking = this.displayBooking(cal);

            // if search returns us a BOOKING (which means this time is taken)
            // then we do not need to look through this time, i.e. - we have
            // a booking from 09:00 to 12:00, so we can skip this period
            if (booking != null) {
                // skipping period is easy - we simply increase the counter for hours
                // on the duration of the event
                hour += booking.getTimeBlock().duration() - 1;
            }
        }
    }

    /**
     * Method to grab user input
     *
     * @param s - String to show user
     *
     * @return what user enters
     */
    private static String getResponseTo(String s) {
        System.out.print(s);
        return (scan.nextLine());
    }

    /**
     * How to add 1 hour to existing calendar object:
     * https://stackoverflow.com/questions/5950417/how-to-increment-time-by-1-hour
     *
     * @param cal existing calendar
     *
     * @return existing RoomBooking object or null if not found
     */
    private RoomBooking findBooking(Calendar cal) {
        // we need to create 2 different calendars for STARTTIME and ENDTIME
        Calendar startTime = Calendar.getInstance();
        startTime.clear();
        Calendar endTime = Calendar.getInstance();
        endTime.clear();

        // We need to initialize TIMEBLOCK to push inside the STARTTIME and ENDTIME
        TimeBlock timeBlock = new TimeBlock();

        // then setting up the beginning time itself
        startTime.setTime(cal.getTime());
        // and setting starttime for TIMEBLOCK
        timeBlock.setStartTime(startTime);

        // setting up the end of the block by
        endTime.setTime(cal.getTime());
        // automatically adding +1 hour to initial value
        endTime.add(Calendar.HOUR_OF_DAY, 1);
        // and setting endtime for TIMEBLOCK
        timeBlock.setEndTime(endTime);

        // now we need to search in the whole array of "roomBookings"
        for (int i = 0; i < this.getRoomBookings().length; i++) {
            // we need to check if the element from array is not NULL
            if (this.getRoomBookings()[i] != null
                // and if it has any overlaps with another block
                && this.getRoomBookings()[i].getTimeBlock().overlaps(timeBlock)) {
                // in case this TIME is already booked - we have to return that booking
                return this.getRoomBookings()[i];
            }
        }

        // in all other cases we return null
        return null;
    }

    /**
     * Getter to get the "roomBookings" array
     *
     * @return roomBookings
     */
    private RoomBooking[] getRoomBookings() {
        return this.roomBookings;
    }

    /**
     * Getter to get last index
     *
     * @return last index to pointing to the last record in the roomBookings array
     */
    private int getBookingIndex() {
        return this.lastBookingIndex;
    }

    /**
     * setter to set booking index
     *
     * @param bookingIndex
     */
    private void setBookingIndex(int bookingIndex) {
        this.lastBookingIndex = bookingIndex;
    }
}
