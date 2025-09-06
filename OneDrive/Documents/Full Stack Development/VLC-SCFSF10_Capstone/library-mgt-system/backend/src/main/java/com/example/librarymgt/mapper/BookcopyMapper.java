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
        dto.setCopies(book.getCopies().stream()
			.map(BookcopyMapper::toBookcopyDTO)
			.toList()
		);
        return dto;
    }

    public static BookcopyDTO toBookcopyDTO(Bookcopy bookcopy) {
        if (bookcopy == null) return null;

     // Map to your new constructor
        return new BookcopyDTO(
            bookcopy.getId(), // copyId
        	bookcopy.getCopyNumber(),
            bookcopy.getStatus().name(), // convert enum to string
            bookcopy.getBook().getId(), // book ID
            bookcopy.getBook().getTitle()   // book title
        );
    }
}
