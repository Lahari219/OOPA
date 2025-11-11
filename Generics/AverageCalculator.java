package Generics;

import java.util.List;

public class AverageCalculator {
    public static <T extends Number> double calculateAverage(List<T> numbers) {
        double sum = 0.0;
        for (T num : numbers) {
            sum += num.doubleValue();
        }
        return sum / numbers.size();
    }

    public static void main(String[] args) {
        System.out.println("Average: " + calculateAverage(List.of(10, 20, 30)));
    }
}
