package Inheritance;

class Appliance {
	 void turnOn() {
	     System.out.println("Appliance is turned on.");
	 }
	}

	//Intermediate class
	class Fan extends Appliance {
	 void rotate() {
	     System.out.println("Fan is rotating.");
	 }
	}

	//Derived class
	class CeilingFan extends Fan {
	 void setSpeed(int speed) {
	     System.out.println("Ceiling fan speed set to " + speed);
	 }
	}

	public class SmartHomeSystem {
	 public static void main(String[] args) {
	     CeilingFan cf = new CeilingFan();
	     cf.turnOn();      // From Appliance
	     cf.rotate();      // From Fan
	     cf.setSpeed(3);   // CeilingFan-specific
	 }
	}
