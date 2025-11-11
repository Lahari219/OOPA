package Generics;

import java.util.*;
import java.util.stream.*;
class Stu{
    String name;
    String dept;
    int marks;
    Stu(String n, String d, int m) { name=n; dept=d; marks=m; }
}

public class GroupingExample {
    public static void main(String[] args) {
        List<Stu> list = List.of(
            new Stu("A", "CS", 80),
            new Stu("B", "IT", 70),
            new Stu("C", "CS", 90)
        );

        Map<String, Double> avgMarks = list.stream()
            .collect(Collectors.groupingBy(
                s -> s.dept,
                Collectors.averagingInt(s -> s.marks)
            ));

        avgMarks.forEach((dept, avg) ->
            System.out.println(dept + " Avg Marks: " + avg));
    }
}
