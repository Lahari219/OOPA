package Generics;

import java.util.List;

public class SumCalculator {
    public static double calculateSum(List<? extends Number> list) {
        double sum = 0.0;
        for (Number n : list) {
            sum += n.doubleValue();
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println("Sum: " + calculateSum(List.of(10, 20, 30.5)));
    }
}
