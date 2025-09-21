package Inheritance;

class Vehicle {
	 void move() {
	     System.out.println("Vehicle is moving...");
	 }
	}

	//Derived class
	class Car extends Vehicle {
	 void fuelType() {
	     System.out.println("Car uses petrol or diesel.");
	 }
	}

	public class VehicleApp {
	 public static void main(String[] args) {
	     Car car = new Car();
	     car.move();      // Inherited
	     car.fuelType();  // Car-specific
	 }
	}
