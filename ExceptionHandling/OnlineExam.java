package ExceptionHandling;

class LateSubmissionException extends Exception {
    public LateSubmissionException(String msg) { super(msg); }
}

public class OnlineExam {
    public static void main(String[] args) {
        boolean submittedOnTime = false;

        try {
            if (!submittedOnTime)
                throw new LateSubmissionException("Submission too late!");
            System.out.println("Exam submitted ✅");
        } catch (LateSubmissionException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}