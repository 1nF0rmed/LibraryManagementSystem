import java.util.*;

public class BookItem extends Book {

  private String barcode;
  private boolean isReferenceOnly;
  private Date borrowed;
  private Date dueDate;
  private double price;
  private BookFormat format;
  private BookStatus status;
  private String currentOwner;

  BookItem(String _ISBN, String _title, String _author, Date borrowed, Date dueDate, boolean isReferenceOnly, String barcode) {
		super(_ISBN, _title, _author);
		// TODO Auto-generated constructor stub
		this.borrowed = borrowed;
		this.barcode = barcode;
		this.dueDate = dueDate;
		this.isReferenceOnly = isReferenceOnly;
		price = 300;
		format = BookFormat.HARDCOVER;
		status = BookStatus.AVAILABLE;
		currentOwner = "NIL";
  }
  
  public boolean canCheckout() {
	  //Check if book is reference only
	  // If yes, then return false;
	  // Else, check if the book status is available
	  if( !isReferenceOnly && status==BookStatus.AVAILABLE )
		  return true;
	  
	  return false;
  }
  
  public void checkout(String memberId) {
	  // Checkout the book
	  // Change the book status to Loaned
	  // Update borrowed date to current date
	  // Update the book due date to 10 days from now
	  // Set previous owner to current member
	  
	  status = BookStatus.LOANED;
	  borrowed = new Date();
	  
	  // Convert date to calendar
      Calendar c = Calendar.getInstance();
      c.setTime(borrowed);
      // Add 10 days 
      c.add(Calendar.DATE, 10);
      // convert calendar to date
      dueDate = c.getTime();
      
      currentOwner = memberId;
      
  }

  
  public String getCurrentOwner() {
	  return currentOwner;
  }
  
  public void resetOwner() {
	  currentOwner = "NIL";
  }

  public String getBarcode() {
	// TODO Auto-generated method stub
	return barcode;
  }
  
  public BookStatus getBookStatus() {
	  return this.status;
  }
  
  public String getBookTitle() {
	  return this.title;
  }
  
  public Date getDueDate() {
	  return dueDate;
  }
  
}