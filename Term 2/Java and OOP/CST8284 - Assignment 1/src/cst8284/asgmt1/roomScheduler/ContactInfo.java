/*
 * Course Name: 20W_CST8284_310 Object Oriented Programming (Java)
 * Student Name: Olga Zimina
 * Class Name: ContactInfo
 * Date: Feb. 9, 2020
 */
package cst8284.asgmt1.roomScheduler;

public class ContactInfo {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String organization;

    public ContactInfo(String firstName, String lastName, String phoneNumber) {
        this(firstName, lastName, phoneNumber, "Algonquin College");
    }

    public ContactInfo(String firstName, String lastName, String phoneNumber, String organization) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setPhoneNumber(phoneNumber);
        this.setOrganization(organization);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return String.format("Contact Information: %s %s\nPhone: %s\n%s",
            getFirstName(), getLastName(), getPhoneNumber(), getOrganization());
    }
}
