
public class StudentTest {

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	System.out.println("Program developed by Olga Zimina");
	Student student1 = new Student();
	Student student2 = new Student(1001, "Josh", 89, 85, 91);
	Student student3 = new Student();
	student3.getInfo();
	student1.calculateGPA();
	student2.calculateGPA();
	student3.calculateGPA();
	
	student1.displayInfo();
	student2.displayInfo();
	student3.displayInfo();
    }

}
