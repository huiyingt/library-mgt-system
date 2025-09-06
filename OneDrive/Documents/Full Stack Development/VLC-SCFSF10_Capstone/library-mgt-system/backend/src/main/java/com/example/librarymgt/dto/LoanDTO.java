package com.example.librarymgt.dto;

import java.util.List;
import java.time.LocalDate;

public class LoanDTO {
    private Long loanId;
    private String username;          // User who borrowed
    private LocalDate borrowDate;       // Date when the book was borrowed
    private List<LoanitemDTO> loanItems;

    public LoanDTO(Long loanId, String username, LocalDate borrowDate,  List<LoanitemDTO> loanItems) {
        this.loanId = loanId;
        this.username = username;
        this.borrowDate = borrowDate;
        this.loanItems = loanItems;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public LocalDate getBorrowDate() {
		return borrowDate;
	}
    
    public void setBorrowDate(LocalDate borrowDate) {
    	this.borrowDate = borrowDate;
    }
    
    public List<LoanitemDTO> getLoanItems() {
        return loanItems;
    }

    public void setLoanItems(List<LoanitemDTO> loanItems) {
        this.loanItems = loanItems;
    }
}
