package com.example.librarymgt.dto;

import com.example.librarymgt.model.Bookcopy.Status;

public class BookcopyDTO {
    private int copyNumber;
    private Status status;
    private BookDTO book;
    
    // Getters and Setters
    public int getCopyNumber() { return copyNumber; }
    public void setCopyNumber(int copyNumber) { this.copyNumber = copyNumber; }
    
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    
    public BookDTO getBook() { return book; }
    public void setBook(BookDTO book) { this.book = book; }

}
