package com.example.librarymgt.dto;

import java.time.LocalDate;

public class LoanitemDTO {
    private Long id;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String renewalCount;
    private double fineAmount;
    private BookcopyDTO bookCopy;

    // Constructor
    public LoanitemDTO(Long id, LocalDate dueDate, LocalDate returnDate,
                       String renewalCount, double fineAmount, BookcopyDTO bookCopy) {
        this.id = id;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.renewalCount = renewalCount;
        this.fineAmount = fineAmount;
        this.bookCopy = bookCopy;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public String getRenewalCount() { return renewalCount; }
    public void setRenewalCount(String renewalCount) { this.renewalCount = renewalCount; }

    public double getFineAmount() { return fineAmount; }
    public void setFineAmount(double fineAmount) { this.fineAmount = fineAmount; }

    public BookcopyDTO getBookCopy() { return bookCopy; }
    public void setBookCopy(BookcopyDTO bookCopy) { this.bookCopy = bookCopy; }
}
