public class Password extends AbstractPassword {
	Password(String password) {
		super.setPassword(password);
	}

	public String first(String password) {
		if (!password.matches("[a-zA-Z]+")) {return "";}
		StringBuilder sb = new StringBuilder();

		for (char character : password.toCharArray()) {
			int ascii     = (int) character;
			int nextAscii = ascii + 1;

			if (nextAscii > 90 && nextAscii < 97) {
				nextAscii = 65;
			} else if (nextAscii > 122) {
				nextAscii = 97;
			}

			sb.append((char) ascii);
			sb.append((char) nextAscii);
		}
		return sb.toString();
	}

	public int second(String convertedPassword) {
		if (convertedPassword.isEmpty()) {
			System.out.println("Password may contain only letters! In this case password is not encrypted and its value is -1!");
			return -1;
		}
		int result = 0;
		for (char character : convertedPassword.toCharArray()) {
			result += (int) character;
		}
		return result;
	}
}
