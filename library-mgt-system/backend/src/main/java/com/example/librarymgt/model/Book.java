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
	@Column(name = "BookId")
	private Long id; //a new ID will be generated automatically for each new book
	
	@Column(name = "ISBN", unique = true, nullable = false, length = 50)
	private String isbn; //ISBN is a unique identifier for books
	
	@Column(name = "BookTitle", nullable = false, length = 255)
	private String title;
	
	@Column(name = "Author", nullable = false, length = 255)
	private String author;
	
	@Enumerated(EnumType.STRING)   // store as string "FICTION" / "NON_FICTION"
	@Column(name = "Category", nullable = false)
	private Category category;	
	
	@Column(name = "PublicationYear", nullable = false)
	private String publicationYear;


	// Constructors, getters, and setters
	public Book() {
		super();
	}

	public Book(String isbn, String title, String author, Category category, String publicationYear) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.category = category;
		this.publicationYear = publicationYear;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public String getPublicationYear() {
		return publicationYear;
	}
	
	public void setPublicationYear(String publicationYear) {
		this.publicationYear = publicationYear;
	}

	@Override
	public String toString() {
		return "Book [ID = " + id + ", IBSN = " + isbn + ", Title = " + title + ", Author = " + author + ", Category = " + category + ", Publication Year = " + publicationYear + "]";
	}
}
