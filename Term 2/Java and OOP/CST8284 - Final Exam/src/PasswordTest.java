import java.util.Scanner;

/**
 * @author Zimina Olga
 * @student 
 */
public class PasswordTest {

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.print("Please enter the password: ");
		System.out.printf("Encrypted password is: %d\n", doEncryption(new Password(scanner.nextLine())));
	}

	private static int doEncryption(Password passwordObject) {
		return passwordObject.second(passwordObject.first(passwordObject.getPassword()));
	}
}
