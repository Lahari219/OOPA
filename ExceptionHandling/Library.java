package ExceptionHandling;

class BookLimitExceededException extends Exception {
    public BookLimitExceededException(String msg) { super(msg); }
}

public class Library {
    public static void main(String[] args) {
        int booksToBorrow = 5;

        try {
            if (booksToBorrow > 3)
                throw new BookLimitExceededException("Cannot borrow more than 3 books!");
            System.out.println(booksToBorrow + " books issued ✅");
        } catch (BookLimitExceededException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}