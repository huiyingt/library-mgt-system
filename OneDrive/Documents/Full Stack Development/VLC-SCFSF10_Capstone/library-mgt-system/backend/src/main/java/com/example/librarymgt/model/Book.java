package com.example.librarymgt.model;
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

	@Override
	public String toString() {
		return "Book [ID = " + bookid + ", IBSN = " + isbn + ", Title = " + title + ", Author = " + author + ", Category = " + category + ", Publication Year = " + publicationYear + "]";
	}
}
