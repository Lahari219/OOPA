package ExceptionHandling;



class LimitExceededException extends Exception {

    public LimitExceededException(String msg) { super(msg); }
}

public class Atm {
    public static void main(String[] args) {
        double withdrawAmount = 25000;

        try {
            if (withdrawAmount > 20000)
                throw new LimitExceededException("Exceeded daily limit!");
            System.out.println("Withdrawn: " + withdrawAmount + " ✅");
        } catch (LimitExceededException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}