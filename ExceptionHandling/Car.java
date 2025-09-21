package ExceptionHandling;

class OverSpeedException extends Exception {
    public OverSpeedException(String msg) { super(msg); }
}

public class Car {
    public static void main(String[] args) {
        int speed = 130;

        try {
            if (speed > 120)
                throw new OverSpeedException("Over speed limit!");
            System.out.println("Speed set: " + speed + " km/h ✅");
        } catch (OverSpeedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}