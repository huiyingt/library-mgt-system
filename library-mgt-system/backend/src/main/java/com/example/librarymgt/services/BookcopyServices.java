package com.example.librarymgt.services;
import com.example.librarymgt.repository.BookcopyRepository;
import com.example.librarymgt.model.Bookcopy;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //indicates that this class is a service component
public class BookcopyServices {

	@Autowired //to inject the BookcopyRepository bean
	private BookcopyRepository bookcopyr; //repository to access Bookcopy data
	
	public Bookcopy saveBookcopy(Bookcopy bookcopy) {
		// Need data: a bookcopy object
		// saveBookcopy requires Bookcopy details
		// Use BookcopyRepository to save the bookcopy
		System.out.println("Book copy added: " + bookcopy);
		return bookcopyr.save(bookcopy); //returns the saved bookcopy object
	}
	
	public Optional<Bookcopy> getBookcopyById(Long id) {
		// Use BookcopyRepository to find the book copy by ID
		return bookcopyr.findById(id); //returns the book copy if found, otherwise null
	}
	
	public Optional<Bookcopy> getBookcopyByBookId(Long bookId) {
		// Use BookcopyRepository to find the book copy by Book ID
		return bookcopyr.findbyBookId(bookId); //returns the book copy if found, otherwise null
	}
	
	public Optional<Bookcopy> getBookcopyByStatus(Bookcopy.Status status) {
		// Use BookcopyRepository to find the book copy by Status
		return bookcopyr.findByStatus(status); //returns the book copy if found, otherwise null
	}
	
	public List<Bookcopy> getAllBookcopies() {
		// Use BookcopyRepository to find all book copies
		return bookcopyr.findAll(); //returns a list of all book copies
	}
	
	public Bookcopy updateBookcopy(Long id, Bookcopy bookcopyDetails) {
		// Use BookcopyRepository to find the book copy by ID
		Bookcopy existingBookcopy = bookcopyr.findById(id)
				.orElseThrow(() -> new RuntimeException("Book copy not found with ID: " + id));
		// Update the existing book copy's status 
		if (bookcopyDetails.getStatus() != null) //check if status is not null
			existingBookcopy.setStatus(bookcopyDetails.getStatus());
		// Save the updated book copy
		System.out.println("Book copy updated: " + existingBookcopy);
		return bookcopyr.save(existingBookcopy); //returns the updated book copy object
		}
	
	public void deleteBookcopy(Long id) {
		bookcopyr.deleteById(id); //delete the book copy by ID
	}
}
