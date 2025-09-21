package Interfaces;

interface Payment {
    void processPayment(double amount);
    void refundPayment(double amount);
}

class CreditCardPayment implements Payment {
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
    }
    public void refundPayment(double amount) {
        System.out.println("Refunding credit card payment of $" + amount);
    }
}

class UPIPayment implements Payment {
    public void processPayment(double amount) {
        System.out.println("Processing UPI payment of $" + amount);
    }
    public void refundPayment(double amount) {
        System.out.println("Refunding UPI payment of $" + amount);
    }
}

public class PaymentTest {
    public static void main(String[] args) {
        Payment[] payments = {new CreditCardPayment(), new UPIPayment()};

        double amount = 100.0;

        for (Payment p : payments) {
            p.processPayment(amount);
            p.refundPayment(amount);
            System.out.println();
        }
    }
}

