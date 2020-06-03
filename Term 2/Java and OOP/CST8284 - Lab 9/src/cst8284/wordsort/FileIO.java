package cst8284.wordsort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class FileIO {
	private static int numberOfStringsLoaded;

	public static ArrayList<String> loadArrayListFromFile(File f) {
		ArrayList<String> list = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(f);
			while (scanner.hasNext()) {
				list.add(scanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		numberOfStringsLoaded = list.size();
		return list;
	}

	public static boolean fileExists(File f) {
		return f != null &&
		       f.exists() &&
		       !f.isDirectory() &&
		       f.canRead();
	}

	public static String toStringFromArrayList(ArrayList<String> arStr) {
		String res = "";
		for (String str : arStr) {
			res += str + "\n";
		}
		return res;
	}

	public static String toStringFileIO(File f) {
		return "Name: " + f.getName() + "\n" +
		       "Size: " + String.valueOf(f.length()) + " Bytes\n" +
		       "Modified: " + new Date((long)f.lastModified()).toString() + "\n" +
		       "Strings: " + String.valueOf(numberOfStringsLoaded);
	}
}
