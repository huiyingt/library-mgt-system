package com.example.librarymgt.model;
import java.time.LocalDate;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name="loans")//for SQL database

public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto-generated 
	@Column(name = "loan_id")
	private Long id; //a new ID will be generated automatically for each new loan
	
	@ManyToOne(fetch = FetchType.LAZY)  
	@JoinColumn(name = "user_id", nullable = false) // FK in Loans table
	private User user; // User who borrowed the book
	
	@Column(name = "borrow_date", nullable = false)
	private LocalDate borrowDate; // Date when the book was borrowed
	
	 @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, orphanRemoval = true)
	 private List<Loanitem> loanItems = new ArrayList<>();
	
	// Constructors, getters, and setters
	public Loan() {
		super();
	}

	public Loan(User user, LocalDate borrowDate) {
		super();
		this.user = user;
		this.borrowDate = borrowDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(LocalDate borrowDate) {
		this.borrowDate = borrowDate;
	}
	
	public List<Loanitem> getLoanItems() {
        return loanItems;
    }

    public void setLoanItems(List<Loanitem> loanItems) {
        this.loanItems = loanItems;
    }

    @Override
    public String toString() {
		return "Loan [ID = " + id + ", User ID = " + user.getUsername() + ", Borrow Date = " + borrowDate + "]";
	}
}
