package ReflectionAPI;

import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Loggable { String message(); }

class AccountService {
    @Loggable(message="Executing deposit operation") public void deposit() { System.out.println("Deposited amount."); }
    @Loggable(message="Executing withdraw operation") public void withdraw() { System.out.println("Withdrawn amount."); }
}

public class LoggingDemo {
    public static void main(String[] args) throws Exception {
        AccountService acc = new AccountService();
        for (Method m : AccountService.class.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Loggable.class)) {
                Loggable log = m.getAnnotation(Loggable.class);
                System.out.println("Log: " + log.message());
                m.invoke(acc);
            }
        }
    }
}