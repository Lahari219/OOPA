package ReflectionAPI;

import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface APIInfo {
    String endpoint();
    String method();
}

class UserAPI {
    @APIInfo(endpoint="/users", method="GET")
    public void getUsers() {}

    @APIInfo(endpoint="/users", method="POST")
    public void createUser() {}
}

public class APIinfoDemo {
    public static void main(String[] args) {
        for (Method m : UserAPI.class.getDeclaredMethods()) {
            if (m.isAnnotationPresent(APIInfo.class)) {
                APIInfo info = m.getAnnotation(APIInfo.class);
                System.out.println("Endpoint: " + info.endpoint() + " Method: " + info.method());
            }
        }
    }
}
