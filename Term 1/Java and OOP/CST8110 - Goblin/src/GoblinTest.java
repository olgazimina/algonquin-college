
public class GoblinTest {
    public static void main(String[] args) {
        System.out.println("Program developed by Olga Zimina");

        Goblin goblin1 = new Goblin();
        Goblin goblin2 = new Goblin(7892, "Blinky", 53, 87);
        Goblin goblin3 = new Goblin();
        goblin3.inputGoblinDetails();

        goblin1.calculateGold();
        goblin2.calculateGold();
        goblin3.calculateGold();

        goblin1.print();
        goblin2.print();
        goblin3.print();
    }
}