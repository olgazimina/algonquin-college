import java.util.Scanner;

public class VendingMachine {
    private Scanner scanner = new Scanner(System.in);
    private String purchasedItem;
    private double purchasedSum;

    VendingMachine() {
	this.purchasedItem = "";
	this.purchasedSum = 0;
    }

    private void printMenu() {
	System.out.println("Welcome to Vending Machine!\n");
	System.out.println("Apple...........$3.00");
	System.out.println("Grape...........$5.00");
	System.out.println("Grapefruit......$7.00\n");

	if (this.purchasedItem.isEmpty()) {
	    System.out.println("1) Purchase Item");
	} else {
	    System.out.println("1) Clear basket");
	}

	System.out.println("2) Print Receipt");
	System.out.println("3) Quit");
	System.out.println();
	System.out.print("What are you going to do? ");
    }

    private void purchaseItem() {
	System.out.print("Please type your choice: ");
	String product = this.scanner.next();
	if ("apple".equalsIgnoreCase(product)) {
	    this.purchasedItem = "Apple";
	    this.purchasedSum = 3;
	} else if ("grape".equalsIgnoreCase(product)) {
	    this.purchasedItem = "Grape";
	    this.purchasedSum = 5;
	} else if ("Grapefruit".equalsIgnoreCase(product)) {
	    this.purchasedItem = "Grapefruit";
	    this.purchasedSum = 7;
	} else {
	    System.out.println("You chose the wrong item.");
	}
	System.out.println();
    }

    private void printReceipt() {
	System.out.println("\n######################");
	System.out.println("You bought:");
	System.out.println(this.purchasedItem + " for $" + this.purchasedSum);
	System.out.println("######################\n");
    }

    public void runMainLoop() {
	int customerChoice = 0;

	while (customerChoice != 3) {
	    
	    this.printMenu();
	    
	    customerChoice = this.scanner.nextInt();
	    
	    if (customerChoice == 1 && this.purchasedItem.isEmpty()) {
		this.purchaseItem();
	    } else if (customerChoice == 1 && !this.purchasedItem.isEmpty()) {
		this.purchasedItem = "";
		this.purchasedSum = 0;
	    } else if (customerChoice == 2) {
		this.printReceipt();
	    }
	}

	this.scanner.close();
    }
}
