/*
 * @author Olga Zimina
 * @course CST8284
 * @date   March, 26 2020
 * @class  BookingInput.java
 * @version 1.1.0
 */

package cst8284.asgmt4.roomScheduler;

/**
 * POJO to store data transferring between application components
 */
public class BookingInput {
	private String eventClient;
	private String eventPhoneNum;
	private String eventDate;
	private String eventSTime;
	private String eventETime;
	private String eventDescription;
	private String eventCategory;
	private String eventOrganization;
	private String oldEventDate;
	private String oldEventSTime;
	private String file;


	/**
	 * Default no arg constructor
	 */
	public BookingInput() {
	}

	/**
	 * All argument constructor
	 *
	 * @param eventClient       client first and last ame
	 * @param eventPhoneNum     phone number
	 * @param eventDate         date of the event
	 * @param eventSTime        start time
	 * @param eventETime        end time
	 * @param eventDescription  description of the event
	 * @param eventCategory     category of the event
	 * @param eventOrganization organisation
	 */
	public BookingInput(String eventClient, String eventPhoneNum, String eventDate, String eventSTime, String eventETime, String eventDescription, String eventCategory, String eventOrganization) {
		this.eventClient       = eventClient;
		this.eventPhoneNum     = eventPhoneNum;
		this.eventDate         = eventDate;
		this.eventSTime        = eventSTime;
		this.eventETime        = eventETime;
		this.eventDescription  = eventDescription;
		this.eventCategory     = eventCategory;
		this.eventOrganization = eventOrganization;
	}

	/**
	 * Getter for client first and last ame
	 *
	 * @return eventClient client first and last ame
	 */
	public String getEventClient() {
		return eventClient;
	}

	/**
	 * Setter for client first and last ame
	 *
	 * @param eventClient client first and last ame
	 */
	public void setEventClient(String eventClient) {
		this.eventClient = eventClient;
	}

	/**
	 * Getter for phone number
	 *
	 * @return phone number
	 */
	public String getEventPhoneNum() {
		return eventPhoneNum;
	}

	/**
	 * Setter for phone number
	 *
	 * @param eventPhoneNum client phone number
	 */
	public void setEventPhoneNum(String eventPhoneNum) {
		this.eventPhoneNum = eventPhoneNum;
	}

	/**
	 * Getter for date of the event
	 *
	 * @return date of the event
	 */
	public String getEventDate() {
		return eventDate;
	}

	/**
	 * Setter for date of the event
	 *
	 * @param eventDate date of the event
	 */
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	/**
	 * Getter for event start time
	 *
	 * @return event start time
	 */
	public String getEventSTime() {
		return eventSTime;
	}

	/**
	 * Setter for event start time
	 *
	 * @param eventSTime event start time
	 */
	public void setEventSTime(String eventSTime) {
		this.eventSTime = eventSTime;
	}

	/**
	 * Getter for event end time
	 *
	 * @return event end time
	 */
	public String getEventETime() {
		return eventETime;
	}

	/**
	 * Setter for event end time
	 *
	 * @param eventETime event end time
	 */
	public void setEventETime(String eventETime) {
		this.eventETime = eventETime;
	}

	/**
	 * Getter for event description
	 *
	 * @return event description
	 */
	public String getEventDescription() {
		return eventDescription;
	}

	/**
	 * Setter for event description
	 *
	 * @param eventDescription event description
	 */
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	/**
	 * Getter for event category information
	 *
	 * @return event category information
	 */
	public String getEventCategory() {
		return eventCategory;
	}

	/**
	 * Setter for event category information
	 *
	 * @param eventCategory event category information
	 */
	public void setEventCategory(String eventCategory) {
		this.eventCategory = eventCategory;
	}

	/**
	 * Getter for organization name
	 *
	 * @return organization name
	 */
	public String getEventOrganization() {
		return eventOrganization;
	}

	/**
	 * Setter for organization name
	 *
	 * @param eventOrganization organization name
	 */
	public void setEventOrganization(String eventOrganization) {
		this.eventOrganization = eventOrganization;
	}

	/**
	 * Getter for previous event start date
	 *
	 * @return previous event start date
	 */
	public String getOldEventDate() {
		return oldEventDate;
	}

	/**
	 * Setter for previous event start date
	 *
	 * @param oldEventDate previous event start date
	 */
	public void setOldEventDate(String oldEventDate) {
		this.oldEventDate = oldEventDate;
	}

	/**
	 * Getter for previous event start time
	 *
	 * @return previous event start time
	 */
	public String getOldEventSTime() {
		return oldEventSTime;
	}

	/**
	 * Setter for previous event start time
	 *
	 * @param oldEventSTime previous event start time
	 */
	public void setOldEventSTime(String oldEventSTime) {
		this.oldEventSTime = oldEventSTime;
	}

	/**
	 * Getter for filename
	 * @return filename
	 */
	public String getFile() {
		return file;
	}

	/**
	 * Setter for filename
 	 * @param file filename
	 */
	public void setFile(String file) {
		this.file = file;
	}
}
