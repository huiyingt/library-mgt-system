package com.example.librarymgt.model;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name="bookcopies")//for SQL database

public class Bookcopy {
	
	public enum Status {
        AVAILABLE,
        BORROWED,
        RESERVED,
        OVERDUE
    }

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto-generated 
	@Column(name = "copy_id")
	private Long copyid; //a new ID will be generated automatically for each new book copy
	
	@ManyToOne(fetch = FetchType.LAZY)  
	@JoinColumn(name = "book_id", nullable = false) // FK in BookCopies table
	@JsonBackReference // Prevent circular reference issues in JSON serialization
	private Book book; // The book this copy belongs to
	
	@Column (name = "copy_number", nullable = false)
	private int copyNumber; // Number of this copy of the book (e.g., 1)
	
	@Enumerated(EnumType.STRING)   // store as string "AVAILABLE" / "BORROWED" / "RESERVED" / "OVERDUE"
	@Column(name = "book_status", nullable = false)
	private Status status; // Status of the book copy
	
	// Constructors, getters, and setters
	public Bookcopy() {
		super();
	}

	public Bookcopy(Book book, int copyNumber, Status status) {
		super();
		this.book = book;
		this.copyNumber = copyNumber; 
		this.status = status;
	}

	public Long getId() {
		return copyid;
	}

	public void setId(Long copyid) {
		this.copyid = copyid;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	public int getCopyNumber() {
		return copyNumber;
	}
	
	public void setCopyNumber(int copyNumber) {
		this.copyNumber = copyNumber;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Book Copy [ID = " + copyid + ", Book ID = " + book + "Copy Number = " + copyNumber + ", Status = " + status + "]";
	}
	
}
