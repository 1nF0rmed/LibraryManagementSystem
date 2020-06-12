import java.util.*;
import java.util.stream.Collectors;

/*
 * Driver class that runs the entire project
 * 
 * */
public class Driver {
	
	// 
	// 
	//
	private static List<Book> initiateBooks() {
		List<Book> books = new ArrayList<Book>();
		
		String titles[] = {"Programming with Python 3E", "Programming with C++ 2E", "Software Modelling 1E", "Modelling Data pipelines 4E", "Sotware Process 2E"};
		String ISBN[] = {"9783598215018", "9783598215025", "9783598215032", "9783598215049", "9783598215056"};
		String authors[] = {"Jamie", "Kristoff", "Kalie", "Hafeniver", "Cathosty"};
		
		for(int i=0;i<titles.length;i++) {
			Book b = new Book(ISBN[i], titles[i], authors[i]);
			books.add(b);
		}
		
		return books;
	}
	
	private static List<BookItem> initiateBookItems() {
		List<BookItem> books = new ArrayList<BookItem>();
		
		String titles[] = {"Programming with Python 3E", "Programming with C++ 2E", "Software Modelling 1E", "Modelling Data pipelines 4E", "Sotware Process 2E"};
		String ISBN[] = {"9783598215018", "9783598215025", "9783598215032", "9783598215049", "9783598215056"};
		String authors[] = {"Jamie", "Kristoff", "Kalie", "Hafeniver", "Cathosty"};
		String barCodes[] = {"9783598215018", "9783598215025", "9783598215032", "9783598215049", "9783598215056"};
		
		for(int i=0;i<titles.length;i++) {
			@SuppressWarnings("deprecation")
			BookItem b = new BookItem(ISBN[i], titles[i], authors[i], new Date(2020, 06, 23), new Date(2020, 06, 23), false, barCodes[i]);
			books.add(b);
		}
		
		return books;
	}
	
	private static void printMenu() {
		System.out.println("########################################################################################");
		System.out.println("#                                               #####   ");                                
		System.out.println("#       # #####  #####    ##   #####  #   #    #     # #   #  ####  ##### ###### #    # ");
		System.out.println("#       # #    # #    #  #  #  #    #  # #     #        # #  #        #   #      ##  ## ");
		System.out.println("#       # #####  #    # #    # #    #   #       #####    #    ####    #   #####  # ## # ");
		System.out.println("#       # #    # #####  ###### #####    #            #   #        #   #   #      #    # ");
		System.out.println("#       # #    # #   #  #    # #   #    #      #     #   #   #    #   #   #      #    # ");
		System.out.println("####### # #####  #    # #    # #    #   #       #####    #    ####    #   ###### #    # ");
		System.out.println("########################################################################################");
		System.out.println("");
		System.out.println("#####                                  MAIN MENU                                   #####");
		System.out.println("########################################################################################");
		System.out.println("1. Find book\n");
		System.out.println("2. View my current books\n");
		System.out.println("3. Check-out a book\n");
		System.out.println("4. Return a book\n");
		System.out.println("5. Renew a book\n");
		System.out.println("6. Exit");
		System.out.println("Enter Option: ");
	}
	
	public static void checkoutProcess(Member member, List<BookItem> books, Scanner sc) {
		String barcode;
		int numBooks;
		
		System.out.println("#########################################################################################");
		System.out.println("#####                             CHECK-OUT PROCESS                                 #####");
		System.out.println("#########################################################################################");
		while(true) {
		System.out.println("Enter the number of books: ");
		
			try {
				numBooks = sc.nextInt();
				sc.nextLine();
				if( numBooks<1 )throw new Exception();
				break;
			} catch(Exception e) {
				System.out.println("Please enter a proper value!");
				sc.nextLine();
			}
		}
		
		
		boolean canContinue = true;
		
		for(int i=0;i<numBooks;i++) {
			System.out.println("\n Enter book barcode: ");
			barcode = sc.nextLine();
			final String tmp = barcode;
			
			if( !canContinue ) {
				System.out.println("---------- Check-out process complete--------------");
				break;
			}
			
			for(int j=0;j<books.size(); j++) {
				
				// If book is what we are looking for and can be checked out
				if( books.get(j).getBarcode().equals(barcode) ) {
					if( member.checkoutBookItem(books.get(j)) ) {
						System.out.println("Checking out book: "+books.get(j).getBookTitle());
						break;
					}
					else {
						
						// If unable to checkout
						if( member.getTotalBooksCheckedOut()>=2 ) {
							System.out.print("Unable to checkout: Number of books exceed 2!!");
							canContinue = false;
						}
						else if( books.get(j).getBookStatus()==BookStatus.LOANED ) {
							System.out.println("Issue Book: "+books.get(j).getBookTitle());
							System.out.println("The required book has been loaned to another user.");
							break;
						}
						else if( books.get(j).getBookStatus()==BookStatus.LOST ) {
							System.out.println("The required book is currently not available in our library.");
							break;
						}
					}
				}
			}
			System.out.println("---------------------------------------------------------------");
		}
		System.out.println("---------- PRESS ENTER TO CONTINUE-------------");
		sc.nextLine();
	}
	
	public static void displayBooks(String id, List<BookItem> books, Scanner sc) {
		
		System.out.println("#########################################################################################");
		System.out.println("#####                               BOOKS DISPLAY                                   #####");
		System.out.println("#########################################################################################");
		
		for( int i=0;i<books.size();i++ ) {
			if( books.get(i).getCurrentOwner().equals(id) ) {
				System.out.println("BOOK: "+books.get(i).title+"\nDUE DATE: "+books.get(i).getDueDate());
				System.out.println();
			}
		}
		System.out.println("---------- PRESS ENTER TO CONTINUE-------------");
		sc.nextLine();
	}
	
	public static void returnBook(Member member, List<BookItem> books, Scanner sc) {
		System.out.println("#########################################################################################");
		System.out.println("#####                               RETURN  BOOKS                                   #####");
		System.out.println("#########################################################################################");
		
		// Get the book barcode from the user
		String barcode;
		System.out.println("\n Enter book barcode: ");
		barcode = sc.nextLine();
		
		// Check if the barcode is valid
		
		// 1) If barcode is valid ==> Check if bookitem has currentowner as member
		//   										a) If yes => Check for fine!!! use Fine Class
		//													then reset currentOwner and set BookStatus of BookItem to AVAILABLE
		// 											b) Otherwise, mention member doesn't own the book
		// 2) Otherwise mention invalid barcode
		
		
		for(int j=0;j<books.size(); j++) {
			
			if( books.get(j).getBarcode().equals(barcode) ) {
				
				BookItem ret_book= books.get(j);
				
				if( ret_book.getCurrentOwner().equals(member.getId()) ) {
					
					if(new Date().after(ret_book.getDueDate())) {
						Fine f= new Fine();
						double fine = f.calculateFine(ret_book.getDueDate(),new Date());
						System.out.println("Please pay the fine for Rs. "+fine);
					}
					member.returnBookItem(ret_book);
					System.out.println("Returning book: "+ret_book.getBookTitle());
				}
				else {
					System.out.println("The member doesn't own this book!");
				}
				break;
			}
		}
		
		// 3) Ask them to press enter to continue
		System.out.println("---------- PRESS ENTER TO CONTINUE-------------");
		sc.nextLine();
		
	}
	
	public static void renewBook(Member member, List<BookItem> books, Scanner sc) {
		System.out.println("#########################################################################################");
		System.out.println("#####                                 RENEW  BOOKS                                  #####");
		System.out.println("#########################################################################################");
		// Get the book barcode from the user
		// Check if the barcode is valid
		String barcode;
		System.out.println("\n Enter book barcode: ");
		barcode = sc.nextLine();
		
		// 1) If barcode is valid ==> Check if bookitem has currentowner as member
		//   										a) If yes => Check for fine!!! use Fine Class
		//													then update dueDate of BookItem to new date
		// 											b) Otherwise, mention member doesn't own the book
		// 2) Otherwise mentione invalid barcode
		
		
		for(int j=0;j<books.size(); j++) {
			
			if( books.get(j).getBarcode().equals(barcode) ) {
				
				BookItem ret_book= books.get(j);
				
				if( ret_book.getCurrentOwner().equals(member.getId()) ) {
					
					if(new Date().after(ret_book.getDueDate())) {
						Fine f= new Fine();
						double fine = f.calculateFine(ret_book.getDueDate(),new Date());
						System.out.println("Please pay the fine for Rs. "+fine);
					}
					if( member.renewBookItem(ret_book) )
						System.out.println("Renewing book: "+ret_book.getBookTitle());
					
				}
				else {
					System.out.println("The member doesn't own this book!");
				}
				break;
			}
		}
		
		// 3) Ask them to press enter to continue
		System.out.println("---------- PRESS ENTER TO CONTINUE-------------");
		sc.nextLine();
		
		
	}

	public static void main(String[] args) {
		/*
		 * This defines the main business logic of the project
		 * Process:
		 * 1) Initiate the library with 5 books
		 * 2) Update catalog with library books for searching purpose
		 * 3) Create 1 library member
		 * 4) Main interaction window:
		 * 4) i) Find book
		 * 4) ii) Display Books
		 * 4) iii) Check-out a book
		 * 4) iv) Return a book
		 * 4) v) Renew a book
		 * 4) vi) Exit
		 * 5) Loop
		 * */
		
		// Books to be used in catalog
		List<Book> searchBooks = initiateBooks();
		// Books to be used in the library system
		List<BookItem> libraryBooks = initiateBookItems();
		
		// Create a 1 library member
		Member member1 = new Member("1011", AccountStatus.ACTIVE);
		
		Scanner sc = new Scanner(System.in);
		boolean isExit = false;
		
		while( !isExit ) {
			printMenu();
			int input = sc.nextInt();
			sc.nextLine();
			switch(input) {
			case 1:
			case 2:
				displayBooks(member1.getId(), libraryBooks, sc);
				break;
			case 3: 
				checkoutProcess(member1, libraryBooks, sc);
				break;
			case 4:
				returnBook(member1, libraryBooks, sc);
				break;
			case 5:
				renewBook(member1, libraryBooks, sc);
				break;
			case 6:
				isExit = true;
				break;
			}
		}
		
		
		
	}

}
