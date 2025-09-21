package ExceptionHandling;

class InvalidPriceException extends Exception {
    public InvalidPriceException(String msg) { super(msg); }
}

public class ProductPriceValidation {
    public static void main(String[] args) {
        double price = 0;

        try {
            if (price <= 0)
                throw new InvalidPriceException("Price must be positive!");
            System.out.println("Price set: ₹" + price + " ✅");
        } catch (InvalidPriceException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
