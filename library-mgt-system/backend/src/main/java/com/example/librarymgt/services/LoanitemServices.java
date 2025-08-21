package com.example.librarymgt.services;
//this is business logic layer
//perform CRUD operations on Book data
import com.example.librarymgt.repository.LoanitemRepository;
import com.example.librarymgt.model.Loanitem;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //indicates that this class is a service component
public class LoanitemServices {

	@Autowired //to inject the LoanitemRepository bean
	private LoanitemRepository loanitemr; //repository to access Loanitem data
	
	// Method to save a new loanitem
	public Loanitem saveLoanitem(Loanitem loanitem) {
		// Need data: a loanitem object
		// saveLoanitem requires Loanitem details
		// Use LoanitemRepository to save the loanitem
		System.out.println("Loan item created: " + loanitem);
		return loanitemr.save(loanitem); //returns the saved loanitem object
	}
	
	// Method to get a loanitem by ID
	public Optional<Loanitem> getLoanitemById(Long id) {
		// Use LoanitemRepository to find the loanitem by ID
		return loanitemr.findById(id); //returns the loanitem if found, otherwise null
	}
	
	// Method to get a loanitem by due date
	public Optional<Loanitem> getLoanitemByDueDate(LocalDate dueDate) {
		// Use LoanitemRepository to find the loanitem by Loan ID
		return loanitemr.findByDueDate(dueDate); //returns the loanitem if found, otherwise null
	}
	
	// Method to get all loanitems by loan ID
	public List<Loanitem> getLoanitemsByLoanId(Long loanId) {
		// Use LoanitemRepository to find all loanitems by Loan ID
		return loanitemr.findbyLoanId(loanId); //returns a list of loanitems for the specified loan
	}
	
	// Method to get all loanitems
	public List<Loanitem> getAllLoanitems() {
		// Use LoanitemRepository to find all loanitems
		return loanitemr.findAll(); //returns a list of all loanitems
	}
	
	// Method to get overdue loanitems
	public List<Loanitem> getOverdueLoanitems() {
	    LocalDate today = LocalDate.now();
	    return loanitemr.findAll().stream()
	            .filter(item -> item.getDueDate().isBefore(today) && item.getReturnDate() == null)
	            .toList();
	}

	// Method to update a loanitem by ID
	public Loanitem updateLoanitem(Long id, Loanitem loanitemDetails) {
		// Use LoanitemRepository to find the loanitem by ID
		Loanitem existingLoanitem = loanitemr.findById(id)
				.orElseThrow(() -> new RuntimeException("Loan item not found with ID: " + id));
		// Update the existing loanitem's details
		if (loanitemDetails.getDueDate() != null) //check if due date is not null
			existingLoanitem.setDueDate(loanitemDetails.getDueDate());
		if (loanitemDetails.getReturnDate() != null) //check if return date is not null
			existingLoanitem.setReturnDate(loanitemDetails.getReturnDate());
		// Save the updated loanitem
		return loanitemr.save(existingLoanitem); //returns the updated loanitem object
	}
	
	// Method to renew a loan item
	public Loanitem renewLoan(Long id) {
	    Loanitem loanitem = loanitemr.findById(id)
	        .orElseThrow(() -> new RuntimeException("Loan item not found with ID: " + id));

	    // Business rule: max 2 renewals allowed
	    if (loanitem.getRenewalCount() >= 2) {
	        throw new RuntimeException("Maximum renewals reached for this loan item.");
	    }

	    // Extend due date by 14 days for each renewal
	    loanitem.setDueDate(loanitem.getDueDate().plusDays(14));
	    loanitem.setRenewalCount(loanitem.getRenewalCount() + 1);

	    return loanitemr.save(loanitem);
	}
	
	// Method to calculate fine based on overdue days
	public Loanitem calculateFine(Long id) {
	    Loanitem loanitem = loanitemr.findById(id)
	        .orElseThrow(() -> new RuntimeException("Loan item not found with ID: " + id));

	    long overdueDays = 0;

	    if (loanitem.getReturnDate() != null) {
	        // Book is returned, check overdue against return date
	        overdueDays = ChronoUnit.DAYS.between(loanitem.getDueDate(), loanitem.getReturnDate());
	        overdueDays = Math.max(overdueDays, 0); // Ensure no negative fine
	    } else {
	        // Book not yet returned, check overdue up to today
	        overdueDays = ChronoUnit.DAYS.between(loanitem.getDueDate(), LocalDate.now());
	    }

	    if (overdueDays > 0) {
	        double fine = overdueDays * 0.50; // Fine is $0.50 per day
	        fine = Math.min(fine, 20.0); // Cap fine at $20.00
	        loanitem.setFineAmount(fine);
	    } else {
	        loanitem.setFineAmount(0.0);
	    }
	    return loanitemr.save(loanitem);
	}

	// Method to delete a loanitem by ID
	public void deleteLoanitem(Long id) {
		// Use LoanitemRepository to delete the loanitem by ID
		loanitemr.deleteById(id); //deletes the loanitem if found
		System.out.println("Loan item deleted with ID: " + id);
	}
}
