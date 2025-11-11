package Generics;

import java.util.*;

class Product {
    String name;
    double price;
    double rating;

    Product(String name, double price, double rating) {
        this.name = name; this.price = price; this.rating = rating;
    }

    public String toString() {
        return name + " (₹" + price + ", ⭐" + rating + ")";
    }
}

public class LambdaSortExample {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>(List.of(
            new Product("Phone", 70000, 4.5),
            new Product("Laptop", 90000, 4.7),
            new Product("Tablet", 30000, 4.5)
        ));

        // Sort by rating desc, then price asc
        products.sort((p1, p2) -> {
            int cmp = Double.compare(p2.rating, p1.rating);
            return (cmp != 0) ? cmp : Double.compare(p1.price, p2.price);
        });

        products.forEach(System.out::println);
    }
}
