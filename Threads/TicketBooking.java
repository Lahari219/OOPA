package Threads;

class Seat {
    private final int number;
    private boolean booked = false;

    Seat(int number) {
        this.number = number;
    }

    public synchronized boolean book() {
        if (booked) return false;
        booked = true;
        return true;
    }

    public boolean isBooked() {
        return booked;
    }

    public int getNumber() {
        return number;
    }
}

class Train {
    private final Seat[] seats;

    Train(int seatCount) {
        seats = new Seat[seatCount];
        for (int i = 0; i < seatCount; i++) seats[i] = new Seat(i + 1);
    }

    public synchronized boolean bookSeat(int seatNo) {
        if (seatNo < 1 || seatNo > seats.length) return false;
        return seats[seatNo - 1].book();
    }
}

// Thread class
class BookerThread extends Thread {
    private final Train train;
    private final int seatNo;

    BookerThread(Train train, int seatNo) {
        this.train = train;
        this.seatNo = seatNo;
    }

    @Override
    public void run() {
        boolean ok = train.bookSeat(seatNo);
        System.out.println(getName() + " -> seat " + seatNo + " : " + (ok ? "BOOKED" : "ALREADY BOOKED"));
    }
}

// Runnable class
class BookerRunnable implements Runnable {
    private final Train train;
    private final int seatNo;
    private final String name;

    BookerRunnable(Train train, int seatNo, String name) {
        this.train = train;
        this.seatNo = seatNo;
        this.name = name;
    }

    public void run() {
        boolean ok = train.bookSeat(seatNo);
        System.out.println(name + " -> seat " + seatNo + " : " + (ok ? "BOOKED" : "ALREADY BOOKED"));
    }
}

public class TicketBooking {
    public static void main(String[] args) {
        Train train = new Train(3);

        // Using Thread
        new BookerThread(train, 1).start();
        new BookerThread(train, 1).start();

        // Using Runnable
        Thread t1 = new Thread(new BookerRunnable(train, 2, "User1"));
        Thread t2 = new Thread(new BookerRunnable(train, 2, "User2"));
        t1.start();
        t2.start();
    }
}
