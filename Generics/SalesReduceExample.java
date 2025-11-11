package Generics;

import java.util.*;

public class SalesReduceExample {
    public static void main(String[] args) {
        List<Integer> sales = List.of(1000, 2000, 3000, 4000);
        int total = sales.stream().reduce(0, Integer::sum);
        System.out.println("Total Sales: " + total);
    }
}
