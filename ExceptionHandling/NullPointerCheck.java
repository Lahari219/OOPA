package ExceptionHandling;

public class NullPointerCheck {
    public static void main(String[] args) {
        String str = null;
        try {
            System.out.println("Length: " + str.length());
        } catch (NullPointerException e) {
            System.out.println("Error: String is null!");
        }
    }
}