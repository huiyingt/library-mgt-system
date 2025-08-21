package com.example.librarymgt.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.librarymgt.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	// This interface extends JpaRepository, which provides methods for CRUD operations
	// This interface will automatically provide CRUD operations for Role entities
	// No need to implement any methods, Spring Data JPA will handle it
	// count(), findAll(), findById(), save(), deleteById() etc. are all available to help work with SQL queries

}
