package ExceptionHandling;

class TemperatureOutOfRangeException extends Exception {
    public TemperatureOutOfRangeException(String msg) { super(msg); }
}

public class TemperatureSensor {
    public static void main(String[] args) {
        double temp = 70;

        try {
            if (temp < -50 || temp > 60)
                throw new TemperatureOutOfRangeException("Temperature out of range!");
            System.out.println("Temperature is normal: " + temp + "°C ✅");
        } catch (TemperatureOutOfRangeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
