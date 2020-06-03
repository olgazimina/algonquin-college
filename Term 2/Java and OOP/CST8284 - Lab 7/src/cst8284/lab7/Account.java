/**
 * @student Olga Zimina
 * @studentnumber 
 */

package cst8284.lab7;

import java.text.*;
import java.util.Calendar;
import java.util.Date;

public class Account {
	private String accountNumber = "000-000000";  // branch number - customer account number
	private String firstName, lastName;
	private static final Calendar   ACCOUNTSTARTDATE = Calendar.getInstance();
	private              DateFormat dateFormat       = new SimpleDateFormat("yyyy-MM-dd");

	public Account(String accountNumber, String firstName, String lastName, String startDate) {
		dateFormat.setLenient(false);
		setAccountNumber(accountNumber);
		setFirstName(firstName);
		setLastName(lastName);
		setAccountStartDate(startDate);
	}

	private void setAccountNumber(String accountNumber) {
		// TODO #1: Run the QuestionGenerator and test for the error condition 
		// indicated.  If the condition exists, throw a BadAccountInputException 
		// displaying the message provided; this exception will be caught by the 
		// exception handler in the AccountLauncher class, and the user prompted 
		// for a new input.  Only if there is no error should the account number 
		// (in the line below) be set.  Note that in addition to the test indicated
		// in the QuestionGenerator, your code must also call the isCheckDigitCorrect()
		// method as well in determining whether to set the account number or not.
		if (accountNumber.contains(" ")) {
			throw new BadAccountInputException("Cannot have leading or trailing spaces in the input String");
		} else if (!isCheckDigitCorrect(accountNumber)) {
			throw new BadAccountInputException("Bad account number; check digit failed.");
		}
		this.accountNumber = accountNumber;
	}

	private static boolean isCheckDigitCorrect(String accountNumber) {
		// TODO #2: Write the code here to test the account number, according to
		// the algorithm indicated in the QuestionGenerator.  If the check digit 
		// is correct then return true, otherwise false.  Use the result to throw
		// a new BadAccountInputException in the setAccountNumber() method above, with
		// the message "Bad account number; check digit failed." if the result of
		// this method returns false.
		if (!accountNumber.matches("\\d{3}-\\d{6}")) { return false; }

		String customerAccountNumber = accountNumber.split("-")[1];

		int sum = 0;

		for (int pos = 0; pos < customerAccountNumber.length() - 1; pos++) {
			int number = Integer.parseInt(String.valueOf(customerAccountNumber.charAt(pos)));
			sum += pos % 2 == 0 ? -2 * number : 9 * number;
		}
		int lastDigit = Integer.parseInt(String.valueOf(customerAccountNumber.charAt(customerAccountNumber.length() - 1)));

		return sum % 9 == lastDigit;
	}

	private static boolean isInputNameCorrect(String name) {
		// TODO #3: Write the code here to test for the input name error condition indicated
		// in the Lab 7 QuestionsGenerator, and throw a new BadAccountInputException with
		// the message indicated.  This exception will be caught in the AccountLanucher 
		// class.  Only if this error does not occur should this function return true.
		// Note that this test is used in checking the setters for both the first
		// and last names, below.
		return name.length() < 30;
	}

	public void setAccountStartDate(String startDate) {

		Date date = null;
		try {
			date = dateFormat.parse(startDate);
			ACCOUNTSTARTDATE.setTime(date);
		} catch (ParseException e) {
			throw new BadAccountInputException("Format is YYYY-MM-DD");
		} catch (RuntimeException e) {
			throw new BadAccountInputException("General runtime exception thrown setting start date");
		}

		// TODO #4: Wrap the above code in a try/catch block, and check the
		// QuestionGenerator for the two exceptions you'll need to test for. 

	}

	private void setFirstName(String firstName) {
		if (isInputNameCorrect(firstName)) { this.firstName = firstName; } else {
			throw new BadAccountInputException("Name cannot exceed 30 characters");
		}
	}

	private void setLastName(String lastName) {
		if (isInputNameCorrect(lastName)) { this.lastName = lastName; } else {
			throw new BadAccountInputException("Name cannot exceed 30 characters");
		}
	}

	public String getAccountNumber()      {return accountNumber;}

	public String getFirstName()          {return firstName;}

	public String getLastName()           {return lastName;}

	public Calendar getAccountStartDate() {return ACCOUNTSTARTDATE;}

	public String toString() {
		return "Customer name: " + getFirstName() + " " + getLastName()
		       + "\nCustomer Account number: " + getAccountNumber()
		       + "\nAccount Created: " + getAccountStartDate().getTime().toString();
	}

}
