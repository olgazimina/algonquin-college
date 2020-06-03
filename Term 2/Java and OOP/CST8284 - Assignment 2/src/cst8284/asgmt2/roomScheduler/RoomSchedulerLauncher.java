package cst8284.asgmt2.roomScheduler;
/*  Course Name: CST8284
    Author: Olga Zimina
    Class name: RoomSchedulerLauncher.java
    Date: February 28, 2020
*/

import cst8284.asgmt2.room.ComputerLab;

public class RoomSchedulerLauncher {

	public static void main(String[] args) {
		ComputerLab computerLab = new ComputerLab();
		computerLab.setRoomNumber("B119");
		(new RoomScheduler(computerLab)).launch();
	}
}
