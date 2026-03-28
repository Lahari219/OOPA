package payroll.exception;

public class InvalidSalaryException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    public InvalidSalaryException(String message) {
        super(message);
    }
    
    public InvalidSalaryException(String message, Throwable cause) {
        super(message, cause);
    }
}