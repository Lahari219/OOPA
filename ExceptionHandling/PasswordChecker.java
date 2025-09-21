package ExceptionHandling;

class WeakPasswordException extends Exception {
    public WeakPasswordException(String msg) { super(msg); }
}

public class PasswordChecker {
    public static void main(String[] args) {
        String password = "pass123";

        try {
            if (password.length() < 8 || !password.matches(".*\\d.*"))
                throw new WeakPasswordException("Password too weak!");
            System.out.println("Password is strong ✅");
        } catch (WeakPasswordException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
