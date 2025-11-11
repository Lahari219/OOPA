package Generics;

import java.util.*;

class Employee implements Comparable<Employee> {
    int id;
    String name;
    double salary;

    Employee(int id, String name, double salary) {
        this.id = id; this.name = name; this.salary = salary;
    }

    // Natural ordering - descending by salary
    public int compareTo(Employee e) {
        return Double.compare(e.salary, this.salary);
    }

    public String toString() {
        return name + " (" + salary + ")";
    }
}

public class EmployeeSortExample {
    public static void main(String[] args) {
        List<Employee> list = List.of(
            new Employee(1, "Alice", 50000),
            new Employee(2, "Bob", 70000),
            new Employee(3, "Charlie", 60000)
        );

        // Using Comparable
        List<Employee> sorted = new ArrayList<>(list);
        Collections.sort(sorted);
        System.out.println("Sorted by Comparable (salary desc): " + sorted);

        // Using Comparator
        sorted.sort(Comparator.comparing(e -> e.name));
        System.out.println("Sorted by Comparator (name asc): " + sorted);
    }
}
