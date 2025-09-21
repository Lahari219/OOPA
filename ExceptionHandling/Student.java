package ExceptionHandling;


class InvalidMarksException extends Exception {
    public InvalidMarksException(String msg) { super(msg); }
}

public class Student {
    public static void main(String[] args) {
        int marks = 120;

        try {
            if (marks < 0 || marks > 100)
                throw new InvalidMarksException("Invalid marks!");
            System.out.println("Marks: " + marks);
        } catch (InvalidMarksException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
