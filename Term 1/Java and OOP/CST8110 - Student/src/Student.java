import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;

public class Student {
    private long studentNumber;
    private String name;
    private double course1Marks;
    private double course2Marks;
    private double course3Marks;
    private double gpa;
    Scanner input = new Scanner(System.in);

    public Student() {
	
	double x = Math.random();
	
	SecureRandom sr = new SecureRandom();
	double y = sr.nextDouble();
	
	Random rnd = new Random();
	double z = rnd.nextDouble();
	
	this.studentNumber = 0;
	this.name = "NoSet";
	this.course1Marks = 0;
	this.course2Marks = 0;
	this.course3Marks = 0;
	this.gpa = 0;
    }

    public Student(long studentNumber, String name, double course1Marks, double course2Marks, double course3Marks) {
	this.studentNumber = studentNumber;
	this.name = name;
	this.course1Marks = course1Marks;
	this.course2Marks = course2Marks;
	this.course3Marks = course3Marks;
    }

    public void getInfo() {
	System.out.print("Enter Student number: ");
	this.studentNumber = input.nextLong();
	System.out.print("Enter Name: ");
	this.name = input.next();
	System.out.print("Enter course1 marks: ");
	this.course1Marks = input.nextDouble();
	System.out.print("Enter course2 marks: ");
	this.course2Marks = input.nextDouble();
	System.out.print("Enter course3 marks: ");
	this.course3Marks = input.nextDouble();
    }

    public void calculateGPA() {
	this.gpa = ((course1Marks + course2Marks + course3Marks) / 300) * 4;
    }

    public void displayInfo() {
	System.out.printf("Student number:%10d Name:%10s course1:%10.2f course2:%10.2f course3:%10.2f gpa:%10.2f\n ",
		studentNumber, name, course1Marks, course2Marks, course3Marks, gpa);
	if (gpa > 3.6) {
	    System.out.print(this.name + "  is in Dean's Honours List");
	}
    }
}
