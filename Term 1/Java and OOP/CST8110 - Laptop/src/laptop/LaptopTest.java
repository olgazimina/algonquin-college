package laptop;
public class LaptopTest {
    public static void main(String[] args) {
        System.out.println("Program developed by Olga Zimina");

        Laptop laptop1 = new Laptop();
        Laptop laptop2 = new Laptop(100103, "Apple", 1200, 7);
        Laptop laptop3 = new Laptop();

        System.out.println("Details of laptop 3");
        laptop3.readLaptopInfo();

        System.out.println("Details:");
        System.out.println("========");

        laptop1.calculateFinalPrice();
        laptop1.displayLaptopInfo();

        laptop2.calculateFinalPrice();
        laptop2.displayLaptopInfo();

        laptop3.calculateFinalPrice();
        laptop3.displayLaptopInfo();
    }
}