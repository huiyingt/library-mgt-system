package com.example.librarymgt.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.librarymgt.services.BookcopyServices;
import com.example.librarymgt.services.LoanServices;
import com.example.librarymgt.services.LoanitemServices;
import com.example.librarymgt.dto.LoanitemDTO;
import com.example.librarymgt.mapper.LoanitemMapper;
import com.example.librarymgt.model.Bookcopy;
import com.example.librarymgt.model.Loan;
import com.example.librarymgt.model.Loanitem;

@RestController
@RequestMapping("/api/loanitems")
@CrossOrigin(origins= "http://localhost:5173")

//CrossOrigin to connect to ReactJS frontend
public class LoanitemController {
	
	@Autowired //to inject the LoanServices bean
	private LoanitemServices loanitemServices; //service to handle business logic
	
	@Autowired
	private LoanServices loanServices; // Service to fetch Loan

	@Autowired
	private BookcopyServices bookcopyServices; // Service to fetch Bookcopy
	
	// Create a new loan item
    @PostMapping("/create")
    public ResponseEntity<LoanitemDTO> createLoanitem(@RequestBody Loanitem loanitem) {
        Loanitem created = loanitemServices.saveLoanitem(loanitem);
        return new ResponseEntity<>(LoanitemMapper.toLoanitemDTO(created), HttpStatus.CREATED);
    }
	
    //borrow a book
    @PostMapping("/borrow")
    public ResponseEntity<LoanitemDTO> borrowBook(@RequestParam Long loanId, @RequestParam Long copyId) {
        // Fetch Loan entity
        Optional<Loan> loanOpt = loanServices.getLoanById(loanId);
        if (loanOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Loan not found
        }

        // Fetch Bookcopy entity
        Optional<Bookcopy> copyOpt = bookcopyServices.getBookcopyEntityById(copyId);
        if (copyOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Book copy not found
        }

        // Borrow the book
        Loanitem item = loanitemServices.borrowBook(loanOpt.get(), copyOpt.get());

        // Convert to DTO
        LoanitemDTO dto = LoanitemMapper.toLoanitemDTO(item);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    
    // Get loan item by ID
    @GetMapping("/{id}")
    public ResponseEntity<LoanitemDTO> getLoanitemById(@PathVariable Long id) {
        Optional<Loanitem> item = loanitemServices.getLoanitemById(id);
        return item.map(loanitem -> new ResponseEntity<>(LoanitemMapper.toLoanitemDTO(loanitem), HttpStatus.OK))
                   .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get all loan items
    @GetMapping("/all")
    public ResponseEntity<List<LoanitemDTO>> getAllLoanitems() {
    	List<LoanitemDTO> dtos = loanitemServices.getAllLoanitems()
    			.stream()
                .map(LoanitemMapper::toLoanitemDTO)
                .toList();
    	return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    
    // Get all loan items by username
    @GetMapping("/loan/{loanId}")
    public ResponseEntity<List<LoanitemDTO>> getLoanitemsByLoanId(@PathVariable Long loanId) {
        List<LoanitemDTO> dtos = loanitemServices.getLoanitemsByLoanId(loanId)
            .stream()
            .map(LoanitemMapper::toLoanitemDTO)
            .toList();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    
	// Get all active loans by username
    @GetMapping("/user/{username}")
    public ResponseEntity<List<LoanitemDTO>> getActiveLoansByUsername(
    		@PathVariable String username) {	
        List<LoanitemDTO> dtos = loanitemServices.getActiveLoansByUsername(username)
            .stream()
            .map(LoanitemMapper::toLoanitemDTO)
            .toList();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    // Get all overdue loan items
    @GetMapping("/overdue")
    public ResponseEntity<List<LoanitemDTO>> getOverdueLoanitems() {
    	List<LoanitemDTO> dtos = loanitemServices.getOverdueLoanitems()
    			.stream()
                .map(LoanitemMapper::toLoanitemDTO)
                .toList();
    	return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
	
	// Check if book can be renewed
    @GetMapping("/renew/{id}")
    public ResponseEntity<Boolean> canRenew(@PathVariable Long id) {
        return new ResponseEntity<>(loanitemServices.canRenew(id), HttpStatus.OK);
    }
	
    // Update a loan item
    @PutMapping("/update/{id}")
    public ResponseEntity<LoanitemDTO> updateLoanitem(@PathVariable Long id, @RequestBody Loanitem loanitem) {
    	Loanitem updated = loanitemServices.updateLoanitem(id, loanitem);
        return new ResponseEntity<>(LoanitemMapper.toLoanitemDTO(updated), HttpStatus.OK);
    }

    // Return a book
    @PutMapping("/return/{id}")
    public ResponseEntity<LoanitemDTO> returnBook(@PathVariable Long id) {
        Loanitem item = loanitemServices.returnBook(id);
        return new ResponseEntity<>(LoanitemMapper.toLoanitemDTO(item), HttpStatus.OK);
    }

    // Renew a loan
    @PutMapping("/renew/{id}")
    public ResponseEntity<LoanitemDTO> renewLoan(@PathVariable Long id) {
        Loanitem item = loanitemServices.renewLoan(id);
        return new ResponseEntity<>(LoanitemMapper.toLoanitemDTO(item), HttpStatus.OK);
    }

    // Calculate fine
    @PutMapping("/fine/{id}")
    public ResponseEntity<LoanitemDTO> calculateFine(@PathVariable Long id) {
        Loanitem item = loanitemServices.calculateFine(id);
        return new ResponseEntity<>(LoanitemMapper.toLoanitemDTO(item), HttpStatus.OK);
    }
    
	@DeleteMapping("/delete/{id}") //endpoint to delete a loanitem by ID
	public ResponseEntity<Void> deleteLoanitem(@PathVariable Long id) {
		// This method will handle the deletion of a loanitem by ID
		loanitemServices.deleteLoanitem(id); //call the service to delete the loanitem by ID
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); //return NO_CONTENT status for successful deletion
	}
	
}