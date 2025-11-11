package ArrayList;
import java.util.ArrayList;

public class StudentListExample {
    public static void main(String[] args) {
        // Create an ArrayList to store student names
        ArrayList<String> students = new ArrayList<>();

        // Add students
        students.add("Alice");
        students.add("Bob");
        students.add("Charlie");

        System.out.println("Initial list: " + students);

        // Add at specific index
        students.add(1, "David"); // Insert "David" at index 1
        System.out.println("After inserting David at index 1: " + students);

        // Remove a student
        students.remove("Charlie"); // Removes by value
        System.out.println("After removing Charlie: " + students);

        // Check if a student exists
        System.out.println("Contains Alice? " + students.contains("Alice"));
        System.out.println("Contains Charlie? " + students.contains("Charlie"));

        // Display total number of students
        System.out.println("Total students: " + students.size());
    }
}
