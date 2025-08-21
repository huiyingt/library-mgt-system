package com.example.librarymgt.repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.librarymgt.model.Loanitem;

public interface LoanitemRepository extends JpaRepository<Loanitem, Long> {
	// This interface extends JpaRepository, which provides methods for CRUD operations
	// This interface will automatically provide CRUD operations for Loanitem entities
	// No need to implement any methods, Spring Data JPA will handle it
	// count(), findAll(), findById(), save(), deleteById() etc. are all available to help work with SQL queries
	
	// Custom query methods
	Optional<Loanitem> findByDueDate(LocalDate dueDate);
	
	List<Loanitem> findbyLoanId(Long loanId);
	
	@Query("SELECT l FROM Loanitem l WHERE l.dueDate < :today AND l.returned = false")
    List<Loanitem> findOverdueLoanitems(LocalDate today);
	
}
