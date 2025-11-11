package ArrayList;

import java.util.ArrayList;
import java.util.Collections;

public class ToDoList {
    public static void main(String[] args) {
        // Create an ArrayList to store to-do tasks
        ArrayList<String> tasks = new ArrayList<>();

        // Add tasks
        tasks.add("Buy groceries");
        tasks.add("Clean the house");
        tasks.add("Finish Java assignment");

        System.out.println("Initial To-Do List: " + tasks);

        // Retrieve a specific task
        System.out.println("Task at index 1: " + tasks.get(1));

        // Remove a completed task
        tasks.remove("Clean the house");
        System.out.println("After removing completed task: " + tasks);

        // Sort tasks alphabetically
        Collections.sort(tasks);
        System.out.println("Sorted To-Do List: " + tasks);

        // Check if list is empty
        System.out.println("Is the to-do list empty? " + tasks.isEmpty());
    }
}
