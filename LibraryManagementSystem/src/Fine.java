import java.util.Date;

public class Fine {
  private Date creationDate;
  private double bookItemBarcode;
  private String memberId;

  public static void collectFine(String memberId, BookItem book) {}
  
  public double calculateFine(Date expected, Date actual) {
	  
	  // Calculate the difference in time
	  long difference = actual.getTime() - expected.getTime();
	  // Divide to get number of days
	  double numDaysLate = ( difference/(1000*60*60*24) );
	  // Calculate the fine
	  // 2 INR/day of fine
	  // Consider making the fine a constant.
	  double fine= 2 * numDaysLate;
	  
	  return fine;
  }
}