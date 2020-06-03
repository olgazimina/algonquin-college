package cst8284.wordsort;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;

public class SortByStringLength implements ActionListener {

	private static int compare(String o1, String o2) {return Integer.compare(o1.length() - o2.length(), 0);}

	@Override
	public void actionPerformed(ActionEvent e) {
		// here might be lambda or method reference
		Comparator<String> comparator = SortByStringLength::compare;

		Collections.sort(WordSorter.getListOfStrings(), comparator);
		WordSorter.reloadJTextArea();
	}
}
