package ExceptionHandling;

class LatePaymentException extends Exception {
    public LatePaymentException(String msg) { super(msg); }
}

public class ElectricityBill {
    public static void main(String[] args) {
        boolean paidOnTime = false;

        try {
            if (!paidOnTime)
                throw new LatePaymentException("Payment is late!");
            System.out.println("Bill paid ✅");
        } catch (LatePaymentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
