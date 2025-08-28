package com.example.librarymgt.services;

import com.example.librarymgt.dto.BookcopyDTO;
import com.example.librarymgt.mapper.BookcopyMapper;
import com.example.librarymgt.model.Bookcopy;
import com.example.librarymgt.repository.BookcopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service //indicates that this class is a service component
public class BookcopyServices {

	@Autowired
    private BookcopyRepository bookcopyRepository;

    // Get all book copies as DTOs
    public List<BookcopyDTO> getAllBookcopies() {
        List<Bookcopy> bookcopies = bookcopyRepository.findAll();
        return bookcopies.stream()
                         .map(BookcopyMapper::toBookcopyDTO)
                         .toList();
    }

    // Get a single book copy by ID as DTO
    public BookcopyDTO getBookcopyById(Long id) {
        Bookcopy bookcopy = bookcopyRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Book copy not found"));
        return BookcopyMapper.toBookcopyDTO(bookcopy);
    }
    
    // Get book copies by book ID as DTOs
    public List<BookcopyDTO> getBookcopiesByBookId(Long bookId) {
    			Optional<Bookcopy> bookcopies = bookcopyRepository.findByBookId(bookId);
		return bookcopies.stream()
						 .map(BookcopyMapper::toBookcopyDTO)
						 .toList();
    }
    
    // Create a new Bookcopy from entity (if needed)
    public BookcopyDTO createBookcopy(Bookcopy bookcopy) {
        Bookcopy saved = bookcopyRepository.save(bookcopy);
        return BookcopyMapper.toBookcopyDTO(saved);
    }

    // Update an existing Bookcopy by ID (if needed)
    public BookcopyDTO updateBookcopy(Long id, Bookcopy updatedBookcopy) {
        Bookcopy bookcopy = bookcopyRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Book copy not found"));

        bookcopy.setBook(updatedBookcopy.getBook());
        bookcopy.setCopyNumber(updatedBookcopy.getCopyNumber());
        bookcopy.setStatus(updatedBookcopy.getStatus());

        Bookcopy saved = bookcopyRepository.save(bookcopy);
        return BookcopyMapper.toBookcopyDTO(saved);
    }

    // Delete a book copy by ID
    public void deleteBookcopy(Long id) {
        Bookcopy bookcopy = bookcopyRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Book copy not found"));
        bookcopyRepository.delete(bookcopy);
    }
}
