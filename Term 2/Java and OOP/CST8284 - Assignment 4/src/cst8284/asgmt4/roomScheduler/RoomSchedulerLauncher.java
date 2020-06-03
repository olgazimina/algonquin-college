/*
  @author Olga Zimina
  @course CST8284
  @date March, 26 2020
  @class RoomSchedulerLauncher.java
  @version 2.0.0
*/

package cst8284.asgmt4.roomScheduler;


import cst8284.asgmt4.room.ComputerLab;
import cst8284.asgmt4.views.RoomSchedulerDialog;

/**
 * Main class to launch Application
 */
public class RoomSchedulerLauncher {

	/**
	 * Main method to start
	 *
	 * @param args array of arguments
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater (new Runnable() {
			public void run() {
				new RoomScheduler(new ComputerLab("B119")).launch();
			} });
	}
}
