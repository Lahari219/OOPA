package ReflectionAPI;


import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@interface APIDescription {
    String description();
}

@APIDescription(description = "Handles user operations")
class SampleAPI {
    @APIDescription(description = "Creates a new user")
    public void createUser() {
        System.out.println("User created!");
    }
}

public class APIDescriptionDemo {
    public static void main(String[] args) {
        Class<SampleAPI> cls = SampleAPI.class;

        if (cls.isAnnotationPresent(APIDescription.class)) {
            APIDescription apiDesc = cls.getAnnotation(APIDescription.class);
            System.out.println("Class description: " + apiDesc.description());
        }

        for (Method m : cls.getDeclaredMethods()) {
            if (m.isAnnotationPresent(APIDescription.class)) {
                APIDescription apiDesc = m.getAnnotation(APIDescription.class);
                System.out.println("Method " + m.getName() + ": " + apiDesc.description());
            }
        }
    }
}