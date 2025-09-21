package ReflectionAPI;

import java.lang.annotation.*;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotEmpty {}

class Customer {
    @NotEmpty
    public String name = "";
    public String email = "customer@example.com";
}

public class CustomValidationDemo {
    public static void main(String[] args) throws IllegalAccessException {
        Customer c = new Customer();
        for (Field f : Customer.class.getDeclaredFields()) {
            if (f.isAnnotationPresent(NotEmpty.class)) {
                f.setAccessible(true);
                Object value = f.get(c);
                if (value == null || value.toString().isEmpty()) {
                    System.out.println("Warning: Field '" + f.getName() + "' should not be empty!");
                }
            }
        }
    }
}