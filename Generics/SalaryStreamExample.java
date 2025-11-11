package Generics;

import java.util.*;

public class SalaryStreamExample {
    public static void main(String[] args) {
        List<Double> salaries = List.of(25000.0, 40000.0, 50000.0, 28000.0);

        salaries.stream()
                .filter(s -> s >= 30000)
                .map(s -> s * 1.10)
                .forEach(System.out::println);
    }
}
