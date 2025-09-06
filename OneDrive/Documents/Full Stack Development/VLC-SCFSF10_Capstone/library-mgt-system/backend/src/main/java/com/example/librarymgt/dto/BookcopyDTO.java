package com.example.librarymgt.dto;

public class BookcopyDTO {
	private Long copyId; // ID of the Bookcopy
	private int copyNumber;
    private String status; // Changed to String to match the enum names
    private Long bookId; // ID of the associated book
    private String title; // Title of the associated book
    
 // Constructor
    public BookcopyDTO(Long copyId, int copyNumber, String status, Long bookId, String title) {
    	this.copyId = copyId;
    	this.copyNumber = copyNumber;
        this.status = status;
        this.bookId = bookId; 
        this.title = title; 
    }

    // Getters & Setters
    public Long getCopyId() { return copyId; }
    public void setCopyId(Long copyId) { this.copyId = copyId; }
    
    public int getCopyNumber() { return copyNumber; }
    public void setCopyNumber(int copyNumber) { this.copyNumber = copyNumber; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}