package com.example.librarymgt.model;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name="books")//for SQL database

public class Book {
	
	public enum Category {
        FICTION,
        NON_FICTION
    }

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto-generated 
	@Column(name = "book_id")
	private Long bookid; //a new ID will be generated automatically for each new book
	
	@Column(name = "isbn", unique = true, nullable = false, length = 50)
	private String isbn; //ISBN is a unique identifier for books
	
	@Column(name = "book_title", nullable = false, length = 255)
	private String title;
	
	@Column(name = "author", nullable = false, length = 255)
	private String author;
	
	@Enumerated(EnumType.STRING)   // store as string "FICTION" / "NON_FICTION"
	@Column(name = "category", nullable = false)
	private Category category;	
	
	@Column(name = "publication_year", nullable = false)
	private Integer publicationYear;
	
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonManagedReference // Prevent circular reference issues in JSON serialization
	private List<Bookcopy> copies = new ArrayList<>();// List of copies of this book

	// Constructors, getters, and setters
	public Book() {
		super();
	}

	public Book(String isbn, String title, String author, Category category, Integer publicationYear) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.category = category;
		this.publicationYear = publicationYear;
	}

	public Long getId() {
		return bookid;
	}

	public void setId(Long bookid) {
		this.bookid = bookid;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Integer getPublicationYear() {
		return publicationYear;
	}
	
	public void setPublicationYear(Integer publicationYear) {
		this.publicationYear = publicationYear;
	}
	
	public List<Bookcopy> getCopies() {
		return copies;
	}
	
	public void setCopies(List<Bookcopy> copies) {
		this.copies = copies;
	}
	
	public void addCopy(Bookcopy copy) {
		copies.add(copy);
		copy.setBook(this);
	}	
	
	public void removeCopy(Bookcopy copy) {
		copies.remove(copy);
		copy.setBook(null);
	}
	
	@Override
	public String toString() {
	    String copyInfo = copies.stream()
	        .map(c -> "[CopyNumber=" + c.getCopyNumber() + ", Status=" + c.getStatus() + "]")
	        .collect(Collectors.joining(", "));
	    
	    return "Book [ID=" + bookid + ", ISBN=" + isbn + ", Title=" + title + ", Author=" + author +
	           ", Category=" + category + ", PublicationYear=" + publicationYear +
	           ", Copies=" + copyInfo + "]";
	}
}
