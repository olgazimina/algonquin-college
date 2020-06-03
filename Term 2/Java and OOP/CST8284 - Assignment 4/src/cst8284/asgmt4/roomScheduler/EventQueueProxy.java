package cst8284.asgmt4.roomScheduler;

import java.awt.*;

/**
 * This is to hide any console output like requires in Assignment 4
 * https://stackoverflow.com/questions/6827646/catch-exceptions-in-javax-swing-application
 */
public class EventQueueProxy extends EventQueue {

	/**
	 * Dispatches the events from superclasses and hide RunTimeExceptions
	 * @param newEvent new Action Event
	 */
	protected void dispatchEvent(AWTEvent newEvent) {
		try {
			super.dispatchEvent(newEvent);
		} catch (Throwable t) {
			// instead of doing any action here we can just simply pass it silently
			// hide the console output
			//t.printStackTrace();
		}
	}
}
