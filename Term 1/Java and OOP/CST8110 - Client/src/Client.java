import java.util.Scanner;

public class Client {
    private String name;
    private int    clientNumber;
    Scanner input = new Scanner(System.in);

    public Client() {
        this("unassigned", -1);
    }

    public Client(String name, int clientNumber) {

    }

    public void keyboardClientInfo() {
        System.out.print("Client Name: ");
        this.name = input.next();

        System.out.print("Client Number: ");
        this.clientNumber = input.nextInt();

        System.out.println();
    }

    public void displayClientInfo() {
        System.out.printf("Client Name: %10s, Client Number: %5d\n", this.name, this.clientNumber);
    }
}
