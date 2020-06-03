import java.util.Scanner;

public class Goblin {
    private int    goblinNumber;
    private String name;
    private double gold, cleverness, greed;
    private Scanner input = new Scanner(System.in);

    public Goblin() {
        this.goblinNumber = -1;
        this.name         = "unknown";
        this.gold         = 0;
        this.greed        = 0;
    }

    public Goblin(int goblinNumber, String name, double cleverness, double greed) {
        this.goblinNumber = goblinNumber;
        this.name         = name;
        this.cleverness   = cleverness;
        this.greed        = greed;
    }

    public void inputGoblinDetails() {
        System.out.print("Goblin Number: ");
        this.goblinNumber = this.input.nextInt();

        while(this.goblinNumber < 0 || this.goblinNumber > 100) {
            System.out.println("Please enter the number which is more than 1 and less than 100");
            this.goblinNumber = this.input.nextInt();
        }

        System.out.print("Goblin Name: ");
        this.name = this.input.next();


        System.out.print("Goblin Cleverness: ");
        this.cleverness = this.input.nextDouble();

        while(this.cleverness < 0 || this.cleverness > 100) {
            System.out.println("Please enter the number which is more than 1 and less than 100");
            this.cleverness = this.input.nextDouble();
        }

        System.out.print("Goblin Greed: ");
        this.greed = this.input.nextDouble();

        while(this.greed < 0 || this.greed > 100) {
            System.out.println("Please enter the number which is more than 1 and less than 100");
            this.greed = this.input.nextDouble();
        }
    }

	public void calculateGold() {
	    this.gold = (this.greed - 1) * ((this.cleverness - 1) / 1000);
	}
    

    public void print() {
        String message = "Goblin#:%5d Name:%10s Cleverness:%8.2f Greed:%8.2f Gold:%8.2f ";
        if (this.gold< 1) {
            message += "poor goblin";
        } else if(this.gold > 1 && this.gold < 6) {
            message += "promising goblin";
        } else if (this.gold > 6){
            message += "this goblin will go far";
        } else {
            message += "not sure about this goblin";
        }
        System.out.println(String.format(message, this.goblinNumber, this.name, this.cleverness, this.greed, this.gold));
    }
}

