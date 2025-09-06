package com.example.librarymgt.services;	
//this is business logic layer
//perform CRUD operations on Book data
import com.example.librarymgt.repository.BookRepository;
import com.example.librarymgt.model.Book;
import com.example.librarymgt.model.Bookcopy;
import com.example.librarymgt.dto.BookDTO;
import com.example.librarymgt.dto.BookcopyDTO;

import java.time.LocalDate;
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
	
	public Optional<Book> getBookById(Long bookid) {
		// Use BookRepository to find the book by ID
		return bookr.findById(bookid); //returns the book if found, otherwise null
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

	// ------------------- USER ENDPOINTS -------------------
	// Search available books by title or author (case-insensitive)
	public List<BookDTO> searchBooksForUsersDTO(String query) {
        List<Book> books = bookr.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(query, query);

        return books.stream()
            .map(book -> {
                List<BookcopyDTO> availableCopies = book.getCopies().stream()
                    .filter(c -> c.getStatus() == Bookcopy.Status.AVAILABLE)
                    .map(c -> new BookcopyDTO(
                    		c.getId(),
                    		c.getCopyNumber(), 
                    		c.getStatus().name(), 
                    		c.getBook().getId(), 
                    		c.getBook().getTitle()
                    		))
                    .toList();
                
             // Skip books with no available copies
                if (availableCopies.isEmpty()) return null;

                // Map Book entity to BookDTO
                BookDTO dto = new BookDTO();
                dto.setIsbn(book.getIsbn());
                dto.setTitle(book.getTitle());
                dto.setAuthor(book.getAuthor());
                dto.setCategory(book.getCategory().name());
                dto.setPublicationYear(book.getPublicationYear());
                dto.setCopies(availableCopies);

                return dto;
            })
            .filter(dto -> dto != null)
            .toList();
    }
	
	// ------------------- LIBRARIAN ENDPOINTS -------------------
	// For librarians: show all books with all copies (DTO)
	public List<BookDTO> getAllBooksWithCopiesForLibrariansDTO() {
	    List<Book> books = bookr.findAllWithCopies();

	    return books.stream()
	        .map(book -> {
	            List<BookcopyDTO> copies = book.getCopies().stream()
	                .map(c -> new BookcopyDTO(
	                		c.getId(),
	                		c.getCopyNumber(), 
	                		c.getStatus().name(), 
	                		c.getBook().getId(), 
	                		c.getBook().getTitle()
	                		))
	                .toList();

	            BookDTO dto = new BookDTO();
	            dto.setIsbn(book.getIsbn());
	            dto.setTitle(book.getTitle());
	            dto.setAuthor(book.getAuthor());
	            dto.setCategory(book.getCategory().name());
	            dto.setPublicationYear(book.getPublicationYear());
	            dto.setCopies(copies);

	            return dto;
	        })
	        .toList();
	}

	// For librarians: search books by title or author (case-insensitive) with all copies (DTO)
	public List<BookDTO> searchBooksForLibrariansDTO(String query) {
	    List<Book> books = bookr.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(query, query);

	    return books.stream()
	        .map(book -> {
	            List<BookcopyDTO> copies = book.getCopies().stream()
	                .map(c -> new BookcopyDTO(
	                		c.getId(),
	                		c.getCopyNumber(), 
	                		c.getStatus().name(), 
	                		c.getBook().getId(), 
	                		c.getBook().getTitle()
	                		))
	                .toList();

	            BookDTO dto = new BookDTO();
	            dto.setIsbn(book.getIsbn());
	            dto.setTitle(book.getTitle());
	            dto.setAuthor(book.getAuthor());
	            dto.setCategory(book.getCategory().name());
	            dto.setPublicationYear(book.getPublicationYear());
	            dto.setCopies(copies);

	            return dto;
	        })
	        .toList();
	}
	
	//update book details
	public Book updateBook(Long bookid, Book bookDetails) {
		// Use BookRepository to find the book by ID
		Book existingBook = bookr.findById(bookid)
				.orElseThrow(() -> new RuntimeException("Book not found with ID: " + bookid));
		// Update the existing book's details
		if (bookDetails.getIsbn() != null) //check if ISBN is not null
			existingBook.setIsbn(bookDetails.getIsbn());
		if (bookDetails.getTitle() != null) //check if title is not null
			existingBook.setTitle(bookDetails.getTitle());
		if (bookDetails.getAuthor() != null) //check if author is not null
			existingBook.setAuthor(bookDetails.getAuthor());	
		if (bookDetails.getCategory() != null) //check if category is not null
			existingBook.setCategory(bookDetails.getCategory());
		if (bookDetails.getPublicationYear() != null) {
		    Integer year = bookDetails.getPublicationYear();
		    if (year >= 1000 && year <= LocalDate.now().getYear()) {
		        existingBook.setPublicationYear(year);
		    } else {
		        throw new IllegalArgumentException("Invalid publication year: " + year);
		    }
		}
		// Save the updated book
		System.out.println("Book updated: " + existingBook);
		return bookr.save(existingBook); //returns the updated book object	
	}
	
	//delete book by ID
	public void deleteBook(Long bookid) {
		// Use BookRepository to delete the book by ID
		if (bookr.existsById(bookid)) { //check if book exists
			bookr.deleteById(bookid); //delete the book
			System.out.println("Book with ID " + bookid + " has been deleted.");
		} else {
			System.out.println("Book with ID " + bookid + " does not exist.");
		}
	}
}
