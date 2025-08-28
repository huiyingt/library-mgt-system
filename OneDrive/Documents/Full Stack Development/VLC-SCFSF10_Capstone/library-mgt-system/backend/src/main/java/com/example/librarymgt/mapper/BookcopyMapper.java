package com.example.librarymgt.mapper;

import com.example.librarymgt.model.Book;
import com.example.librarymgt.model.Bookcopy;
import com.example.librarymgt.dto.BookDTO;
import com.example.librarymgt.dto.BookcopyDTO;

public class BookcopyMapper {

    public static BookDTO toBookDTO(Book book) {
        if (book == null) return null;

        BookDTO dto = new BookDTO();
        dto.setIsbn(book.getIsbn());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setCategory(book.getCategory().name());
        dto.setPublicationYear(book.getPublicationYear());
        return dto;
    }

    public static BookcopyDTO toBookcopyDTO(Bookcopy bookcopy) {
        if (bookcopy == null) return null;

        BookcopyDTO dto = new BookcopyDTO();
        dto.setCopyNumber(bookcopy.getCopyNumber());
        dto.setStatus(bookcopy.getStatus()); // enum, not string
        dto.setBook(toBookDTO(bookcopy.getBook()));
        return dto;
    }
}