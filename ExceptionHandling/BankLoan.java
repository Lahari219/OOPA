package ExceptionHandling;

class LowCreditScoreException extends Exception {
    public LowCreditScoreException(String msg) { super(msg); }
}

public class BankLoan {
	 public static void main(String[] args) {
	        int creditScore = 600;

	        try {
	            if (creditScore < 650)
	                throw new LowCreditScoreException("Credit score too low!");
	            System.out.println("Loan approved ✅");
	        } catch (LowCreditScoreException e) {
	            System.out.println("Error: " + e.getMessage());
	        }
	    }
}
