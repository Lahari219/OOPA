package ReflectionAPI;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

class item {
    public String name = "Laptop";
    public double price = 1200.0;

    public void showDetails() {
        System.out.println("Product: " + name + ", Price: " + price);
    }
}

public class ReflectionInspectorDemo {
    public static void main(String[] args) {
        Class<?> cls = item.class;  // Use wildcard to avoid errors

        System.out.println("Declared Methods:");
        for (Method m : cls.getDeclaredMethods()) {
            System.out.println(m.getName() + " - " + m.getReturnType().getSimpleName());
        }

        System.out.println("\nDeclared Fields:");
        for (Field f : cls.getDeclaredFields()) {
            System.out.println(f.getName() + " - " + f.getType().getSimpleName());
        }
    }
}