package Generics;

import java.util.*;
import java.util.function.*;

class Student {
    String name;
    double grade;

    Student(String name, double grade) {
        this.name = name; this.grade = grade;
    }
}

public class PredicateConsumerExample {
    public static void main(String[] args) {
        List<Student> students = List.of(
            new Student("Alice", 82.5),
            new Student("Bob", 65.0),
            new Student("Charlie", 91.0)
        );

        Predicate<Student> above75 = s -> s.grade > 75;
        Consumer<Student> display = s -> System.out.println(s.name + ": " + s.grade);

        students.stream()
                .filter(above75)
                .forEach(display);
    }
}
