package lab5;

import java.util.Scanner;

public class CountTest {
    public static void main(String[] args) {
        System.out.println("Program developed by Olga Zimina");
        Count count = new Count();

        Scanner scanner = new Scanner(System.in);
        int userChoice = 0;

        do {
            System.out.println("1) Display Count");
            System.out.println("2) Exit");
            System.out.println();
            System.out.print("Enter your selection:");
            userChoice = scanner.nextInt();

            if (userChoice == 1) {
                count.getValuesFromUser();
                count.displaySteps();
            } else if(userChoice == 2) {
                System.out.println("Goodbye!!!");
            } else {
                System.out.println("Please enter correct option");
            }
        } while (userChoice != 2);
    }
}

	/*//for loop up
	int numberOfTimes = 10;
	for (int i = 0; i > numberOfTimes; i++) {
	    System.out.println("this is time #" + i);
	}
	//for loop going down
	for (int i = numberOfTimes; i>0; i--) {
	    System.out.println(i);
	    
    }
	Scanner input= new Scanner(System.in);
	do {
	    System.out.print("what is 2 +2?");
	}while (input.nextInt() !=4);
	
	}
}
 */
 


