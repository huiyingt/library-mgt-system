package com.example.librarymgt.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name="loanitems")//for SQL database

public class Loanitem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto-generated 
	@Column(name = "LoanItemId")
	private Long id; //a new ID will be generated automatically for each new loan item
	
	@ManyToOne(fetch = FetchType.LAZY)  
	@JoinColumn(name = "LoanId", nullable = false) // FK in LoanItems table
	private Loan loan; // The loan this item belongs to
	
	@ManyToOne(fetch = FetchType.LAZY)  
	@JoinColumn(name = "CopyId", nullable = false) // FK in LoanItems table
	private Bookcopy bookCopy; // The book copy that was borrowed
	
	@Column(name = "DueDate", nullable = false)
	private LocalDate dueDate; // Due date for returning the book copy
	
	@Column (name = "ReturnDate", nullable = true)
	private LocalDate returnDate; // Return date of the book copy, can be null if not returned yet
	
	@Column(name = "RenewalCount", nullable = false)
	private int renewalCount; // Number of times the loan has been renewed, default is 0
	
	@Column(name = "FineAmt", nullable = true)
	private double fineAmount; // Fine amount for overdue books, can be null if no fine is applied
	
	// Constants for business rules
    private static final int RENEWAL_PERIOD_DAYS = 14; 
    private static final int MAX_RENEWALS = 2;
    private static final double FINE_PER_DAY = 0.50; 
    
    
	// Constructors, getters, and setters
	public Loanitem() {
		super();
	}

	public Loanitem(Loan loan, Bookcopy bookCopy, LocalDate dueDate, LocalDate returnDate, int renewalCount, double fineAmount) {
		super();
		this.loan = loan;
		this.bookCopy = bookCopy;
		this.dueDate = loan.getBorrowDate().plusDays(14); // 14-day loan period
		this.returnDate = returnDate;
		this.renewalCount = renewalCount;
		this.fineAmount = fineAmount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	public Bookcopy getBookCopy() {
		return bookCopy;
	}

	public void setBookCopy(Bookcopy bookCopy) {
		this.bookCopy = bookCopy;
	}
	
	public LocalDate getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	public LocalDate getReturnDate() {
		return returnDate;
	}
	
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
	
	public int getRenewalCount() {
		return renewalCount;
	}
	
	public void setRenewalCount(int renewalCount) {
		this.renewalCount = renewalCount;
	}
	
	public double getFineAmount() {
		return fineAmount;
	}
	
	public void setFineAmount(double fineAmount) {
		this.fineAmount = fineAmount;
	}
	
	@Override
	public String toString() {
		return "Loan item [ID = " + id + ", Loan ID = " + loan + ", Copy ID = " + bookCopy + ", Due Date = " + dueDate 
				+ ", Return Date = " + returnDate + ", Renewal Count = " + renewalCount + ", Fine Amount = " + fineAmount + "]";
	}
	
	/**
     * Checks if the loan item can be renewed based on business rules.
     * @return true if the item can be renewed, false otherwise.
     */
    public boolean canRenew() {
        return !isOverdue() && this.renewalCount < MAX_RENEWALS;
    }

    /**
     * Renews the loan item by extending the due date and incrementing the renewal count.
     * @throws IllegalStateException if the loan cannot be renewed.
     */
    public void renew() {
        if (canRenew()) {
            this.dueDate = this.dueDate.plusDays(RENEWAL_PERIOD_DAYS);
            this.renewalCount++;
        } else {
            throw new IllegalStateException("Cannot renew this loan item. It is overdue or the renewal limit has been reached.");
        }
    }

    /**
     * Checks if the loan item is currently overdue (not yet returned and past due date).
     * This is crucial for calculating ongoing fines and determining renewal eligibility.
     * @return true if the item is currently overdue, false otherwise.
     */
    public boolean isOverdue() {
        // An item is currently overdue if it has NOT been returned yet (returnDate is null)
        // AND the current date is after its due date.
        return this.returnDate == null && LocalDate.now().isAfter(this.dueDate);
    }
    
    /**
     * Calculates the fine amount for the overdue loan item.
     * @return The calculated fine amount.
     */
    public double calculateFine() {
        if (isOverdue()) {
            long overdueDays = ChronoUnit.DAYS.between(this.dueDate, LocalDate.now());
            this.fineAmount = overdueDays * FINE_PER_DAY;
            return this.fineAmount;
        }
        this.fineAmount = 0.0;
        return 0.0;
    }
}
