import java.text.DecimalFormat;
import java.util.Scanner;

public class Account {

    private long accountNumber;
    private String name;
    private double balance, interestPercent, interestAmount;
    private Scanner input = new Scanner(System.in);

    public Account() {
	this.accountNumber = 1000;
	this.name = "Not Set";
	this.balance = 100;
	this.interestPercent = 0.01;
    }

    public Account(long accountNumber, String name, double balance, double interestPercent) {
	this.accountNumber = accountNumber;
	this.name = name;
	this.balance = balance;
	this.interestPercent = interestPercent;
    }

    public void readInfo() {
	System.out.println("Enter account number ");
	this.accountNumber = input.nextLong();
	System.out.println("Enter name ");
	this.name = input.next();
	System.out.println("Enter balance ");
	this.balance = input.nextDouble();
	System.out.println("Enter interest percentege ");
	this.interestPercent = input.nextDouble();

	while (this.interestPercent < 0) {
	    System.out.println("Interest percentage cannot be negative. Please re enter: ");
	    this.interestPercent = input.nextDouble();
	}
    }

    public void calculateInterest() {
	this.interestAmount = this.balance * this.interestPercent / 100;
    }

    public void displayInfo() {
	this.calculateInterest();

	String message = String.format(
		"Account number: %10d Name: %10s Balance: %10.2f Interest Percentage %10.2f Interest %10.2f",
		this.accountNumber, this.name, this.balance, this.interestPercent, this.interestAmount);
	System.out.println(message);
    }
}
