
public class OlgaZiminaLabTest {

	public static void main(String[] args) {

		Video vid1 = new Video();

		Video vid2 = new Video("cats and cucumber", "Josh123", 2016, 500, 60, 1080);
		Video vid3 = new Video("tide pod challenge", "Amir345", 2017, 1200, 40, 720);

		System.out.println("Vid1 " + vid1.toString());
		System.out.println("Vid2 " + vid2.toString());
		System.out.println("Vid3 " + vid3.toString());

		System.out.println("This video2 has an age of " + vid2.getAge(2019) + " years");
		System.out.println("This video3 has an age of " + vid3.getAge(2019) + " years");
		System.out.println("This video2 is over 1 Gigabyte: " + vid2.overOneGigaByte());
		System.out.println("This video3 is over 1 Gigabyte: " + vid3.overOneGigaByte());
	}

}
