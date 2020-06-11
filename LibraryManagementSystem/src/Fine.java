import java.util.Date;
import java.time.temporal.ChronoUnit;

public class Fine {
  private Date creationDate;
  private double bookItemBarcode;
  private String memberId;

  public static void collectFine(String memberId, BookItem book) {}
  
  private double calculateFine(Date expected, Date actual) {
	  double fine= 2 * ChronoUnit.DAYS.between(expected,actual);
	  
	  return fine;
  }
}