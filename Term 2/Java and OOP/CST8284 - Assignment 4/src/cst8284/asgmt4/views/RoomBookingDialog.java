/*
  @author Olga Zimina
  @course CST8284
  @date April 8, 2020
  @class RoomBookingDialog.java
  @version 1.0.0
*/

package cst8284.asgmt4.views;

import cst8284.asgmt4.roomScheduler.BookingInput;
import cst8284.asgmt4.roomScheduler.RoomBooking;
import cst8284.asgmt4.roomScheduler.RoomScheduler;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

/* Adapted, with considerable modification, from
 * http://www.java2s.com/Code/Java/Swing-JFC/TextAcceleratorExample.htm,
 * which is sloppy code and should not be emulated.
 */

/**
 * Class responsible for main dialog window and its behaviour
 */
public class RoomBookingDialog extends JFrame {

	private final GridBagConstraints labelConstraints = new GridBagConstraints(
		0, GridBagConstraints.RELATIVE, 1, 1, 1, 1,
		GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 20), 0, 0);
	private final GridBagConstraints textConstraints  = new GridBagConstraints(
		1, GridBagConstraints.RELATIVE, 1, 1, 1, 1,  // gridx, gridy, gridwidth, gridheight, weightx, weighty
		GridBagConstraints.EAST, 0, new Insets(5, 5, 5, 10), 20, 20); // anchor, fill, insets, ipadx, ipady
	private final GridBagConstraints btnConstraints   = new GridBagConstraints(
		0, GridBagConstraints.RELATIVE, 2, 1, 0.5, 0.5,
		GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 20), 0, 0);

	private final int    labelWidth  = 24;
	private final Font   defaultFont = new Font("SansSerif", Font.PLAIN, 20);
	private final JFrame frame       = new JFrame();
	private final JPanel btnPanel    = new JPanel();

	private Panel                pane                = new Panel();
	private BookingInput         bookingInput        = new BookingInput();
	private Map<String, Boolean> windowVisibilitySet = new HashMap<>();

	private static final String
		BTN_SEARCH      = "Seacrh",
		BTN_SAVE        = "Save",
		BTN_DELETE      = "Delete",
		BTN_EXIT        = "Exit",
		FLD_DATE        = "Booking Date (DDMMYYY):",
		FLD_STIME       = "Booking Time (2 p.m. or 14:00):",
		FLD_ETIME       = "Booking Time (3 p.m. or 15:00):",
		FLD_PHONE       = "Phone Number (e.g. 613-555-1212):",
		FLD_ORG         = "Organization (optional):",
		FLD_CATEGORY    = "Event Category:",
		FLD_DESCRIPTION = "Description:",
		FLD_NAME        = "Client Name (FirstName LastName):";

	/**
	 * Getter to obtain pane Object
	 *
	 * @return Pane Object
	 */
	public Panel getPane() {
		return pane;
	}

	/**
	 * Obtain user entered data with getter
	 *
	 * @return bookingInput object with input data
	 */
	public BookingInput getBookingInput() {
		return bookingInput;
	}

	/**
	 * Get information about all elements disabled/enabled status to draw
	 *
	 * @return map with all elements
	 */
	public Map<String, Boolean> getWindowVisibilitySet() {
		return windowVisibilitySet;
	}

	/**
	 * Get information about different elements disabled/enabled status to draw
	 *
	 * @param key Define which element to retrieve
	 *
	 * @return boolean status
	 */
	public boolean getWindowVisibility(String key) {
		return getWindowVisibilitySet().containsKey(key) ? getWindowVisibilitySet().get(key) : true;
	}

	/**
	 * Set information about different elements disabled/enabled status to draw
	 *
	 * @param string Define which element to set visibility
	 * @param flag   visibility flag - true enabled, false - disabled
	 */
	public void setWindowVisibility(String string, Boolean flag) {
		this.getWindowVisibilitySet().put(string, flag);
	}

	/**
	 * Getter to get panel with buttons
	 *
	 * @return Object panel with buttons
	 */
	public JPanel getBtnPanel() {
		return btnPanel;
	}

	/**
	 * Show main interaction window dialog
	 *
	 * @param roomScheduler RoomScheduler object to run main logic
	 * @param header        Header to show as a title
	 * @param roomBooking   if set it will be filled in - used fo changing and search
	 */
	public void showModalWindow(RoomScheduler roomScheduler, String header, RoomBooking roomBooking) {
		int    saveChoice  = 2;
		String saveMessage = "Booking time and date saved";
		String saveError   = "Cannot save booking; that time is already booked";
		frame.setTitle(header);
		getPane().setLayout(new GridBagLayout());

		// Set the first seven rows with Label/TextField
		JTextField eventDate         = setRow(FLD_DATE, 'e');
		JTextField eventSTime        = setRow(FLD_STIME, 's');
		JTextField eventETime        = setRow(FLD_ETIME, 'f');
		JTextField eventClient       = setRow(FLD_NAME, 'n');
		JTextField eventPhoneNum     = setRow(FLD_PHONE, 'p');
		JTextField eventOrganization = setRow(FLD_ORG, 'o');
		JTextField eventCategory     = setRow(FLD_CATEGORY, 'c');
		JTextField eventDescription  = setRow(FLD_DESCRIPTION, 'd');

		if (roomBooking != null) {
			String dayOfMonth = roomBooking.getTimeBlock().getStartTime().get(Calendar.DAY_OF_MONTH) < 10 ? 
					"0" + String.valueOf(roomBooking.getTimeBlock().getStartTime().get(Calendar.DAY_OF_MONTH)) :
					String.valueOf(roomBooking.getTimeBlock().getStartTime().get(Calendar.DAY_OF_MONTH));
			String month = roomBooking.getTimeBlock().getStartTime().get(Calendar.MONTH) < 9 ? 
					"0" + String.valueOf(roomBooking.getTimeBlock().getStartTime().get(Calendar.MONTH) + 1) :
					String.valueOf(roomBooking.getTimeBlock().getStartTime().get(Calendar.MONTH) + 1);
			String year = String.valueOf(roomBooking.getTimeBlock().getStartTime().get(Calendar.YEAR));

			eventDate.setText(dayOfMonth + month + year);
			eventSTime.setText(String.valueOf(roomBooking.getTimeBlock().getStartTime().get(Calendar.HOUR_OF_DAY)) + ":00");
			eventETime.setText(String.valueOf(roomBooking.getTimeBlock().getEndTime().get(Calendar.HOUR_OF_DAY)) + ":00");
			eventClient.setText(roomBooking.getContactInfo().getFirstName() + " " + roomBooking.getContactInfo().getLastName());
			eventPhoneNum.setText(roomBooking.getContactInfo().getPhoneNumber());
			eventOrganization.setText(roomBooking.getContactInfo().getOrganization());
			eventCategory.setText(roomBooking.getActivity().getCategory());
			eventDescription.setText(roomBooking.getActivity().getDescription());

			getBookingInput().setOldEventSTime(eventSTime.getText());
			getBookingInput().setOldEventDate(eventDate.getText());
			saveChoice  = 4;
			saveMessage = "Booking time and date updated";
			saveError   = "Cannot update booking; that time is already booked";
		}

		// Load the buttons across the bottom
		getBtnPanel().setLayout(new FlowLayout());
		setBtnRow(BTN_SEARCH, 'r', (ActionEvent e) -> {
			getBookingInput().setEventDate(eventDate.getText());
			getBookingInput().setEventSTime(eventSTime.getText());

			if (header.equalsIgnoreCase(RoomSchedulerDialog.CHANGE_BOOKING)) {
				RoomBooking rb = searchAndEditBooking(roomScheduler);
				if (rb != null) {
					clearWindows();
					updateRoomBooking(roomScheduler, rb);
				}
			} else if (header.equalsIgnoreCase(RoomSchedulerDialog.DELETE_BOOKING)) {
				RoomBooking rb = searchAndEditBooking(roomScheduler);
				if (rb != null) {
					clearWindows();
					deleteRoomBooking(roomScheduler, rb);
				}
			} else if (header.equalsIgnoreCase(RoomSchedulerDialog.DISPLAY_DAY_BOOKINGS)) {
				clearWindows();
				runAction(roomScheduler, 6, JOptionPane.INFORMATION_MESSAGE, "", "");
			} else if (header.equalsIgnoreCase(RoomSchedulerDialog.DISPLAY_BOOKING)) {
				clearWindows();
				runAction(roomScheduler, 5, JOptionPane.INFORMATION_MESSAGE, "", "");
			}
		});

		int    finalSaveChoice  = saveChoice;
		String finalSaveMessage = saveMessage;
		String finalSaveError   = saveError;
		setBtnRow(BTN_SAVE, 's', e -> {
			getBookingInput().setEventDate(eventDate.getText());
			getBookingInput().setEventSTime(eventSTime.getText());
			getBookingInput().setEventETime(eventETime.getText());
			getBookingInput().setEventClient(eventClient.getText());
			getBookingInput().setEventPhoneNum(eventPhoneNum.getText());
			getBookingInput().setEventOrganization(eventOrganization.getText());
			getBookingInput().setEventCategory(eventCategory.getText());
			getBookingInput().setEventDescription(eventDescription.getText());
			if (confirmationDialog("Add record", "Are you sure to save the record?") == 0) {
				runAction(roomScheduler, finalSaveChoice, JOptionPane.INFORMATION_MESSAGE, finalSaveMessage, finalSaveError);
			}
		});

		setBtnRow(BTN_EXIT, 'x', (ActionEvent e) -> frame.dispose());

		getPane().add(btnPanel, btnConstraints);
		frame.add(pane);
		frame.pack();

		// Close dialog
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			}
		});

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * Initialize each text field  with font, size, etc.
	 *
	 * @param label            text to label the field
	 * @param keyboardShortcut shortcut to use
	 *
	 * @return initialized text field
	 */
	private JTextField setRow(String label, char keyboardShortcut) {
		JLabel     jLabel;
		JTextField textField;

		getPane().add(jLabel = new JLabel(label, SwingConstants.RIGHT), labelConstraints);
		jLabel.setFont(defaultFont);
		jLabel.setDisplayedMnemonic(keyboardShortcut);
		jLabel.setEnabled(getWindowVisibility(label));

		getPane().add(textField = new JTextField(labelWidth), textConstraints);
		textField.setFont(defaultFont);
		textField.setFocusAccelerator(keyboardShortcut);
		textField.setEnabled(getWindowVisibility(label));

		return textField;
	}

	/**
	 * Initialize buttons with text, size, action listeners
	 *
	 * @param label            text to write on buttons
	 * @param keyboardShortcut shortcut to use
	 * @param action           action event to be executed when button is pressed/clicked
	 *
	 * @return initialized button
	 */
	private JButton setBtnRow(String label, char keyboardShortcut, ActionListener action) {
		JButton button = new JButton(label);

		button.setFont(defaultFont);
		button.setEnabled(getWindowVisibility(label));
		button.setMnemonic(keyboardShortcut);
		button.addActionListener(action);

		getBtnPanel().add(button);

		return button;
	}

	/**
	 * Used to modify previously found data and save it
	 *
	 * @param roomScheduler RoomScheduler object to run main logic
	 * @param roomBooking   room booking object to draw in dialog window
	 */
	public void updateRoomBooking(RoomScheduler roomScheduler, RoomBooking roomBooking) {
		setWindowVisibility(BTN_SEARCH, false);
		setWindowVisibility(BTN_DELETE, false);
		showModalWindow(roomScheduler, RoomSchedulerDialog.ENTER_ROOM_BOOKING, roomBooking);
	}

	/**
	 * Save new/existing booking into the ArrayList
	 *
	 * @param roomScheduler RoomScheduler object to run main logic
	 */
	public void saveRoomBooking(RoomScheduler roomScheduler) {
		setWindowVisibility(BTN_SEARCH, false);
		setWindowVisibility(BTN_DELETE, false);
		showModalWindow(roomScheduler, RoomSchedulerDialog.ENTER_ROOM_BOOKING, null);
	}

	/**
	 * Delete existing booking from ArrayList
	 *
	 * @param roomScheduler RoomScheduler object to run main logic
	 * @param roomBooking   room booking object to delete
	 */
	public void deleteRoomBooking(RoomScheduler roomScheduler, RoomBooking roomBooking) {
		if (confirmationDialog("Delete record", "Are you sure to delete record?\n" + roomBooking.toString()) == 0) {
			runAction(roomScheduler, 3, JOptionPane.INFORMATION_MESSAGE, "Booking successfully deleted", "Booking has not been deleted");
		} else {
			frame.dispose();
		}
	}

	/**
	 * Draw dialog window for searching room booking
	 *
	 * @param roomScheduler RoomScheduler object to run main logic
	 * @param type          type of the action
	 */
	public void searchRoomBooking(RoomScheduler roomScheduler, String type) {
		setWindowVisibility(BTN_DELETE, false);
		setWindowVisibility(BTN_SAVE, false);
		setWindowVisibility(FLD_ETIME, false);
		setWindowVisibility(FLD_CATEGORY, false);
		setWindowVisibility(FLD_DESCRIPTION, false);
		setWindowVisibility(FLD_NAME, false);
		setWindowVisibility(FLD_ORG, false);
		setWindowVisibility(FLD_PHONE, false);
		showModalWindow(roomScheduler, type, null);
	}

	/**
	 * Draw dialog window for searching and displaying bookings for day
	 *
	 * @param roomScheduler RoomScheduler object to run main logic
	 */
	public void displayDayRoomBookings(RoomScheduler roomScheduler) {
		setWindowVisibility(BTN_DELETE, false);
		setWindowVisibility(BTN_SAVE, false);
		setWindowVisibility(FLD_STIME, false);
		setWindowVisibility(FLD_ETIME, false);
		setWindowVisibility(FLD_CATEGORY, false);
		setWindowVisibility(FLD_DESCRIPTION, false);
		setWindowVisibility(FLD_NAME, false);
		setWindowVisibility(FLD_ORG, false);
		setWindowVisibility(FLD_PHONE, false);
		showModalWindow(roomScheduler, RoomSchedulerDialog.DISPLAY_DAY_BOOKINGS, null);
	}

	/**
	 * Draw dialog window for searching and showing one particular booking
	 *
	 * @param roomScheduler RoomScheduler object to run main logic
	 */
	public void displayOneRoomBookings(RoomScheduler roomScheduler) {
		setWindowVisibility(BTN_DELETE, false);
		setWindowVisibility(BTN_SAVE, false);
		setWindowVisibility(FLD_ETIME, false);
		setWindowVisibility(FLD_CATEGORY, false);
		setWindowVisibility(FLD_DESCRIPTION, false);
		setWindowVisibility(FLD_NAME, false);
		setWindowVisibility(FLD_ORG, false);
		setWindowVisibility(FLD_PHONE, false);
		showModalWindow(roomScheduler, RoomSchedulerDialog.DISPLAY_BOOKING, null);
	}

	/**
	 * Action trigger to run RoomScheduler object to save booking
	 *
	 * @param roomScheduler RoomScheduler object to run main logic
	 * @param fileFromUser  absolute path for filename to save
	 */
	public void saveRoomBookings(RoomScheduler roomScheduler, String fileFromUser) {
		if (fileFromUser == null) { return; }
		if (!fileFromUser.endsWith(".book")) {fileFromUser += ".book";}
		bookingInput.setFile(fileFromUser);
		runAction(roomScheduler, 7, JOptionPane.INFORMATION_MESSAGE, "Current room bookings backed up to file",
			"Bookings cannot be saved to file");
	}

	/**
	 * Action trigger to run RoomScheduler object to load data from booking file
	 *
	 * @param roomScheduler RoomScheduler object to run main logic
	 * @param fileFromUser  absolute path for filename from user
	 */
	public void loadRoomBookings(RoomScheduler roomScheduler, String fileFromUser) {
		if (fileFromUser == null) { return; }
		bookingInput.setFile(fileFromUser);
		runAction(roomScheduler, 8, JOptionPane.INFORMATION_MESSAGE, "Loaded from file successfully",
			"There is no bookings in the file or file is missing/corrupted");
	}

	/**
	 * Action trigger to exit from application and save data to file silently
	 *
	 * @param roomScheduler RoomScheduler object to run main logic
	 * @param jFrame        frame of the main window (not this one)
	 */
	public void exitRoomBookings(RoomScheduler roomScheduler, JFrame jFrame) {
		if (confirmationDialog("Exit", "Are you sure want to exit?") == 0) {
			bookingInput.setFile("CurrentRoomBookings.book");
			runAction(roomScheduler, 0, JOptionPane.INFORMATION_MESSAGE, "", "");
			jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));
		}
	}

	/**
	 * Action trigger to search any booking in RoomScheduler
	 *
	 * @param roomScheduler RoomScheduler object to run main logic
	 *
	 * @return roomBooking or null if not found
	 */
	private RoomBooking searchAndEditBooking(RoomScheduler roomScheduler) {
		RoomBooking roomBooking = roomScheduler.findBooking(roomScheduler.makeCalendarFromUserInput(null, true, bookingInput));
		if (roomBooking == null) {
			JFrame jFrame = new JFrame();
			getPane().setLayout(new GridBagLayout());
			JOptionPane.showMessageDialog(jFrame, "Booking not found", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return roomBooking;
	}

	/**
	 * Action trigger for RoomScheduler executeMenu() method
	 *
	 * @param roomScheduler  RoomScheduler object to run main logic
	 * @param menuChoice     defines which action to run in executeMenu()
	 * @param type           type of the warning/error modal window to show
	 * @param successMessage String with data to show in success case
	 * @param errorMessage   String with data to show in error case
	 */
	private void runAction(RoomScheduler roomScheduler, int menuChoice, int type, String successMessage, String errorMessage) {
		if (successMessage.isEmpty() && errorMessage.isEmpty()) {
			RoomSchedulerDialog.updateTextArea(roomScheduler.executeMenuItem(menuChoice, bookingInput));
			return;
		}
		if (!roomScheduler.executeMenuItem(menuChoice, bookingInput).equalsIgnoreCase(errorMessage)) {
			infoDialog("Status", successMessage, type);
			frame.dispose();
		} else {
			infoDialog("Error", errorMessage, JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Draw modal confirmation window
	 *
	 * @param title   window title
	 * @param message window message
	 *
	 * @return 1 in case of yes, 0 in case of no
	 */
	private int confirmationDialog(String title, String message) {
		JFrame jFrame = new JFrame();
		getPane().setLayout(new GridBagLayout());
		return JOptionPane.showConfirmDialog(jFrame, message, title, JOptionPane.YES_OPTION);
	}

	/**
	 * Draw modal window with status information
	 *
	 * @param title   window title
	 * @param message window message
	 * @param type    type for error message window
	 */
	private void infoDialog(String title, String message, int type) {
		JFrame jFrame = new JFrame();
		getPane().setLayout(new GridBagLayout());
		JOptionPane.showMessageDialog(jFrame, message, title, type);
	}

	/**
	 * Reusable code to cleanup windows
	 */
	private void clearWindows() {
		frame.dispose();
		getPane().removeAll();
		getBtnPanel().removeAll();
		getWindowVisibilitySet().clear();
	}
}
