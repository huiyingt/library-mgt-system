package com.example.librarymgt.services;
//this is business logic layer
import com.example.librarymgt.repository.LoanitemRepository;
import com.example.librarymgt.model.Bookcopy;
import com.example.librarymgt.model.Loan;
import com.example.librarymgt.model.Loanitem;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //indicates that this class is a service component
public class LoanitemServices {

	@Autowired //to inject the LoanitemRepository bean
	private LoanitemRepository loanitemr; //repository to access Loanitem data
	
	@Autowired
	private BookcopyServices bookcopyServices;
	
	// Method to save a new loanitem
	public Loanitem saveLoanitem(Loanitem loanitem) {
		System.out.println("Loan item created: " + loanitem);
		return loanitemr.save(loanitem); //returns the saved loanitem object
	}
	
	public Loanitem borrowBook(Loan loan, Bookcopy bookCopy) {
		if (bookCopy.getStatus() == Bookcopy.Status.BORROWED) {
	        throw new RuntimeException("Book copy is already borrowed.");
	    }
		
	    Loanitem item = new Loanitem();
	    item.setLoan(loan);
	    item.setBookCopy(bookCopy);
	    item.setDueDate(LocalDate.now().plusDays(14)); // default 14-day loan period
	    item.setRenewalCount("0");
	    item.setReturnDate(null);
	    item.setFineAmount(0.0);
	    
	    // Update book copy status
	    bookCopy.setStatus(Bookcopy.Status.BORROWED);
	    bookcopyServices.updateBookcopy(bookCopy.getId(), bookCopy);
	    
	    return loanitemr.save(item);
	}
	
	// Method to get a loanitem by ID
	public Optional<Loanitem> getLoanitemById(Long id) {
		// Use LoanitemRepository to find the loanitem by ID
		return loanitemr.findById(id); //returns the loanitem if found, otherwise null
	}
	
	// Method to get all loanitems by loan ID
	public List<Loanitem> getLoanitemsByLoanId(Long loanId) {
		// Use LoanitemRepository to find all loanitems by Loan ID
		return loanitemr.findByLoanId(loanId); //returns a list of loanitems for the specified loan
	}
	
	//Method to get all active loans for a user
	public List<Loanitem> getActiveLoansByUsername(String username) {
	    return loanitemr.findByLoanUserUsernameAndReturnDateIsNull(username);
	}
	
	// Method to get all loanitems
	public List<Loanitem> getAllLoanitems() {
		// Use LoanitemRepository to find all loanitems
		return loanitemr.findAll(); //returns a list of all loanitems
	}
	
	// Method to get overdue loanitems
	public List<Loanitem> getOverdueLoanitems() {
	    return loanitemr.findAll().stream()
	    		.filter(Loanitem::isOverdue)
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
		if (loanitemDetails.getRenewalCount() != null) { //check if renewal count is not null
			int renewalCountInt = Integer.parseInt(loanitemDetails.getRenewalCount());
		    if (renewalCountInt >= 0) {
		        existingLoanitem.setRenewalCount(String.valueOf(renewalCountInt));
		    }
        }
        if (loanitemDetails.getFineAmount() >= 0) {
            existingLoanitem.setFineAmount(loanitemDetails.getFineAmount());
        }

		// Save the updated loanitem
		return loanitemr.save(existingLoanitem); //returns the updated loanitem object
	}
	
	// Method to return book
	public Loanitem returnBook(Long id) {
	    Loanitem item = loanitemr.findById(id)
	        .orElseThrow(() -> new RuntimeException("Loan item not found with ID: " + id));
	    if (item.getReturnDate() != null) {
	        throw new RuntimeException("This book has already been returned.");
	    }
	    
	    item.setReturnDate(LocalDate.now());
	    item.calculateFine(); // Update fine if overdue
	    
	    // Update book copy status
	    Bookcopy bookCopy = item.getBookCopy();
	    bookCopy.setStatus(Bookcopy.Status.AVAILABLE);
	    bookcopyServices.updateBookcopy(bookCopy.getId(), bookCopy);
	    
	    return loanitemr.save(item);
	}
	
	// Calculate fine for a loan item
    public Loanitem calculateFine(Long id) {
        Loanitem item = loanitemr.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan item not found with ID: " + id));
        item.calculateFine();           // call entity method
        return loanitemr.save(item);
    }
	
	// Method to renew a loan item
	public Loanitem renewLoan(Long id) {
        Loanitem item = loanitemr.findById(id)
                .orElseThrow(() -> new RuntimeException("Loan item not found with ID: " + id));
        
        Bookcopy bookCopy = item.getBookCopy();
        if (bookCopy.getStatus() != Bookcopy.Status.BORROWED) {
            throw new RuntimeException("Cannot renew: book copy is not currently borrowed.");
        }
        
        item.renew();                  // call entity method
        return loanitemr.save(item);
    }
	
	// Method to check if book can be renewed
	public boolean canRenew(Long id) {
	    Loanitem item = loanitemr.findById(id)
	        .orElseThrow(() -> new RuntimeException("Loan item not found with ID: " + id));
	    return item.canRenew();
	}
	

	// Method to delete a loanitem by ID
	public void deleteLoanitem(Long id) {
		// Use LoanitemRepository to delete the loanitem by ID
		loanitemr.deleteById(id); //deletes the loanitem if found
		System.out.println("Loan item deleted with ID: " + id);
	}
}
