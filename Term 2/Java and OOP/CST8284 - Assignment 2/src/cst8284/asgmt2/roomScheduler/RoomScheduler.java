package cst8284.asgmt2.roomScheduler;
/*  Course Name: CST8284
    Author: Olga Zimina
    Class name: RoomScheduler.java
    Date: February 28, 2020
    Few methods are borrowed from original source code provided by prof. D. Houtman.
*/

import cst8284.asgmt2.room.Room;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class RoomScheduler {

	private static Scanner                scan         = new Scanner(System.in);
	// this ArrayList will contain all information about bookings
	private        ArrayList<RoomBooking> roomBookings = new ArrayList<>();
	private        Room                   room;

	private static final int
		DISPLAY_ROOM_INFORMATION = 1,
		ENTER_ROOM_BOOKING       = 2,
		DELETE_BOOKING           = 3,
		CHANGE_BOOKING           = 4,
		DISPLAY_BOOKING          = 5,
		DISPLAY_DAY_BOOKINGS     = 6,
		SAVE_BOOKINGS_TO_FILE    = 7,
		LOAD_BOOKINGS_FROM_FILE  = 8,
		EXIT                     = 0;

	/**
	 * Default constructor
	 *
	 * @param room room instance
	 */
	public RoomScheduler(Room room) {
		this.setRoom(room);
	}

	/**
	 * This is the main launcher for the menu.
	 */
	public void launch() {
		// "userChoice" - is what user enters as his/her choice
		// by default we can set anything, because it will be overwritten
		int userChoice = EXIT;

		// as it is a requirement we have to try to load data from file
		// and store it inside the ArrayList of roomBookings.
		// using saveRoomBooking() method will do the job without duplicates
		// but will change the order of elements inside
		// HOWEVER - in this case we will get during initial load the following messages:
		// Booking time and date saved.
		// Booking time and date saved.
		// which means we need to modify the saveRoomBooking() method to stop
		// printing this information and print it from executeMenuItem() method
		for (RoomBooking roomBooking : this.loadBookingsFromFile()) {
			this.saveRoomBooking(roomBooking);
		}
		// ArrayList doesn't have setter on UML diagram, so we have to
		// access it directly, because all methods must satisfy UML only
		// this.roomBookings = this.loadBookingsFromFile();

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
		System.out.println("Enter a selection from the following menu:");
		System.out.println(DISPLAY_ROOM_INFORMATION + ". Display room information");
		System.out.println(ENTER_ROOM_BOOKING + ". Enter a room booking");
		System.out.println(DELETE_BOOKING + ". Remove a room booking");
		System.out.println(CHANGE_BOOKING + ". Change a room booking");
		System.out.println(DISPLAY_BOOKING + ". Display a booking");
		System.out.println(DISPLAY_DAY_BOOKINGS + ". Display room bookings for the whole day");
		System.out.println(SAVE_BOOKINGS_TO_FILE + ". Backup current bookings to file");
		System.out.println(LOAD_BOOKINGS_FROM_FILE + ". Load current bookings from file");
		System.out.println(EXIT + ". Exit program");

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
			// display current room information
			case DISPLAY_ROOM_INFORMATION:
				displayRoomInfo();
				break;

			// when user want to create new booking
			case ENTER_ROOM_BOOKING:
				// first of all we need to make RoomBooking instance from user with makeBookingFromUserInput() method
				// and try to save it using saveRoomBooking() method.
				// saveRoomBooking() method is also used in other operations - during loading data from file
				// - that's why we need to move printing message here from saveRoomBooking() method.
				// Otherwise we will get the following messages on every load from file:
				// Booking time and date saved.
				// Booking time and date saved.
				if (saveRoomBooking(makeBookingFromUserInput())) {
					System.out.println("Booking time and date saved.");
				} else {
					System.out.println("Cannot save booking; that time is already booked");
				}
				break;

			// when user decided to delete booking
			case DELETE_BOOKING:
				System.out.println("Enter booking to delete");
				// we need to call "deleteBooking" with a parameter of newly created Calendar
				this.deleteBooking(makeCalendarFromUserInput(null, true));
				break;

			// in case user wants to change booking
			case CHANGE_BOOKING:
				System.out.println("Enter booking to change");
				// we need to call "changeBooking()" method with a parameter of newly created Calendar
				this.changeBooking(makeCalendarFromUserInput(null, true));
				break;

			// when user wants to list one specific booking
			case DISPLAY_BOOKING:
				// call this "displayBooking" method which will show us all information about the booking
				// or produce a note that there is no any bookings at that time
				displayBooking(makeCalendarFromUserInput(null, true));
				break;

			// when user wants to list all bookings for chosen day
			case DISPLAY_DAY_BOOKINGS:
				// we need to pass it into "displayDayBookings()" method
				// to show all information for the whole day
				displayDayBookings(makeCalendarFromUserInput(null, false));
				break;

			// we need to save all bookings at once in a file
			case SAVE_BOOKINGS_TO_FILE:
				// we need to check the status which method saveBookingsToFile() returns
				// if it equals true - then we need to write "success"
				if (this.saveBookingsToFile()) {
					System.out.println("Current room bookings backed up to file");
				} else {
					System.out.println("Bookings cannot be saved to file");
				}
				break;

			// here we are loading all bookings and insert them into instance variables
			case LOAD_BOOKINGS_FROM_FILE:
				// we need to check the status of the operation and print message - that's why we have to read
				// data from file first into new ArrayList
				ArrayList<RoomBooking> loadedRoomBookings = this.loadBookingsFromFile();
				// now we need to check - did we read data from file or not?
				// method loadBookingsFromFile() always returns us a list, but in case of failure
				// that list will be empty
				if (loadedRoomBookings.size() > 0) {
					// using saveRoomBooking() method will do the job without duplicates
					// but will change the order of elements inside
					for (RoomBooking roomBooking : loadedRoomBookings) {
						this.saveRoomBooking(roomBooking);
					}
					// since there's no setter for [roomBookings] in UML diagram
					// and we're not allowed to add new methods, so we have to get
					// direct access to the instance variable of [roomBookings]
					// this.roomBookings = this.loadBookingsFromFile();
				} else {
					// otherwise we need to print error message
					System.out.println("There is no bookings in the file or file is missing/corrupted");
				}
				break;

			// we have nothing to do with EXIT - just Exit
			case EXIT:
				// before exit - we need to save data into file - that's the default behaviour
				this.saveBookingsToFile();
				System.out.println("Exiting Room Booking Application\n");
				break;
			default:
				System.out.println("Invalid choice: try again. (Select " + EXIT + " to exit.)\n");
		}
		System.out.println();  // add blank line after each output
	}

	/**
	 * Show current Room information
	 */
	private void displayRoomInfo() {
		System.out.print(getRoom().toString());
	}

	/**
	 * This method is borrowed from original source code provided by prof. D. Houtman.
	 * This method adds RoomBooking Object into RoomBooking List
	 *
	 * @param roomBooking RoomBooking Object
	 *
	 * @return true if the RoomBooking Object was saved
	 * or false if it could not be saved
	 */
	private boolean saveRoomBooking(RoomBooking roomBooking) {
		// Check this TimeBlock to see if already booked
		TimeBlock tb = roomBooking.getTimeBlock();
		// use its Calendar
		Calendar cal = (Calendar) tb.getStartTime().clone();
		//Get first hour of block
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		//Loop through each hour in TimeBlock
		for (; hour < tb.getEndTime().get(Calendar.HOUR_OF_DAY); hour++) {
			// set next hour
			cal.set(Calendar.HOUR_OF_DAY, hour);
			// TimeBlock already booked at that hour, can't add appointment
			if (findBooking(cal) != null) {
				return false;
			}
		}
		// it is not necessary to use here indexes because ArrayList.add() method always
		// add new records to the end of the list.
		// source: https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#add-E-
		// The same for remove: https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#remove-int-
		// it will rebuild the indexes
		getRoomBookings().add(roomBooking);
		return true;
	}

	/**
	 * This method deletes one booking
	 *
	 * @param calendar Calendar which is used to find booking which we want to delete
	 *
	 * @return true if deleted and false if there is nothing do delete
	 */
	private boolean deleteBooking(Calendar calendar) {
		// we need to find a booking first to determine if there is a booking at all to delete?
		RoomBooking roomBooking = findBooking(calendar);
		// if it is not null, because findBooking() returns null in case it couldn't find booking
		// then we can safely delete the booking. Otherwise we need to show error message
		if (roomBooking == null) {
			System.out.println("There is no booking starting at that time");
			return false;
		} else {
			// if booking exists we need to ask user if he/she wants to delete that booking
			String decision = getResponseTo("Press 'Y' to confirm deletion, any other key to abort: ");

			if (decision.equalsIgnoreCase("y")) {
				// if the answer is "yes" - then we need to remove that booking from the list
				getRoomBookings().remove(roomBooking);
				System.out.println("Booking deleted");
				// and we also need to return true - because booking was deleted
				return true;
			} else {
				// if user entered anything else - we do not need to delete booking
				System.out.println("Booking has not deleted");
				// but we need to return false, since booking was not deleted
				return false;
			}

		}
	}

	/**
	 * This method allows to change one chosen booking by start time.
	 * By changing we understand choose different time within the same day,
	 * if that time has not been taken already.
	 *
	 * @param calendar Calendar instance
	 *
	 * @return true or false in case changing booking was made or not.
	 * Actually it is true when RoomBooking was found, otherwise false
	 */
	private boolean changeBooking(Calendar calendar) {
		RoomBooking roomBooking = this.displayBooking(calendar);
		// in case we have nothing to change - we have to return false
		if (roomBooking == null) {
			return false;
		}
		int roomBookingIndex = getRoomBookings().indexOf(roomBooking);

		// at first we need to setup start time, by making a new instance of the Calendar
		// we need to also clear Calendar, to prevent mistakes by milliseconds
		int      hour      = processTimeString(getResponseTo("Enter New Start Time: "));
		Calendar startTime = Calendar.getInstance();
		startTime.clear();
		startTime.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), hour, 0);

		// Second thing - we need to setup end time, by making a new instance of the Calendar
		// we also need to also clear Calendar, to prevent mistakes by milliseconds
		hour = processTimeString(getResponseTo("Enter New End Time: "));
		Calendar endTime = Calendar.getInstance();
		endTime.clear();
		endTime.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), hour, 0);

		// havinf start time and end time we can setup new time block instance
		TimeBlock timeBlock = new TimeBlock(startTime, endTime);

		// before save the new time we have to check if it is already taken or not
		// we allow to take time only in 2 conditions:
		// 1) if it is not taken
		// 2) if it is taken, but only by the current instance - which we want to re-book again
		RoomBooking checkBooking = this.findBooking(startTime);
		if (checkBooking == null || roomBooking.equals(checkBooking)) {
			// here we get RoomBooking from the list and set up new time block for the chosen instance
			getRoomBookings().get(roomBookingIndex).setTimeBlock(timeBlock);
			System.out.println("Booking has been changed to:");
			this.displayBooking(startTime);
			// we successfully changed the current booking - so return true
			return true;
		}

		// if those above conditions are false - booking cannot be changed
		System.out.println("Sorry this time is already booked");
		return false;
	}

	/**
	 * This method is borrowed from original source code provided by prof. D. Houtman.
	 * Display one BOOKING (if found) or special note (if time is not booked)
	 *
	 * @param cal calendar, containing time
	 *
	 * @return BOOKING if exists or NULL.
	 */
	private RoomBooking displayBooking(Calendar cal) {
		RoomBooking booking = findBooking(cal);
		int         hr      = cal.get(Calendar.HOUR_OF_DAY);
		System.out.print((booking != null) ?
			"---------------\n" + booking.toString() + "---------------\n" :
			"No booking scheduled between " + hr + ":00 and " + (hr + 1) + ":00\n"
		);
		return booking;
	}

	/**
	 * This method is borrowed from original source code provided by prof. D. Houtman.
	 * This method display all bookings for one day starting from 08:00 up to 23:00
	 *
	 * @param cal calendar, containing time
	 */
	private void displayDayBookings(Calendar cal) {
		for (int hrCtr = 8; hrCtr < 24; hrCtr++) {
			cal.set(Calendar.HOUR_OF_DAY, hrCtr);
			RoomBooking rb = displayBooking(cal);
			if (rb != null) { hrCtr = rb.getTimeBlock().getEndTime().get(Calendar.HOUR_OF_DAY) - 1; }
		}
	}

	/**
	 * This method is used to save all bookings into file.
	 * It is necessary to use Serialization in all classes - RoomBooking, Activity, ContactInfo, TimeBlock
	 * because all this classes are parts of RoomBooking.
	 * <p>
	 * Serialization is used to dump Java Object into file, like here:
	 * https://www.tutorialspoint.com/java/java_serialization.htm
	 * together with FileOutputStream and ObjectOutputStream
	 * very good example is here: https://stackoverflow.com/a/16111797
	 *
	 * @return true if file saved, false otherwise
	 */
	private boolean saveBookingsToFile() {
		String fileName = "CurrentRoomBookings.book";
		try {
			FileOutputStream   fileOutputStream   = new FileOutputStream(fileName);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			// using "objectOutputStream.writeObject()" we write all RoomBooking Objects
			// one after another from ArrayList into the file, we created above
			for (RoomBooking roomBooking : getRoomBookings()) {
				objectOutputStream.writeObject(roomBooking);
			}
			objectOutputStream.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}


	/**
	 * This method is used to read data from file and transform that data into ArrayList of RoomBooking
	 * here Deserialization used. very good example is here: https://stackoverflow.com/a/16111797
	 *
	 * @return ArrayList of RoomBooking Objects
	 */
	private ArrayList<RoomBooking> loadBookingsFromFile() {
		// this is the file name from which we're reading data
		String fileName = "CurrentRoomBookings.book";
		// this is the new empty ArrayList where we save all data we will read from file
		ArrayList<RoomBooking> roomBookings = new ArrayList<>();
		try {
			FileInputStream   fileInputStream   = new FileInputStream(fileName);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			// "objectInputStream.readObject()" used to read what we have in file with name
			// "CurrentRoomBookings.book" and since we saved there earlier the ArrayList of RoomBooking
			// Objects, so now we have to cast data from file to the type of "RoomBooking"
			// and using method "add()" of ArrayList - we add all RoomBooking Objects into "roomBookings" ArrayList
			// one after another.

			// we also have to use infinite loop and when we will reach the end of file
			// this method "objectInputStream.readObject()" produce an exception of type "EOFException"
			while (1 == 1) {
				roomBookings.add((RoomBooking) objectInputStream.readObject());
			}
		} catch (EOFException e) {
			// when we catch this type of exception - it means we read all objects from file and we
			// are ready to return the full ArrayList of RoomBooking Objects
			return roomBookings;
		} catch (IOException | ClassNotFoundException e) {
			// sample output doesn't provide any information - do we need to print anything
			// in case the file is missing or not. That's why it is empty, because it should
			// return roomBookings - but in this case it will be empty.
		}
		return roomBookings;
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
		// We also need to use "split()" to create first name and last name. How to use split() method I read here:
		// https://beginnersbook.com/2013/12/java-string-split-method-example/
		// As a result split() will make an array with firstname as the 1 element and last name as 2 element
		String[] fullName    = getResponseTo("Enter Client Name (as FirstName LastName): ").trim().split(" ");
		String   phoneNumber = getResponseTo("Phone Number (e.g. 613-555-1212): ").trim();
		// Then we need to ask user for organization.
		String organization     = getResponseTo("Organization (optional): ").trim();
		String eventCategory    = getResponseTo("Enter event category: ").trim();
		String eventDescription = getResponseTo("Enter detailed description of event: ").trim();
		// Now we can fill in the ContactInfo, because we have all the data and can use the parameterized
		// constructor of ContactInfo Object to create it. In this case "firstLastNames[0]" is the first name
		// and "firstLastNames[1]" is the last name.
		ContactInfo contactInfo = new ContactInfo(fullName[0], fullName[1], phoneNumber);
		// If user entered the name of organization (its length will be more than 0 then),
		// then we need to add it to the ContactInfo Object using setter
		if (organization.length() > 0) {
			contactInfo.setOrganization(organization);
		}
		// next step - is to create the Activity Object which we need as well in RoomBoking instance
		// after user entered data - we need to call parameterized constructor of Activity Class
		Activity activity = new Activity(eventCategory, eventDescription);
		// last step is to create Start Time and End Time. We have to use TimeBlock Object for that, which
		// have 2 Calendar Objects inside - 1 for Start time and 2 - for End Time
		TimeBlock timeBlock = new TimeBlock();
		// To set up StartTime we need to create Calendar Object which will store Start Time. We have to use
		// makeCalendarFromUserInput() method which returns to us fully ready Calendar. If we set second parameter
		// of this method to TRUE (and it is true now) that method asks us about DAY and START TIME of the event
		Calendar startTime = makeCalendarFromUserInput(null, true);
		// now use setter for Start Time to set the Start Time
		timeBlock.setStartTime(startTime);
		// endTime always must be larger than startTime. It is necessary to pay attention to minutes, seconds, milliseconds
		// because they're taking part in comparison of 2 Calendar objects.
		do {
			// We then need to create another Calendar Object to save End Time of the event. However here we do not ask
			// user to enter the DAY when it ends, because all events are one-day-long only. That's why we need to
			// use Start Time, which we created previously and send it into the method of "makeCalendarFromUserInput"
			// as the first parameter to be used.
			Calendar endTime = makeCalendarFromUserInput(startTime, true);
			// then we need to set End Time as a part of the TimeBlock Object using its internal setter
			timeBlock.setEndTime(endTime);
		} while (timeBlock.getEndTime().compareTo(timeBlock.getStartTime()) <= 0);
		// After we get all 3 objects ready: TimeBlock, ContactInfo and Activity - we can create RoomBooking Object
		// and then return that new RoomBooking Object
		return (new RoomBooking(contactInfo, activity, timeBlock));
	}

	/**
	 * This method is borrowed from original source code provided by prof. D. Houtman.
	 * This method is used to create Calendar instance from user input.
	 * Examples are takedn from https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html
	 * and from https://javatutorialhq.com/java/util/calendar-class-tutorial/set-method-example/
	 *
	 * @param initCal     input given Calendar. Can be null
	 * @param requestHour whether we need to setup time or not
	 *
	 * @return Calendar instance
	 */
	private Calendar makeCalendarFromUserInput(Calendar initCal, boolean requestHour) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		String  date    = "";
		int     hour    = 0;
		boolean needCal = (initCal == null);

		if (needCal) { date = getResponseTo("Event Date (entered as DDMMYYYY): "); }
		int day   = needCal ? Integer.parseInt(date.substring(0, 2)) : initCal.get(Calendar.DAY_OF_MONTH);
		int month = needCal ? Integer.parseInt(date.substring(2, 4)) - 1 : initCal.get(Calendar.MONTH);
		int year  = needCal ? Integer.parseInt(date.substring(4, 8)) : initCal.get(Calendar.YEAR);

		if (requestHour) {
			String time = getResponseTo((needCal ? "Start" : "End") + " Time: ");
			hour = processTimeString(time);
		}

		cal.set(year, month, day, hour, 0);
		return (cal);
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
	 * This method is borrowed from original source code provided by prof. D. Houtman.
	 *
	 * @param cal input given Calendar. Cannot be null
	 *
	 * @return null if there're no findings, or RoomBooking instance otherwise
	 */
	private RoomBooking findBooking(Calendar cal) {
		Calendar oneHourLater = Calendar.getInstance();
		oneHourLater.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) + 1);
		TimeBlock findTB = new TimeBlock(cal, oneHourLater);
		for (RoomBooking roomBooking : getRoomBookings()) {
			if (roomBooking.getTimeBlock().overlaps(findTB)) { return roomBooking; }
		}
		return null;
	}


	/**
	 * Getter to get the "roomBookings" list
	 *
	 * @return roomBookings
	 */
	private ArrayList<RoomBooking> getRoomBookings() {return roomBookings;}

	/**
	 * setter for Room Object
	 *
	 * @param room Room instance
	 */
	private void setRoom(Room room) {
		this.room = room;
	}

	/**
	 * getter to get "Room" object
	 *
	 * @return "Room" object
	 */
	private Room getRoom() {
		return this.room;
	}
}
