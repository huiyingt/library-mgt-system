package com.example.librarymgt.controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.librarymgt.services.BookcopyServices;
import com.example.librarymgt.model.Bookcopy;
import com.example.librarymgt.dto.BookcopyDTO;


@RestController
@RequestMapping("/api/bookcopies") //base URL for book copy-related endpoints
@CrossOrigin(origins= "http://localhost:5173")
//CrossOrigin to connect to ReactJS frontend
public class BookcopyController {

	@Autowired
    private BookcopyServices bookcopyServices;

    // GET all book copies
    @GetMapping("/all")
    public List<BookcopyDTO> getAllBookcopies() {
    			// This method will handle the retrieval of all book copies
		return bookcopyServices.getAllBookcopies(); //call the service to get all book copies
		// A list of BookcopyDTO objects will be returned as the response
    }
    
    // GET a book copy by ID
    @GetMapping("/{copyid}")
    public BookcopyDTO getBookcopyById(@PathVariable Long copyid) {
    			// This method will handle the retrieval of a book copy by ID
		return bookcopyServices.getBookcopyById(copyid); //call the service to get the book copy by ID
		// The BookcopyDTO object will be returned as the response
    }
    
    // GET book copies by book ID
    @GetMapping("/book/{bookId}")
    public List<BookcopyDTO> getBookcopiesByBookId(@PathVariable Long bookId) {
    			// This method will handle the retrieval of book copies by book ID
    			return bookcopyServices.getBookcopiesByBookId(bookId); //call the service to get book copies by book ID
    					// A list of BookcopyDTO objects will be returned as the response
    }
    	
    // POST a new book copy
    @PostMapping("/create")
    public BookcopyDTO createBookcopy(@RequestBody Bookcopy bookcopy) {
    			// This method will handle the creation of a new book copy
		// It will receive a Bookcopy object from the request body
		return bookcopyServices.createBookcopy(bookcopy); //call the service to save the book copy
		// The saved BookcopyDTO object will be returned as the response
    }
    
    // PUT update an existing book copy by ID
    @PutMapping("/update/{copyid}")
    public BookcopyDTO updateBookcopy(@PathVariable Long copyid, @RequestBody Bookcopy updatedBookcopy) {
				// This method will handle the update of an existing book copy
    			// It will receive a Bookcopy object from the request body
    			return bookcopyServices.updateBookcopy(copyid, updatedBookcopy); //call the service to update the book copy
    					// The updated BookcopyDTO object will be returned as the response
    }
    
    // DELETE a book copy by ID
    @DeleteMapping("/delete/{copyid}")
    public void deleteBookcopy(@PathVariable Long copyid) {
				// This method will handle the deletion of a book copy by ID
    			bookcopyServices.deleteBookcopy(copyid); //call the service to delete the book copy
		// No response body is returned, just a 204 No Content status
    			System.out.println("Book copy with ID " + copyid + " deleted successfully.");
    }
    
    
}