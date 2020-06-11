import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Catalog implements Search {
  private HashMap<String, List<Book>> bookTitles;
  private HashMap<String, List<Book>> bookAuthors;
  private HashMap<String, List<Book>> bookSubjects;
  private HashMap<String, List<Book>> bookPublicationDates;

  public List<Book> searchByTitle(String query) {
    // return all books containing the string query in their title.
    return bookTitles.get(query);
  }

  public List<Book> searchByAuthor(String query) {
    // return all books containing the string query in their author's name.
    return bookAuthors.get(query);
  }

  @Override
  public List<Book> searchBySubject(String subject) {
	// TODO Auto-generated method stub
	return null;
  }

  @Override
  public List<Book> searchByPubDate(Date publishDate) {
	// TODO Auto-generated method stub
	return null;
  }
  
  public void addBook(String category, List<Book> books) {
	  // Add the book to all the list of titles based on category
	  this.bookTitles.put(category, books);
  }
}