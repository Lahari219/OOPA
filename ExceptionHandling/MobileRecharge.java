package ExceptionHandling;

class MinimumRechargeException extends Exception {
    public MinimumRechargeException(String msg) { super(msg); }
}

public class MobileRecharge {
    public static void main(String[] args) {
        int rechargeAmount = 5;

        try {
            if (rechargeAmount < 10)
                throw new MinimumRechargeException("Recharge amount too low!");
            System.out.println("Recharged: ₹" + rechargeAmount + " ✅");
        } catch (MinimumRechargeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}