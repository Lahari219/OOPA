package ReflectionAPI;

import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface RoleAllowed { String role(); }

class UserService {
    @RoleAllowed(role="ADMIN") public void deleteUser() { System.out.println("User deleted."); }
    @RoleAllowed(role="USER") public void viewProfile() { System.out.println("Profile viewed."); }
}

public class RoleBasedAccessDemo {
    public static void main(String[] args) throws Exception {
        UserService us = new UserService();
        String role = "USER";

        for (Method m : UserService.class.getDeclaredMethods()) {
            if (m.isAnnotationPresent(RoleAllowed.class)) {
                RoleAllowed ra = m.getAnnotation(RoleAllowed.class);
                if (ra.role().equals(role)) m.invoke(us);
                else System.out.println("Access denied for method: " + m.getName());
            }
        }
    }
}