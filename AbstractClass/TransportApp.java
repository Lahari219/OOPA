package AbstractClass;

abstract class TransportBooking {
    abstract double calculateFare(double distance); // Varies per transport mode

    void bookTicket() { // Common for all
        System.out.println("Ticket booked successfully.");
    }
}

class BusBooking extends TransportBooking {
    @Override
    double calculateFare(double distance) {
        return distance * 0.5; // example fare per km
    }
}

class TrainBooking extends TransportBooking {
    @Override
    double calculateFare(double distance) {
        return distance * 0.8; // example fare per km
    }
}

class FlightBooking extends TransportBooking {
    @Override
    double calculateFare(double distance) {
        return distance * 2.0; // example fare per km
    }
}

public class TransportApp {
    public static void main(String[] args) {
        TransportBooking bus = new BusBooking();
        System.out.println("Bus fare: $" + bus.calculateFare(100));
        bus.bookTicket();

        TransportBooking flight = new FlightBooking();
        System.out.println("Flight fare: $" + flight.calculateFare(100));
        flight.bookTicket();
    }
}