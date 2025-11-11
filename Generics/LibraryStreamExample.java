package Generics;

import java.util.*;
import java.util.stream.*;

class Book {
    String title;
    String author;
    double price;
    double rating;
    Book(String t, String a, double p, double r) { title=t; author=a; price=p; rating=r; }
}

public class LibraryStreamExample {
    public static void main(String[] args) {
        List<Book> books = List.of(
            new Book("Java 101", "James", 499, 4.6),
            new Book("Python", "Guido", 599, 4.2),
            new Book("C++ Basics", "Bjarne", 399, 3.9)
        );

        List<String> result = books.stream()
            .filter(b -> b.rating > 4)
            .sorted(Comparator.comparingDouble(b -> b.price))
            .map(b -> b.title)
            .collect(Collectors.toList());

        System.out.println("Filtered & Sorted Titles: " + result);
    }
}
