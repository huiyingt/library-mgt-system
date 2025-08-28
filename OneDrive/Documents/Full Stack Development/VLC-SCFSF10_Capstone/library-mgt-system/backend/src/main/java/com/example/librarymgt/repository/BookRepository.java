package com.example.librarymgt.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.librarymgt.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> { 
// This interface extends JpaRepository, which provides methods for CRUD operations
// This interface will automatically provide CRUD operations for Book entities
// No need to implement any methods, Spring Data JPA will handle it
// count(), findAll(), findById(), save(), deleteById() etc. are all available to help work with SQL queries

// Custom query - this allows us to find a book by its title
    Optional<Book> findByTitle(String title);
    Optional<Book> findByAuthor(String author);

    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title, String author);
}

