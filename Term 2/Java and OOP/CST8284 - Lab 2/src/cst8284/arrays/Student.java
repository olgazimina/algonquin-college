package cst8284.arrays;

public class Student {
	
	private String firstName;
	private String lastName;
	private int studentNumber;
	
	public Student(int number) {
		this("First"+number, "Last"+number, number);
	}
	
	public Student(String first, String last, int number) {
		setFirstName(first); setLastName(last); setStudentNumber(number);
	};
	
	public String getFirstName() {return firstName;}
	public void setFirstName(String firstName) {this.firstName = firstName;}
	
	public String getLastName() {return lastName;}
	public void setLastName(String lastName) {this.lastName = lastName;}
	
	public int getStudentNumber() {return studentNumber;}
	public void setStudentNumber(int studentNumber) {this.studentNumber = studentNumber;}
	
}
