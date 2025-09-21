package AbstractClass;

abstract class BankAccount {
    protected double balance; // subclasses can access

    abstract double calculateInterest(); // varies per account

    void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: $" + amount + ", Balance: $" + balance);
    }

    void withdraw(double amount) {
        if(amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount + ", Balance: $" + balance);
        } else {
            System.out.println("Insufficient balance.");
        }
    }
}

class SavingsAccount extends BankAccount {
    @Override
    double calculateInterest() {
        return balance * 0.04; // 4% interest
    }
}

class CurrentAccount extends BankAccount {
    @Override
    double calculateInterest() {
        return 0; // no interest for current account
    }
}

public class BankingApplication {
    public static void main(String[] args) {
        BankAccount sa = new SavingsAccount();
        sa.deposit(1000);
        System.out.println("Interest: $" + sa.calculateInterest());

        BankAccount ca = new CurrentAccount();
        ca.deposit(2000);
        System.out.println("Interest: $" + ca.calculateInterest());
    }
}