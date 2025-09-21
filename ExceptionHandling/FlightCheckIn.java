package ExceptionHandling;

class LateCheckInException extends Exception {
    public LateCheckInException(String msg) { super(msg); }
}

public class FlightCheckIn {
    public static void main(String[] args) {
        int minutesBeforeDeparture = 20;

        try {
            if (minutesBeforeDeparture < 30)
                throw new LateCheckInException("Check-in too late!");
            System.out.println("Check-in successful ✅");
        } catch (LateCheckInException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
