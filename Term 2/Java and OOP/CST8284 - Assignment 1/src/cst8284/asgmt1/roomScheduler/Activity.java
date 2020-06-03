/*
 * Course Name: 20W_CST8284_310 Object Oriented Programming (Java)
 * Student Name: Olga Zimina
 * Class Name: Activity
 * Date: Feb. 9, 2020
 */
package cst8284.asgmt1.roomScheduler;

public class Activity {
    private String description;
    private String category;

    public Activity(String description, String category) {
        this.description = description;
        this.category    = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("Event: %s\nDescription: %s", getCategory(), getDescription());
    }
}
