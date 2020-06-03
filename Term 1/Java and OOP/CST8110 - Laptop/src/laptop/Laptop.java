package laptop;
import java.util.Scanner;

public class Laptop {
    private long   laptopId;
    private String laptopBrand;
    private double price, finalPrice, applicableTaxes;

    public Laptop() {
        this.laptopId        = 100101;
        this.laptopBrand     = "Acer";
        this.price           = 550;
        this.applicableTaxes = 17;
    }

    public Laptop(long laptopId, String laptopBrand, double price, double applicableTaxes) {
        this.laptopId        = laptopId;
        this.laptopBrand     = laptopBrand;
        this.price           = price;
        this.applicableTaxes = applicableTaxes;
    }

    public void readLaptopInfo() {
        Scanner input = new Scanner(System.in);
        System.out.print("Laptop ID: ");
        this.laptopId = input.nextLong();

        System.out.print("Laptop brand: ");
        this.laptopBrand = input.next();

        do {
            System.out.print("Price: ");
            this.price = input.nextDouble();
            if (this.price <= 0) {
                System.out.println("Price should be a positive value");
            }
        } while (this.price <= 0);

        System.out.print("Applicable taxes: ");
        this.applicableTaxes = input.nextDouble();
    }

    public void calculateFinalPrice() {
	this.finalPrice = this.price * this.applicableTaxes / 100 + this.price;
    }

    public void displayLaptopInfo() {
        System.out.printf("ID: %8d, Brand: %8s, Applicable Taxes: %3.0f%%, Final Price: $%8.2f\n",
            this.laptopId, this.laptopBrand, this.applicableTaxes, this.finalPrice);
    }
}
