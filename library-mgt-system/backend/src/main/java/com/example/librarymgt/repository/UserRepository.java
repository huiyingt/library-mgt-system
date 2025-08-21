package com.example.librarymgt.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.librarymgt.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
// This interface extends JpaRepository, which provides methods for CRUD operations
// This interface will automatically provide CRUD operations for User entities
// No need to implement any methods, Spring Data JPA will handle it
// count(), findAll(), findById(), save(), deleteById() etc. are all available to help work with SQL queries
	
	Optional<User> findByEmail(String email); // Method to find a user by email
	Optional<User> findByUsername(String username); // Method to find a user by username
	
}