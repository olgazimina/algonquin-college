package lab5;

import java.util.Scanner;

public class Count {

    private int stepSize;
    private int startNum;

    private Scanner scanner = new Scanner(System.in);

    Count() {
        new Count(0, 0);
    }

    Count(int stepSize, int startNum) {
        this.startNum = startNum;
        this.stepSize = stepSize;
    }

    public void getValuesFromUser() {
        System.out.print("Enter the start value: ");
        this.startNum = scanner.nextInt();

        while (this.startNum <= 0) {
            System.out.println("Sorry your value should be more than 0!");
            System.out.print("Enter the start value: ");
            this.startNum = scanner.nextInt();
        }

        System.out.print("Enter the countdown step size: ");
        this.stepSize = scanner.nextInt();

        while (this.stepSize <= 0) {
            System.out.println("Sorry your value should be more than 0!");
            System.out.print("Enter the countdown step size: ");
            this.stepSize = scanner.nextInt();
        }
    }

    public void displaySteps() {
        System.out.println("The numbers are:");
        for (int i = this.startNum; i > 0; i = i - this.stepSize) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}        