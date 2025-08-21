package com.example.librarymgt.model;
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
	@Column(name = "CopyId")
	private Long id; //a new ID will be generated automatically for each new book copy
	
	@ManyToOne(fetch = FetchType.LAZY)  
	@JoinColumn(name = "BookId", nullable = false) // FK in BookCopies table
	private Book book; // The book this copy belongs to
	
	@Enumerated(EnumType.STRING)   // store as string "AVAILABLE" / "BORROWED" / "RESERVED" / "OVERDUE"
	@Column(name = "BookStatus", nullable = false)
	private Status status; // Status of the book copy
	
	// Constructors, getters, and setters
	public Bookcopy() {
		super();
	}

	public Bookcopy(Book book, Status status) {
		super();
		this.book = book;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Book Copy [ID = " + id + ", Book ID = " + book + ", Status = " + status + "]";
	}
	
}
