package ExceptionHandling;

import java.util.Scanner;

public class ArrayIndexDemo {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter index (0-4): ");
        int index = sc.nextInt();

        try {
            System.out.println("Element: " + arr[index]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Invalid index!");
        }
    }
}
