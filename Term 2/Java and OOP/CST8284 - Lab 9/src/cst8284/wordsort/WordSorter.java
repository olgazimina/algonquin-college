package cst8284.wordsort;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class WordSorter extends JFrame {

	private static final long      serialVersionUID = 1L;
	private static final Toolkit   tk               = Toolkit.getDefaultToolkit();
	private static final Dimension screenSize       = tk.getScreenSize();
	private static final JTextArea scrollText       = new JTextArea();

	private static ArrayList<String> listOfStrings = null;
	private static File              file          = null;

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				loadAndShowWords();
			}
		});
	}

	private static void loadAndShowWords() {
		JFrame frame = new JFrame();
		frame.setTitle("Word Sorter");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		int screenX = (int) screenSize.getWidth() / 2;
		int screenY = (int) (7 * screenSize.getHeight() / 8);
		
		/*Uncomment the following code when you have finished TODO #1 and test your code.
		  Make sure the word list appears in the scroll pane.  Then remove this comment.
		*/
		file          = getFileFromUser("");
		listOfStrings = FileIO.loadArrayListFromFile(file);
		if (listOfStrings != null) {
			reloadJTextArea();
		}
		frame.add(getWestPanel(), BorderLayout.WEST);
		frame.add(getCenterPanel(scrollText, screenY), BorderLayout.CENTER);
		frame.setPreferredSize(new Dimension(screenX, screenY));

		frame.pack();
		frame.setVisible(true);

	}

	public static JPanel getCenterPanel(JTextArea jta, int height) {
		JScrollPane centerPane = new JScrollPane(jta);
		centerPane.setPreferredSize(new Dimension(400, 7 * height / 8));
		JPanel jp = new JPanel();
		jp.add(centerPane);
		return jp;
	}

	public static JPanel getWestPanel() {
		JPanel             controlPanel = new JPanel(new GridLayout(6, 1));
		JPanel             westPanel    = new JPanel(new GridBagLayout());
		GridBagConstraints gbc          = new GridBagConstraints();
		gbc.anchor  = GridBagConstraints.NORTH;
		gbc.weighty = 1;

		controlPanel.add(setWestPanelBtns(" Sort List Alphabetically ", new SortListAlphabetically()));
		controlPanel.add(setWestPanelBtns("    Reverse List Order    ", new ReverseListOrder()));
		controlPanel.add(setWestPanelBtns("   Sort By String Length  ", new SortByStringLength()));
		controlPanel.add(setWestPanelBtns("      Randomize List      ", new RandomizeList()));

		JTextArea tf = new JTextArea(FileIO.toStringFileIO(file));
		tf.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		controlPanel.add(tf);

		westPanel.add(controlPanel, gbc);
		return westPanel;
	}

	private static JButton setWestPanelBtns(String btnLabel, ActionListener act) {
		final Font font = new Font("SansSerif", Font.PLAIN, 20);
		JButton    btn  = new JButton(btnLabel);
		btn.setFont(font);
		btn.addActionListener(act);
		return btn;
	}

	// Adapted from: https://www.mkyong.com/swing/java-swing-jfilechooser-example/
	private static File getFileFromUser(String fileName) {
		File         f  = null;
		JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		fc.setFileFilter(new FileNameExtensionFilter(".txt Files", "txt"));
		do {
			int returnValue = fc.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				f = fc.getSelectedFile();
				if (FileIO.fileExists(f)) { return f; }
			} else if (returnValue == JFileChooser.CANCEL_OPTION) { return null; }
		} while (true);
	}

	public static ArrayList<String> getListOfStrings() {
		return listOfStrings;
	}

	public static void reloadJTextArea() {
		scrollText.setText(FileIO.toStringFromArrayList(getListOfStrings()));
	}
}
