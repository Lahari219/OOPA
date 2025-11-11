package Generics;



import java.util.*;

public class StreamReduce {
    public static void main(String[] args) {
        List<Integer> sales = Arrays.asList(100, 200, 300);
        int total = sales.stream().reduce(0, Integer::sum);
        System.out.println(total);
    }
}
