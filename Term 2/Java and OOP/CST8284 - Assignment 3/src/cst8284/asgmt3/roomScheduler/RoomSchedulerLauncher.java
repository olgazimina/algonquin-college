/*
  @author Olga Zimina
  @course CST8284
  @date March, 26 2020
  @class RoomSchedulerLauncher.java
  @version 1.0.0
*/

package cst8284.asgmt3.roomScheduler;


import cst8284.asgmt3.room.ComputerLab;

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
		ComputerLab computerLab = new ComputerLab();
		computerLab.setRoomNumber("B119");
		(new RoomScheduler(computerLab)).launch();
	}
}
