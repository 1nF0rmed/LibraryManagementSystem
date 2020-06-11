import java.util.*;

public class Member extends Account {
  private Date dateOfMembership;
  private int totalBooksCheckedout;
  
  Member(String id, AccountStatus status) {
	  this.id = id;
	  this.status = status;
	  this.totalBooksCheckedout = 0;
  }

  public int getTotalBooksCheckedOut() {
	return totalBooksCheckedout;
  }

  public boolean reserveBookItem(BookItem bookItem) {
	return false;
  }

  private void incrementTotalBooksCheckedout() {
	  totalBooksCheckedout++;
  }

  public boolean checkoutBookItem(BookItem bookItem) {
	  if( bookItem.canCheckout() && this.totalBooksCheckedout<2 ) {
		  bookItem.checkout(this.id);
		  this.incrementTotalBooksCheckedout();
		  
		  return true;
	  }
	  
	  return false;
  }

  public String getId() {
	// TODO Auto-generated method stub
	return id;
  }


  private void ShowError(String string) {
	// TODO Auto-generated method stub
	
  }

  private void checkForFine(String bookItemBarcode) {
    
  }

  public void returnBookItem(BookItem bookItem) {
	  this.totalBooksCheckedout--;
	  bookItem.resetOwner();
  }

  public boolean renewBookItem(BookItem bookItem) {
    bookItem.updateDueDate();
    return true;
  }

@Override
public boolean resetPassword() {
	// TODO Auto-generated method stub
	return false;
}
}
