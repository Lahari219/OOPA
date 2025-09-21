package ReflectionAPI;


import java.lang.reflect.Method;

public class ProductReflectionDemo {

    public static void main(String[] args) throws Exception {
        Class<?> cls = Product.class; // Load class
        Object obj = cls.getDeclaredConstructor().newInstance(); // Instantiate
        Method method = cls.getMethod("showDetails"); // Get method
        method.invoke(obj); // Invoke dynamically
    }
}

class Product {
    public void showDetails() {
        System.out.println("Product: Laptop, Price: $1200");
    }
}
