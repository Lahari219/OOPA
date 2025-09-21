package ExceptionHandling;


class ExcessStayDurationException extends Exception {
    public ExcessStayDurationException(String msg) {
        super(msg);
    }
}

public class Hotel {
    public static void main(String[] args) {
        int days = 40;

        try {
            if (days > 30) {
                throw new ExcessStayDurationException("Cannot book for more than 30 days!");
            }
            System.out.println("Booking successful ✅");
        } catch (ExcessStayDurationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
