package Inheritance;

class Person {
	 void displayInfo() {
	     System.out.println("This is a person.");
	 }
	}

	//Intermediate class
	class Student extends Person {
	 String studentID = "S123";
	 void showStudentID() {
	     System.out.println("Student ID: " + studentID);
	 }
	}

	//Derived class
	class GraduateStudent extends Student {
	 void submitThesis() {
	     System.out.println("Graduate student submitted thesis.");
	 }
	}

	public class AcademicSystem {
	 public static void main(String[] args) {
	     GraduateStudent gs = new GraduateStudent();
	     gs.displayInfo();   // From Person
	     gs.showStudentID(); // From Student
	     gs.submitThesis();  // Graduate-specific
	 }
	}