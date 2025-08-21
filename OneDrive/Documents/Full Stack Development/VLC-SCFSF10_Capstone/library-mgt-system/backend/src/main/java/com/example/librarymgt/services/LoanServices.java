package com.example.librarymgt.services;
//this is business logic layer
//perform CRUD operations on Book data
import com.example.librarymgt.repository.LoanRepository;
import com.example.librarymgt.model.Loan;
import com.example.librarymgt.model.Loanitem;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //indicates that this class is a service component
public class LoanServices {
	
	@Autowired //to inject the LoanRepository bean
	private LoanRepository loanr; //repository to access Loan data
	
	public Loan saveLoan(Loan loan) {
		// Need data: a loan object
		// saveLoan requires Loan details
		// Use LoanRepository to save the loan
		System.out.println("Loan created: " + loan);
		return loanr.save(loan); //returns the saved loan object
	}
	
	public Optional<Loan> getLoanById(Long id) {
		// Use LoanRepository to find the loan by ID
		return loanr.findById(id); //returns the loan if found, otherwise null
	}
	
	public List<Loan> getAllLoans() {
		// Use LoanRepository to find all loans
		return loanr.findAll(); //returns a list of all loans
	}
	
	public List<Loanitem> getLoanItemsByLoanId(Long loanId) {
	    // Find the loan by its ID
		// Find the loan by its ID
	    return loanr.findById(loanId)
	            .map(Loan::getLoanItems) // if present, return the loanItems list
	            .orElseThrow(() -> new RuntimeException("Loan not found with id: " + loanId));
	}
		
	public List<Loanitem> getOverdueItemsByLoanId(Long loanId) {
	    Loan loan = loanr.findById(loanId)
	            .orElseThrow(() -> new RuntimeException("Loan not found with ID: " + loanId));

	    LocalDate today = LocalDate.now();

	    return loan.getLoanItems().stream()
	            .filter(item -> item.getDueDate().isBefore(today) && item.getReturnDate() == null)
	            .toList();
	}
	
	public void deleteLoan(Long id) {
		// Use LoanRepository to delete the loan by ID
		loanr.deleteById(id); //deletes the loan if found
		System.out.println("Loan deleted with ID: " + id);
	}
}
