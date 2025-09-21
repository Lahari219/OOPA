package Inheritance;

class Animal {
    void eat() {
        System.out.println("Animal is eating...");
    }
}

// Derived class
class Lion extends Animal {
    void roar() {
        System.out.println("Lion is roaring...");
    }
}

public class ZooApp {
    public static void main(String[] args) {
        Lion lion = new Lion();
        lion.eat();   // Inherited behavior
        lion.roar();  // Specific behavior
    }
}