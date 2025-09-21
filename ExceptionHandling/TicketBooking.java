package ExceptionHandling;

class SeatsNotAvailableException extends Exception {
    public SeatsNotAvailableException(String msg) { super(msg); }
}

public class TicketBooking {
    public static void main(String[] args) {
        int availableSeats = 10;
        int ticketsRequested = 15;

        try {
            if (ticketsRequested > availableSeats)
                throw new SeatsNotAvailableException("Not enough seats available!");
            System.out.println(ticketsRequested + " tickets booked ✅");
        } catch (SeatsNotAvailableException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}