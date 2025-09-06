package com.example.librarymgt.services;
//this is business logic layer
//perform CRUD operations on Book data
import com.example.librarymgt.repository.LoanRepository;
import com.example.librarymgt.model.Loan;
import com.example.librarymgt.model.Loanitem;
import com.example.librarymgt.model.User;
import com.example.librarymgt.repository.UserRepository;
import com.example.librarymgt.dto.BookcopyDTO;
import com.example.librarymgt.dto.LoanDTO;
import com.example.librarymgt.dto.LoanitemDTO;
import com.example.librarymgt.model.Bookcopy;
import com.example.librarymgt.repository.BookcopyRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //indicates that this class is a service component
public class LoanServices {
	
	@Autowired //to inject the LoanRepository bean
	private LoanRepository loanr; //repository to access Loan data
	
	@Autowired
	private UserRepository userr;
	
	@Autowired
	private BookcopyRepository bookcopyRepository;
	
	public Loan saveLoan(Loan loan) {
		// Need data: a loan object
		// saveLoan requires Loan details
		// Use LoanRepository to save the loan
		System.out.println("Loan created: " + loan);
		return loanr.save(loan); //returns the saved loan object
	}
	
	public LoanDTO createLoanFromDTO(LoanDTO loanDTO) {
	    User user = userr.findByUsername(loanDTO.getUsername())
	    		.orElseThrow(() -> new RuntimeException("User not found: " + loanDTO.getUsername()));
	    
	    Loan loan = new Loan();
	    loan.setUser(user);
	    loan.setBorrowDate(loanDTO.getBorrowDate() != null ? loanDTO.getBorrowDate() : LocalDate.now());

	    // Map LoanitemDTO to Loanitem
	    for (LoanitemDTO itemDTO : loanDTO.getLoanItems()) {
	        Long copyId = itemDTO.getBookCopy().getCopyId();  // or copyNumber if you prefer
	        Bookcopy copy = bookcopyRepository.findById(copyId)
	                .orElseThrow(() -> new RuntimeException("Book copy not found with ID: " + copyId));

	        // Update status of Bookcopy to BORROWED
	        copy.setStatus(Bookcopy.Status.BORROWED);
	        bookcopyRepository.save(copy);

	        Loanitem item = new Loanitem();
	        item.setLoan(loan);
	        item.setBookCopy(copy);
	        item.setDueDate(itemDTO.getDueDate() != null ? itemDTO.getDueDate() : LocalDate.now().plusDays(14));
	        // Set renewalCount to "0" if null
	        item.setRenewalCount(itemDTO.getRenewalCount() != null ? itemDTO.getRenewalCount() : "0");
	        loan.getLoanItems().add(item);
	    }

	    loan = loanr.save(loan);
	    
	    return mapToLoanDTO(loan);

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


	//--- DTO Mapping Methods ---
	// DTO mapping method for Loan
	public LoanDTO mapToLoanDTO(Loan loan) {
		List<LoanitemDTO> itemDTOs = loan.getLoanItems()
				.stream()
                .map(item -> mapToLoanitemDTO(item))
                .toList();
		return new LoanDTO(
            loan.getId(),
            loan.getUser().getUsername(),
            loan.getBorrowDate(),
            itemDTOs
	);
	}


	// DTO mapping method for Loanitem
    public LoanitemDTO mapToLoanitemDTO(Loanitem item) {
        BookcopyDTO copyDTO = new BookcopyDTO(
        		item.getBookCopy().getId(),
                item.getBookCopy().getCopyNumber(),
                item.getBookCopy().getStatus().name(),
                item.getBookCopy().getBook().getId(),
                item.getBookCopy().getBook().getTitle()
        );

        return new LoanitemDTO(
                item.getId(),
                item.getDueDate(),
                item.getReturnDate(),
                item.getRenewalCount(),
                item.getFineAmount(),
                copyDTO
        );
    }
}
