package ArrayList;

import java.util.ArrayList;

public class ProductInventoryExample {
    public static void main(String[] args) {
        // Create an ArrayList to store product quantities
        ArrayList<Integer> quantities = new ArrayList<>();

        // Add product quantities
        quantities.add(10);
        quantities.add(20);
        quantities.add(15);

        System.out.println("Initial product quantities: " + quantities);

        // Update a quantity at a specific index
        quantities.set(1, 25); // Update index 1 (20 → 25)
        System.out.println("After updating index 1 to 25: " + quantities);

        // Calculate total quantity using for-each loop
        int total = 0;
        for (int qty : quantities) {
            total += qty;
        }
        System.out.println("Total quantity: " + total);

        // Clear all quantities
        quantities.clear();
        System.out.println("After clearing list: " + quantities);
    }
}
