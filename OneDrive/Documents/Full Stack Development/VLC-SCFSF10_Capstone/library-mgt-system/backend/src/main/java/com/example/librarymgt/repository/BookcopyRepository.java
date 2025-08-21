package com.example.librarymgt.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.librarymgt.model.Bookcopy;

public interface BookcopyRepository extends JpaRepository<Bookcopy, Long> {
	// This interface extends JpaRepository, which provides methods for CRUD operations
	// This interface will automatically provide CRUD operations for Bookcopy entities
	// No need to implement any methods, Spring Data JPA will handle it
	// count(), findAll(), findById(), save(), deleteById() etc. are all available to help work with SQL queries
	
	// Custom query method to find a book copy by its status
	Optional<Bookcopy> findByStatus(Bookcopy.Status status);
	Optional<Bookcopy> findByBookId(Long bookId);
}
