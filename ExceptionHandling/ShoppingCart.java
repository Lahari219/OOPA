package ExceptionHandling;

class OutOfStockException extends Exception {
    public OutOfStockException(String msg) { super(msg); }
}

public class ShoppingCart {
    public static void main(String[] args) {
        int stock = 5;
        int qty = 10;

        try {
            if (qty > stock) throw new OutOfStockException("Not enough stock!");
            System.out.println(qty + " items purchased ✅");
        } catch (OutOfStockException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
