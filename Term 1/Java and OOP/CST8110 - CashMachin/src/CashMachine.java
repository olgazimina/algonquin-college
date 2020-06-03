import java.text.DecimalFormat;
import java.util.Scanner;

public class CashMachine {

    private int itemCount;
    private double tax;
    private double sum;
    private String receipt = "";
    private Scanner consoleInput = new Scanner(System.in);

    CashMachine(double tax) {
	this.tax=tax;
	
   }

    private String getOneStringInput(String argument) {
	System.out.print(argument);
	String inputValue = this.consoleInput.next();
	return inputValue;
    }

    private double getOneDoubleInput(String argument) {
	System.out.print(argument);
	double a = this.consoleInput.nextDouble();
	return a;
    }

    private int getItemCount() {
	return this.itemCount;
    }

    private double calculateTax() {
	return this.sum * 0.01 * this.tax;
    }

    private double caclulateTaxPlusSum() {
	return this.sum + this.calculateTax();
    }

    private void purchaseItem() {
	String name = this.getOneStringInput("item name (no spaces) >>");
	double price = this.getOneDoubleInput("item price >>");
	this.itemCount++;
	this.sum = this.sum + price;
	this.receipt = this.receipt + String.format("%-15s$%.2f%n", name, price);
    }

    private void printReceipt() {
	System.out.println();
	if (this.itemCount == 0) {
	    System.out.println("Empty!!");
	} else {
	    System.out.println("Receipt for " + this.itemCount + " items:");
	    System.out.println(this.receipt);
	    
	    DecimalFormat df = new DecimalFormat("#0.00");
	    System.out.println("Tax: $" + df.format(this.calculateTax()));
	    System.out.println("Total: $" + df.format(this.caclulateTaxPlusSum()));
	}
    }

    public void printMenu() {
	System.out.println();
	System.out.println("1) Purchase Item");
	System.out.println("2) Print Receipt");
	System.out.println("3) Quit");
	System.out.println();
    }

    public boolean processMenu() {
	double userInput = this.getOneDoubleInput("Action>>");
	if (userInput == 1) {
	    this.purchaseItem();
	} else if (userInput == 2) {
	    this.printReceipt();
	} else if (userInput == 3) {
	    return false;
	}
	return true;
    }
}
