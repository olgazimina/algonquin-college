
public class OlgaZiminaLabTest {

	public static void main(String[] args) {
		Car car1 = new Car();
		Car car2 = new Car("A", "Honda Civic", "Red", "12341234ABDSDS", 5000);
		Car car3 = new Car("A", "Toyota Camry","Blue", "ASDAS3DG3TFRDF", 11000);
		

		System.out.println("This car2 has an age of " + car2.getAge(2019) + " years");
		System.out.println("This car3 has an age of " + car3.getAge(2019) + " years");
		System.out.println("This car2 is over 10000$: " + car2.overTenTousand());
		System.out.println("This car3 is over 10000$: " + car3.overTenTousand());
	}

}
