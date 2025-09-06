package com.example.librarymgt.controller;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.librarymgt.services.BookServices;
import com.example.librarymgt.model.Book;
import com.example.librarymgt.dto.BookDTO;
@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins= "http://localhost:5173")
//CrossOrigin to connect to ReactJS frontend
public class BookController {

	@Autowired //to inject the BookServices bean
	private BookServices bookServices; //service to handle business logic
	
	@PostMapping("/create") //endpoint to create a new book
	public Book createBook(@RequestBody Book book) {
		// This method will handle the creation of a new book
		// It will receive a Book object from the request body
		return bookServices.saveBook(book); //call the service to save the book
		// The saved book object will be returned as the response	
	}
	
	@GetMapping("/{bookid}") //endpoint to get a book by ID
	public Optional<Book> getBookById(@PathVariable Long bookid) {
		// This method will handle the retrieval of a book by ID
		return bookServices.getBookById(bookid); //call the service to get the book by ID
		// The book object will be returned as the response
	}
	
	@GetMapping("/title/{title}") //endpoint to get a book by title
	public Optional<Book> getBookByTitle(@PathVariable String title) {
		// This method will handle the retrieval of a book by title
		return bookServices.getBookByTitle(title); //call the service to get the book by title
		// The book object will be returned as the response
	}
	
	@GetMapping("/author/{author}") //endpoint to get a book by author
	public Optional<Book> getBookByAuthor(@PathVariable String author) {
		// This method will handle the retrieval of a book by author
		return bookServices.getBookByAuthor(author); //call the service to get the book by author
		// The book object will be returned as the response
	}
	
	@GetMapping("/all") //endpoint to get all books
	public List<Book> getAllBooks() {
		// This method will handle the retrieval of all books
		return bookServices.getAllBooks(); //call the service to get all books
		// A list of book objects will be returned as the response
	}
	
	// Search available books by title or author (for users)
	@GetMapping("/search/user")
	public List<BookDTO> searchBooksForUsers(@RequestParam String query) {
	    if (query == null || query.trim().isEmpty()) {
	        return new ArrayList<>();
	    }
	    return bookServices.searchBooksForUsersDTO(query);
	}
	
	// Get all books with all copies (for librarians)
	@GetMapping("/all/librarian")
	public List<BookDTO> getAllBooksWithCopiesForLibrarians() {
	    return bookServices.getAllBooksWithCopiesForLibrariansDTO();
	}

	// Search books by title or author with all copies (for librarians)
	@GetMapping("/search/librarian")
	public List<BookDTO> searchBooksForLibrarians(@RequestParam String query) {
	    if (query == null || query.trim().isEmpty()) {
	        return new ArrayList<>();
	    }
	    return bookServices.searchBooksForLibrariansDTO(query);
	}
	
	@PutMapping("/update/{bookid}") //endpoint to update a book by ID (pathvariable)
	public Book updateBook(@PathVariable Long bookid, @RequestBody Book book) {
		// This method will handle the update of an existing book
		// It will receive a Book object from the request body
		return bookServices.updateBook(bookid, book); //call the service to update the book
		// The updated book object will be returned as the response
	}
	
	@DeleteMapping("/delete/{bookid}") //endpoint to delete a book by ID
	public void deleteBook(@PathVariable Long bookid) {
		// This method will handle the deletion of a book by ID
		bookServices.deleteBook(bookid); //call the service to delete the book
		// No response body is returned for deletion
	}
	
}
