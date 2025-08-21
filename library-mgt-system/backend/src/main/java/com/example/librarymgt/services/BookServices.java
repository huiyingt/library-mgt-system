package com.example.librarymgt.services;	
//this is business logic layer
//perform CRUD operations on Book data
import com.example.librarymgt.repository.BookRepository;
import com.example.librarymgt.model.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //indicates that this class is a service component
public class BookServices {

	@Autowired //to inject the BookRepository bean
	private BookRepository bookr; //repository to access Book data
	
	public Book saveBook(Book book) {
		// Need data: a book object
		// saveBook requires Book details
		// Use BookRepository to save the book
		System.out.println("Book added: " + book);
		return bookr.save(book); //returns the saved book object
	}
	
	public Optional<Book> getBookById(Long id) {
		// Use BookRepository to find the book by ID
		return bookr.findById(id); //returns the book if found, otherwise null
	}
	
	public Optional<Book> getBookByTitle (String title) {
		// Use BookRepository to find the book by ISBN
		return bookr.findByTitle(title); //returns the book if found, otherwise null
	}
	
	public Optional<Book> getBookByAuthor (String author) {
		// Use BookRepository to find the book by Author
		return bookr.findByAuthor(author); //returns the book if found, otherwise null
	}
	
	public List<Book> getAllBooks() {
		// Use BookRepository to find all books
		return bookr.findAll(); //returns a list of all books
	}	
	
	public Book updateBook(Long id, Book bookDetails) {
		// Use BookRepository to find the book by ID
		Book existingBook = bookr.findById(id)
				.orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));
		// Update the existing book's details
		if (bookDetails.getIsbn() != null) //check if ISBN is not null
			existingBook.setIsbn(bookDetails.getIsbn());
		if (bookDetails.getTitle() != null) //check if title is not null
			existingBook.setTitle(bookDetails.getTitle());
		if (bookDetails.getAuthor() != null) //check if author is not null
			existingBook.setAuthor(bookDetails.getAuthor());	
		if (bookDetails.getCategory() != null) //check if category is not null
			existingBook.setCategory(bookDetails.getCategory());
		if (bookDetails.getPublicationYear() != null) //check if published date is not null
			existingBook.setPublicationYear(bookDetails.getPublicationYear());
		// Save the updated book
		System.out.println("Book updated: " + existingBook);
		return bookr.save(existingBook); //returns the updated book object	
	}
	
	public void deleteBook(Long id) {
		// Use BookRepository to delete the book by ID
		if (bookr.existsById(id)) { //check if book exists
			bookr.deleteById(id); //delete the book
			System.out.println("Book with ID " + id + " has been deleted.");
		} else {
			System.out.println("Book with ID " + id + " does not exist.");
		}
	}
}
