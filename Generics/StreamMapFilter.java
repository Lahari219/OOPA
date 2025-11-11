package Generics;

import java.util.*;

public class StreamMapFilter {
    public static void main(String[] args) {
        List<Double> salaries = Arrays.asList(25000.0, 40000.0, 60000.0);
        salaries.stream()
            .filter(s -> s >= 30000)
            .map(s -> s * 1.1)
            .forEach(System.out::println);
    }
}
