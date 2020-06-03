package cst8284.asgmt2.roomScheduler;
/*  Course Name: CST8284
    Author: Olga Zimina
    Class name: Activity.java
    Date: February 28, 2020
    Few methods are borrowed from original source code provided by prof. D. Houtman.
*/

import java.io.Serializable;

public class Activity implements Serializable {
	private String category, description;
	
	public Activity(String category, String description) {
		setCategory(category); setDescription(description);	
	}
	
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	
	public String getCategory() {return category;}
	public void setCategory(String category) {this.category = category;}
	
	@Override
	public String toString() {
		return  "Event: " + getCategory() + "\n" + 
			((getDescription()!="")?"Description: " + getDescription():"") + "\n";
	}
}
