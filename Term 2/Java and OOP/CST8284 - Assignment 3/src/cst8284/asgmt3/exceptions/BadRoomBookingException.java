/*
 * @author Olga Zimina
 * @course CST8284
 * @date   March, 26 2020
 * @class  BadRoomBookingException.java
 * @version 1.0.0
 */

package cst8284.asgmt3.exceptions;

/**
 * Class adding custom exception of type RunTimeException. This type of
 * exception is thrown when user inputs wrong data
 */
public class BadRoomBookingException extends RuntimeException {

	/**
	 * String with short reason of exception
	 */
	private String header;

	/**
	 * String with detailed message of exception
	 */
	private String message;

	/**
	 * Default constructor
	 */
	public BadRoomBookingException() {
		this("Bad room rooking entered", "Please try again");
	}

	/**
	 * Parameterized constructor
	 *
	 * @param header  String with short reason of exception
	 * @param message String with detailed message of exception
	 */
	public BadRoomBookingException(String header, String message) {
		super(message);
		this.setHeader(header);
	}

	/**
	 * Getter to return value of header variable
	 *
	 * @return String with short reason of exception
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * Setter to set value of header variable
	 *
	 * @param header String with short reason of exception
	 */
	public void setHeader(String header) {
		this.header = header;
	}

	/**
	 * Getter to return value of message variable
	 *
	 * @return String with detailed message of exception
	 */
	@Override
	public String getMessage() {
		return super.getMessage();
	}

	/**
	 * Setter to set value of message variable
	 *
	 * @param message String with detailed message of exception
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
