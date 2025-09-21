package ExceptionHandling;

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String msg) { super(msg); }
}

public class BankAccount {
    public static void main(String[] args) {
        double balance = 5000;
        double withdrawAmount = 6000;

        try {
            if (withdrawAmount > balance)
                throw new InsufficientBalanceException("Insufficient balance!");
            balance -= withdrawAmount;
            System.out.println("Withdrawn: " + withdrawAmount + ", Balance: " + balance);
        } catch (InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}