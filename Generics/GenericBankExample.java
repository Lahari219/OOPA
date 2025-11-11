package Generics;

class Account<T> {
    private T balance;

    public Account(T balance) {
        this.balance = balance;
    }

    public T getBalance() {
        return balance;
    }

    public <U> void displayBalance(U accountType) {
        System.out.println(accountType + " balance: " + balance);
    }
}

public class GenericBankExample {
    public static void main(String[] args) {
        Account<Double> savings = new Account<>(15000.50);
        Account<Integer> current = new Account<>(12000);

        savings.displayBalance("Savings");
        current.displayBalance("Current");
    }
}
