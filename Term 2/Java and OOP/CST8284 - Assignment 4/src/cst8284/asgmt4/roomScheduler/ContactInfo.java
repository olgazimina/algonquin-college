/*
  @author Olga Zimina
  @course CST8284
  @date March, 26 2020
  @class ContactInfo.java
  @version 1.0.0
  Few methods are borrowed from original source code provided by prof. D. Houtman.
*/

package cst8284.asgmt4.roomScheduler;

import java.io.Serializable;

/**
 * POJO class setting information about the contact for event from RoomBooking
 * It describes the name of the person his phone number and organization
 */
public class ContactInfo implements Serializable {
	/**
	 * Contact's first name
	 */
	private String firstName;

	/**
	 * Contact's last name
	 */
	private String lastName;

	/**
	 * Contact's phone number
	 */
	private String phoneNumber;

	/**
	 * Contact's organization name
	 */
	private String organization;

	/**
	 * Default parameterized constructor
	 *
	 * @param firstName   Contact's first name
	 * @param lastName    Contact's last name
	 * @param phoneNumber Contact's phone number
	 */
	public ContactInfo(String firstName, String lastName, String phoneNumber) {
		this(firstName, lastName, phoneNumber, "Algonquin College");
	}

	/**
	 * Extended parameterized constructor
	 *
	 * @param firstName    Contact's first name
	 * @param lastName     Contact's last name
	 * @param phoneNumber  Contact's phone number
	 * @param organization Contact's organization name
	 */
	public ContactInfo(String firstName, String lastName, String phoneNumber, String organization) {
		setFirstName(firstName);
		setLastName(lastName);
		setPhoneNumber(phoneNumber);
		setOrganization(organization);
	}

	/**
	 * Setter to set value of contact's first name variable
	 *
	 * @param firstName String with contact's first name
	 */
	public void setFirstName(String firstName) {this.firstName = firstName;}

	/**
	 * Getter to return value of contact's first name variable
	 *
	 * @return String with contact's first name
	 */
	public String getFirstName() {return firstName;}

	/**
	 * Setter to set value of contact's last name variable
	 *
	 * @param lastName String with contact's last name
	 */
	public void setLastName(String lastName) {this.lastName = lastName;}

	/**
	 * Getter to return value of contact's last name variable
	 *
	 * @return String with contact's last name
	 */
	public String getLastName() {return lastName;}

	/**
	 * Setter to set value of contact's phone number variable
	 *
	 * @param phoneNumber String with contact's phone number
	 */
	public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

	/**
	 * Getter to return value of contact's phone number variable
	 *
	 * @return String with contact's phone number
	 */
	public String getPhoneNumber() {return phoneNumber;}

	/**
	 * Setter to set value of contact's organization name variable
	 *
	 * @param organization String with contact's organization name
	 */
	public void setOrganization(String organization) {this.organization = organization;}

	/**
	 * Getter to return value of contact's organization name variable
	 *
	 * @return String with contact's organization name
	 */
	public String getOrganization() {return organization;}

	/**
	 * Overwritten standard method to stringify object parameters
	 *
	 * @return Stringified Object parameters
	 */
	@Override
	public String toString() {
		return "Contact Information: " +
		       ((getFirstName() != "") ? (getFirstName() + " " + getLastName()) : "") + "\n" +
		       "Phone: " + getPhoneNumber() +
		       ((getOrganization().equals("")) ? "" : ("\n" + getOrganization() + "\n"));
	}
}
