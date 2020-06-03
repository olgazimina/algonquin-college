/**
 * @student Olga Zimina
 * @studentnumber 
 */

package cst8284.lab7;

import java.util.Scanner;

public class AccountLauncher {
	private static Account act;
	private static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		while (!loadAccount()) {}
		System.out.println(getAccount().toString());
	}

	private static boolean loadAccount() {
		boolean accountLoaded = false;
		try {
			String firstName = getResponseTo("Input customer first name: ");
			String lastName = getResponseTo("Input customer last name: ");
			String actNum = getResponseTo("Input customer account number (using format 123-123456): ");
    		String actStartUpDate = getResponseTo("Input account startup date as YYYY-MM-DD: ");
    		setAccount(new Account(actNum, firstName, lastName, actStartUpDate));
			accountLoaded = true;
		} catch (BadAccountInputException ex) {
			System.out.println(ex.getMessage() + "; please re-enter\n");
		} catch (Exception ex) {
			System.out.println("General exception thrown; source unknown");
		}
		return accountLoaded;
	}
	
	private static String getResponseTo(String s) {
		System.out.print(s);
		return(in.nextLine());
	}

	public static Account getAccount() {return act;}
	public static void setAccount(Account act) {AccountLauncher.act = act;}
}