package ExceptionHandling;
class InvalidSalaryException extends Exception {
    public InvalidSalaryException(String msg) { super(msg); }
}

public class Employee {
    public static void main(String[] args) {
        double salary = -5000;

        try {
            if (salary < 0)
                throw new InvalidSalaryException("Salary cannot be negative!");
            System.out.println("Salary: " + salary + " ✅");
        } catch (InvalidSalaryException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}