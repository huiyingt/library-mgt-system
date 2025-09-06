package com.example.librarymgt.mapper;

import com.example.librarymgt.dto.BookcopyDTO;
import com.example.librarymgt.dto.LoanitemDTO;
import com.example.librarymgt.model.Loanitem;

public class LoanitemMapper {
    public static LoanitemDTO toLoanitemDTO(Loanitem item) {
        BookcopyDTO bookCopyDTO = new BookcopyDTO(
            item.getBookCopy().getId(),
        	item.getBookCopy().getCopyNumber(),
            item.getBookCopy().getStatus().name(), // enum → String
            item.getBookCopy().getBook().getId(),
            item.getBookCopy().getBook().getTitle()
        );

        return new LoanitemDTO(
            item.getId(),
            item.getDueDate(),
            item.getReturnDate(),
            item.getRenewalCount(),
            item.getFineAmount(),
            bookCopyDTO
        );
    }
}
