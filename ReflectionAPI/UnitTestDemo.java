package ReflectionAPI;

import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface TestCase {}

class CalculatorTest {
    @TestCase
    public void testAddition() {
        if (2 + 3 == 5) System.out.println("Addition test passed");
        else System.out.println("Addition test failed");
    }

    @TestCase
    public void testSubtraction() {
        if (5 - 3 == 2) System.out.println("Subtraction test passed");
        else System.out.println("Subtraction test failed");
    }
}

public class UnitTestDemo {
    public static void main(String[] args) throws Exception {
        CalculatorTest testObj = new CalculatorTest();
        for (Method m : CalculatorTest.class.getDeclaredMethods()) {
            if (m.isAnnotationPresent(TestCase.class)) {
                m.invoke(testObj);
            }
        }
    }
}