import java.util.ArrayList;
import java.util.List;

public class Book {
  private String ISBN;
  protected String title;
  private String subject;
  private String publisher;
  private String language;
  private int numberOfPages;
  private List<Author> authors;
  
  Book(String _ISBN, String _title, String _author) {
	  ISBN = _ISBN;
	  title = _title;
	  authors = new ArrayList<Author>();
	  authors.add(new Author(_author, "PhD"));
	  subject = _title;
	  publisher = "Pearson";
	  language = "English";
	  numberOfPages = 300;
  }
}