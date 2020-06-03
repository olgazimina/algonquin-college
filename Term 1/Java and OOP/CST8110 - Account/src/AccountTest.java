
public class AccountTest {

    public static void main(String[] args) {

	/*
	 * Create an object acc1 with no-arg constructor o Create an object acc2 that
	 * has accountNumber as 1001, name as “John”, balance as 5000, interestPercent
	 * as 3.5. 
	 * 
	 * o Create an object acc3 and get details by invoking readInfo() method
	 * 
	 * o Calculate interest and print info of acc1. 
	 * 
	 * o Calculate interest and print info of acc2. 
	 * 
	 * o Calculate interest and print info of acc3.
	 */

	Account acc1 = new Account();
	Account acc2 = new Account(1001, "John", 5000, 3.5);
	Account acc3 = new Account();
	acc3.readInfo();
	
	acc1.displayInfo();
	acc2.displayInfo();
	acc3.displayInfo();
    }

}
