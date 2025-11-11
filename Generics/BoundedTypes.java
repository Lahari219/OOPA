package Generics;

import java.util.*;

public class BoundedTypes {
    public static double calculateSum(List<? extends Number> list) {
        double sum = 0;
        for (Number n : list) sum += n.doubleValue();
        return sum;
    }

    public static <T extends Number> double average(List<T> list) {
        double sum = 0;
        for (T n : list) sum += n.doubleValue();
        return sum / list.size();
    }

    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(10, 20, 30);
        System.out.println(calculateSum(nums));
        System.out.println(average(nums));
    }
}
