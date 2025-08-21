package com.example.librarymgt.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.librarymgt.services.LoanServices;
import com.example.librarymgt.model.Loan;
import com.example.librarymgt.model.Loanitem;

@RestController
@RequestMapping("/api/loans")
@CrossOrigin(origins= "http://localhost:5173")
//CrossOrigin to connect to ReactJS frontend
public class LoanController {

	@Autowired //to inject the LoanServices bean
	private LoanServices loanServices; //service to handle business logic
	
	@PostMapping("/create") //endpoint to create a new loan
	public Loan createLoan(@RequestBody Loan loan) {
		// This method will handle the creation of a new loan
		// It will receive a Loan object from the request body
		return loanServices.saveLoan(loan); //call the service to save the loan
		// The saved loan object will be returned as the response	
	}
	
	@GetMapping("/{id}") //endpoint to get a loan by ID
	public Optional<Loan> getLoanById(@PathVariable Long id) {
		// This method will handle the retrieval of a loan by ID
		return loanServices.getLoanById(id); //call the service to get the loan by ID
		// The loan object will be returned as the response
	}
	
	@GetMapping("/{id}/items")
	public List<Loanitem> getLoanItemsByLoanId(@PathVariable Long loanId) {
	    return loanServices.getLoanItemsByLoanId(loanId);
	}
	
	@GetMapping("/all") //endpoint to get all loans
	public List<Loan> getAllLoans() {
		// This method will handle the retrieval of all loans
		return loanServices.getAllLoans(); //call the service to get all loans
		// A list of loan objects will be returned as the response
	}
	
	@DeleteMapping("/delete/{id}") //endpoint to delete a loan by ID (pathvariable)
	public void deleteLoan(@PathVariable Long id) {
		// This method will handle the deletion of an existing loan
		 loanServices.deleteLoan(id); //call the service to delete the loan by ID
		 System.out.println("Deleted Loan with ID: " + id);
		 // No response body is needed for deletion, just confirmation in console log
	}
	
}
