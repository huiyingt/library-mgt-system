package com.example.librarymgt.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.example.librarymgt.services.LoanServices;
import com.example.librarymgt.model.Loan;
import com.example.librarymgt.model.Loanitem;
import com.example.librarymgt.dto.LoanDTO;
import com.example.librarymgt.dto.LoanitemDTO;

@RestController
@RequestMapping("/api/loans")
@CrossOrigin(origins= "http://localhost:5173")
//CrossOrigin to connect to ReactJS frontend
public class LoanController {

	@Autowired //to inject the LoanServices bean
	private LoanServices loanServices; //service to handle business logic
	
	// Create a new loan (used internally)
	@PostMapping("/create")
    public LoanDTO createLoan(@RequestBody Loan loan) {
        Loan savedLoan = loanServices.saveLoan(loan);
        return loanServices.mapToLoanDTO(savedLoan);
    }
	
	// Borrow books for a user (creates a loan)
	@PostMapping("/borrow")
    public ResponseEntity<LoanDTO> borrowBook(@RequestBody LoanDTO loanDTO) {
		LoanDTO savedLoan = loanServices.createLoanFromDTO(loanDTO);
	    return ResponseEntity.ok(savedLoan);
	}
	
	// Get a loan by ID
    @GetMapping("/{id}")
    public ResponseEntity<LoanDTO> getLoanById(@PathVariable Long id) {
        Optional<Loan> optionalLoan = loanServices.getLoanById(id);
        if (optionalLoan.isPresent()) {
            return ResponseEntity.ok(loanServices.mapToLoanDTO(optionalLoan.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get all loans (for librarians)
    @GetMapping("/all")
    public List<LoanDTO> getAllLoans() {
        return loanServices.getAllLoans()
                .stream()
                .map(loanServices::mapToLoanDTO)
                .toList();
    }

    // Get all loan items for a specific loan
    @GetMapping("/{id}/items")
    public List<LoanitemDTO> getLoanItemsByLoanId(@PathVariable Long id) {
        List<Loanitem> loanItems = loanServices.getLoanItemsByLoanId(id);
        return loanItems.stream()
                .map(loanServices::mapToLoanitemDTO)
                .toList();
    }

    // Get overdue items for a specific loan
    @GetMapping("/{id}/overdue-items")
    public List<LoanitemDTO> getOverdueItemsByLoanId(@PathVariable Long id) {
        List<Loanitem> overdueItems = loanServices.getOverdueItemsByLoanId(id);
        return overdueItems.stream()
                .map(loanServices::mapToLoanitemDTO)
                .toList();
    }

    // Delete a loan by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
        loanServices.deleteLoan(id);
        return ResponseEntity.noContent().build();
    }
}