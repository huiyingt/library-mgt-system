package com.example.librarymgt.services;
//this is business logic layer
//perform CRUD operations on User data
import com.example.librarymgt.repository.UserRepository;
import com.example.librarymgt.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service //indicates that this class is a service component
public class UserServices {

	@Autowired //to inject the UserRepository bean
	private UserRepository userr; //repository to access User data
	
	@Autowired
    private PasswordEncoder passwordEncoder; // Inject the password encoder
	
	public User saveUser(User user) {
		// Need data: a user object
		// saveUser requires User details
		// HASH THE PASSWORD BEFORE SAVING
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
		// Use UserRepository to save the user
		System.out.println("User added: " + user);
		return userr.save(user); //returns the saved user object
	}
	
	public Optional<User> getUserById(Long id) {
		// Use UserRepository to find the user by ID
		return userr.findById(id); //returns the user if found, otherwise null
	}
	
	public Optional<User> getUserByEmail(String email) {
		// Use UserRepository to find the user by email
		return userr.findByEmail(email); //returns the user if found, otherwise null
	}
	
	public Optional<User> getUserByUsername(String username) {
		// Use UserRepository to find the user by username
		return userr.findByUsername(username); //returns the user if found, otherwise null
	}
	
	public List<User> getAllUsers() {
		// Use UserRepository to find all users
		return userr.findAll(); //returns a list of all users
	}	
	
	public User updateUser(Long id, User userDetails) {
		// Use UserRepository to find the user by ID
		User existingUser = userr.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
		if (userDetails.getUsername() != null) //check if name is not null
			existingUser.setUsername(userDetails.getUsername());
		if (userDetails.getEmail() != null) //check if email is not null
			existingUser.setEmail(userDetails.getEmail());
		
		// HASH THE PASSWORD IF IT IS BEING UPDATED
	    if (userDetails.getPassword() != null) {
	        String hashedPassword = passwordEncoder.encode(userDetails.getPassword());
	        existingUser.setPassword(hashedPassword);
	    }
		
	    if (userDetails.getContactNo() != null) //check if contact number is not null
			existingUser.setContactNo(userDetails.getContactNo());
		if (userDetails.getAddress() != null) //check if address is not null
			existingUser.setAddress(userDetails.getAddress());
		if (userDetails.getRole() != null) //check if role is not null
			existingUser.setRole(userDetails.getRole());
		return userr.save(existingUser); //returns the updated user object
	}
	
	public void deleteUser(Long id) {
		userr.deleteById(id); //deletes the user by ID
	}
}
