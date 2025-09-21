package Inheritance;

class Creature {
	 void eat() {
	     System.out.println("Creature is eating...");
	 }
	}

	//Intermediate class
	class Dog extends Creature {
	 void bark() {
	     System.out.println("Dog is barking...");
	 }
	}

	//Derived class
	class Puppy extends Dog {
	 void weep() {
	     System.out.println("Puppy is weeping...");
	 }
	}

	//Main class
	public class PetSystem {
	 public static void main(String[] args) {
	     Puppy puppy = new Puppy();
	     puppy.eat();   // From Creature
	     puppy.bark();  // From Dog
	     puppy.weep();  // From Puppy
	 }
}