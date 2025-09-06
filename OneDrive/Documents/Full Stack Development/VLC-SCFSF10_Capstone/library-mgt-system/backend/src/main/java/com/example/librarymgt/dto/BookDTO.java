package com.example.librarymgt.dto;

import java.util.List;

public class BookDTO {
    private String isbn;
    private String title;
    private String author;
    private String category;
    private Integer publicationYear;
    private List<BookcopyDTO> copies; // List of copies of this book
    
    // Getters and Setters
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public Integer getPublicationYear() { return publicationYear; }
    public void setPublicationYear(Integer publicationYear) { this.publicationYear = publicationYear; }
    
    public List<BookcopyDTO> getCopies() { return copies; }
    public void setCopies(List<BookcopyDTO> copies) { this.copies = copies; }
    
}
    