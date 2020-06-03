/*
  @author Olga Zimina
  @course CST8284
  @date April 8, 2020
  @class RoomSchedulerDialog.java
  @version 1.0.0
*/

package cst8284.asgmt4.views;

import cst8284.asgmt4.roomScheduler.EventQueueProxy;
import cst8284.asgmt4.roomScheduler.RoomScheduler;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * class responcible for drawing main window
 */
public class RoomSchedulerDialog extends JFrame {
	private static final long      serialVersionUID = 1L;
	private final        JFrame    frame            = new JFrame();
	private final        Toolkit   tk               = Toolkit.getDefaultToolkit();
	private final        Dimension screenSize       = tk.getScreenSize();
	private static final JTextArea scrollText       = new JTextArea();

	public static final String
		DISPLAY_ROOM_INFORMATION = "Display Room Info",
		ENTER_ROOM_BOOKING       = "Enter a room booking",
		DELETE_BOOKING           = "Remove a room booking",
		CHANGE_BOOKING           = "Change a room booking",
		DISPLAY_BOOKING          = "Display a booking",
		DISPLAY_DAY_BOOKINGS     = "Display bookings for day",
		SAVE_BOOKINGS_TO_FILE    = "Backup current bookings to file",
		LOAD_BOOKINGS_FROM_FILE  = "Load current bookings from file",
		EXIT                     = "Exit program";


	private ArrayList<String> listOfStrings = null;
	private File              file          = null;

	/**
	 * This is the entry point to draw main dialog window, hide error messages
	 *
	 * @param roomScheduler RoomScheduler object to run main logic
	 */
	public void run(RoomScheduler roomScheduler) {
		// part of the https://stackoverflow.com/questions/6827646/catch-exceptions-in-javax-swing-application
		// to hide in console any RunTimeExceptions
		Toolkit.getDefaultToolkit().getSystemEventQueue().push(new EventQueueProxy());

		// start main window
		showMainWindow(roomScheduler);
	}

	/**
	 * Drawing the main window with Swing
	 *
	 * @param roomScheduler RoomScheduler object to run main logic
	 */
	private void showMainWindow(RoomScheduler roomScheduler) {
		frame.setTitle("Room Booking Service");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		int screenX = (int) screenSize.getWidth() / 3 * 2;
		int screenY = (int) (screenSize.getHeight() / 3 * 2);

		frame.add(getWestPanel(roomScheduler), BorderLayout.WEST);
		frame.add(getCenterPanel(scrollText, screenY), BorderLayout.CENTER);
		frame.setPreferredSize(new Dimension(screenX, screenY));

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * Adding central text area into main window
	 *
	 * @param textArea text area to be added
	 * @param height   size in pixels for text area
	 *
	 * @return JPanel text area initialized object
	 */
	public JPanel getCenterPanel(JTextArea textArea, int height) {
		JScrollPane centerPane = new JScrollPane(textArea);
		centerPane.setPreferredSize(new Dimension(500, 9 * height / 10));
		JPanel jPanel = new JPanel();
		jPanel.add(centerPane);
		return jPanel;
	}

	/**
	 * Adding panel with main buttons into the main window
	 *
	 * @param roomScheduler RoomScheduler object to run main logic
	 *
	 * @return JPanel text area initialized object
	 */
	public JPanel getWestPanel(RoomScheduler roomScheduler) {
		JPanel             controlPanel = new JPanel(new GridLayout(12, 1));
		JPanel             westPanel    = new JPanel(new GridBagLayout());
		GridBagConstraints gbc          = new GridBagConstraints();
		gbc.anchor  = GridBagConstraints.NORTH;
		gbc.weighty = 1;

		controlPanel.add(setWestPanelButtons(DISPLAY_ROOM_INFORMATION, (ActionEvent e) -> {
			updateTextArea(roomScheduler.executeMenuItem(1, null));
		}));
		controlPanel.add(setWestPanelButtons(ENTER_ROOM_BOOKING, (ActionEvent e) -> {
			new RoomBookingDialog().saveRoomBooking(roomScheduler);
		}));
		controlPanel.add(setWestPanelButtons(DELETE_BOOKING, (ActionEvent e) -> {
			new RoomBookingDialog().searchRoomBooking(roomScheduler, RoomSchedulerDialog.DELETE_BOOKING);
		}));
		controlPanel.add(setWestPanelButtons(CHANGE_BOOKING, (ActionEvent e) -> {
			new RoomBookingDialog().searchRoomBooking(roomScheduler, RoomSchedulerDialog.CHANGE_BOOKING);
		}));
		controlPanel.add(setWestPanelButtons(DISPLAY_BOOKING, (ActionEvent e) -> {
			new RoomBookingDialog().displayOneRoomBookings(roomScheduler);
		}));
		controlPanel.add(setWestPanelButtons(DISPLAY_DAY_BOOKINGS, (ActionEvent e) -> {
			new RoomBookingDialog().displayDayRoomBookings(roomScheduler);
		}));
		controlPanel.add(setWestPanelButtons(SAVE_BOOKINGS_TO_FILE, (ActionEvent e) -> {
			new RoomBookingDialog().saveRoomBookings(roomScheduler, getFileFromUser(true));
		}));
		controlPanel.add(setWestPanelButtons(LOAD_BOOKINGS_FROM_FILE, (ActionEvent e) -> {
			new RoomBookingDialog().loadRoomBookings(roomScheduler, getFileFromUser(false));
		}));
		controlPanel.add(setWestPanelButtons(EXIT, (ActionEvent e) -> {
			new RoomBookingDialog().exitRoomBookings(roomScheduler, frame);
		}));

		JTextArea tf = new JTextArea("CST8284 - Assignment 4\nOlga Zimina\nstudent #1");
		tf.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		controlPanel.add(tf);

		westPanel.add(controlPanel, gbc);
		return westPanel;
	}

	/**
	 * Instantiate main window buttons
	 *
	 * @param label          text to draw on buttons
	 * @param actionListener action which is triggered by pressing on buttons
	 *
	 * @return initialized button
	 */
	private JButton setWestPanelButtons(String label, ActionListener actionListener) {
		final Font font   = new Font("SansSerif", Font.PLAIN, 20);
		JButton    button = new JButton(label);
		button.setFont(font);
		button.addActionListener(actionListener);
		return button;
	}

	/**
	 * Dialog window to choose file for reading writing backup data
	 * Adapted from: https://www.mkyong.com/swing/java-swing-jfilechooser-example/
	 *
	 * @param readWrite flag showing current operation - read or write
	 *
	 * @return String object with full filename
	 */
	private String getFileFromUser(boolean readWrite) {
		File         f  = null;
		JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		fc.setFileFilter(new FileNameExtensionFilter(".book Files", "book"));
		do {
			int returnValue = readWrite ? fc.showSaveDialog(null) : fc.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				f = fc.getSelectedFile();
				return f.getAbsolutePath();
			} else if (returnValue == JFileChooser.CANCEL_OPTION) { return null; }
		} while (true);
	}

	/**
	 * Draw test in main window text area
	 *
	 * @param text text to draw
	 */
	public static void updateTextArea(String text) {
		scrollText.setText(text);
	}
}
