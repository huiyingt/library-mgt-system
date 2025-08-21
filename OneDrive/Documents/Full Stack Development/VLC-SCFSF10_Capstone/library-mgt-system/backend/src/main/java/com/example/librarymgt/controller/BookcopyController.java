package com.example.librarymgt.controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.librarymgt.services.BookcopyServices;
import com.example.librarymgt.model.Bookcopy;

@RestController
@RequestMapping("/api/bookcopies") //base URL for book copy-related endpoints
@CrossOrigin(origins= "http://localhost:5173")
//CrossOrigin to connect to ReactJS frontend
public class BookcopyController {

	@Autowired //to inject the BookcopyServices bean
	private BookcopyServices bookcopyServices; //service to handle business logic
	
	@PostMapping("/create") //endpoint to create a new book copy
	public Bookcopy createBookcopy(@RequestBody Bookcopy bookcopy) {
		// This method will handle the creation of a new book copy
		// It will receive a Bookcopy object from the request body
		return bookcopyServices.saveBookcopy(bookcopy); //call the service to save the book copy
		// The saved book copy object will be returned as the response	
	}
	
	@GetMapping("/{id}") //endpoint to get a book copy by ID
	public Optional<Bookcopy> getBookcopyById(@PathVariable Long copyid) {
		// This method will handle the retrieval of a book copy by ID
		return bookcopyServices.getBookcopyById(copyid); //call the service to get the book copy by ID
		// The book copy object will be returned as the response
	}
	
	@GetMapping("/book/{bookId}") //endpoint to get a book copy by Book ID
	public Optional<Bookcopy> getBookcopyByBookId(@PathVariable Long bookId) {
		// This method will handle the retrieval of a book copy by Book ID
		return bookcopyServices.getBookcopyByBookId(bookId); //call the service to get the book copy by Book ID
		// The book copy object will be returned as the response
	}
	
	@GetMapping("/status/{status}") //endpoint to get a book copy by Status
	public Optional<Bookcopy> getBookcopyByStatus(@PathVariable Bookcopy.Status status) {
		// This method will handle the retrieval of a book copy by Status
		return bookcopyServices.getBookcopyByStatus(status); //call the service to get the book copy by Status
		// The book copy object will be returned as the response
	}
	
	@GetMapping("/all") //endpoint to get all book copies
	public List<Bookcopy> getAllBookcopies() {
		// This method will handle the retrieval of all book copies
		return bookcopyServices.getAllBookcopies(); //call the service to get all book copies
		// A list of book copy objects will be returned as the response
	}
	
	@PutMapping("/update/{id}") //endpoint to update a book copy by ID (pathvariable)
	public Bookcopy updateBookcopy(@PathVariable Long copyid, @RequestBody Bookcopy bookcopy) {
		// This method will handle the update of an existing book copy
		// It will receive a Bookcopy object from the request body
		return bookcopyServices.updateBookcopy(copyid, bookcopy); //call the service to update the book copy
		// The updated book copy object will be returned as the response
	}
	
	@DeleteMapping("/delete/{id}") //endpoint to delete a book copy by ID
	public void deleteBookcopy(@PathVariable Long copyid) {
		// This method will handle the deletion of a book copy by ID
		bookcopyServices.deleteBookcopy(copyid); //call the service to delete the book copy by ID
		// No response body is returned for deletion
	}
}
