package cst8284.wordsort;

import java.util.Comparator;

public class StringLenghtCompator implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		return Integer.compare(o1.length() - o2.length(), 0);
	}
}
