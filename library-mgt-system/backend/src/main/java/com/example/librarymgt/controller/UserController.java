package com.example.librarymgt.controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.librarymgt.services.UserServices;
import com.example.librarymgt.model.User;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins= "http://localhost:5173")
//CrossOrigin to connect to ReactJS frontend
public class UserController {

	@Autowired //to inject the UserServices bean
	private UserServices userServices; //service to handle business logic
	
	@PostMapping("/create") //endpoint to create a new user
	public User createUser(@RequestBody User user) {
		// This method will handle the creation of a new user
		// It will receive a User object from the request body
		return userServices.saveUser(user); //call the service to save the user
		// The saved user object will be returned as the response	
	}
	
	@GetMapping("/{id}") //endpoint to get a user by ID
	public Optional<User> getUserById(@PathVariable Long id) {
		// This method will handle the retrieval of a user by ID
		return userServices.getUserById(id); //call the service to get the user by ID
		// The user object will be returned as the response
	}
	
  	@GetMapping("/search") //endpoint to search user by username or email (instead of username and email as path variables)
	public Optional<User> searchUser(@RequestParam(required = false) String username, 
	                                  @RequestParam(required = false) String email) {
		// This method will handle the search of a user by username or email
		if (username != null) {
			return userServices.getUserByUsername(username); //search by username
		} else if (email != null) {
			return userServices.getUserByEmail(email); //search by email
		}
		return Optional.empty(); //return empty if neither is provided
	}
  	
	@GetMapping("/all") //endpoint to get all users
	public List<User> getAllUsers() {
		// This method will handle the retrieval of all users
		return userServices.getAllUsers(); //call the service to get all users
		// A list of user objects will be returned as the response
	}
	
	@PutMapping("/update/{id}") //endpoint to update a user by ID (pathvariable)
	public User updateUser(@PathVariable Long id, @RequestBody User user) {
		// This method will handle the update of an existing user
		// It will receive a User object from the request body
		return userServices.updateUser(id, user); //call the service to update the user
		// The updated user object will be returned as the response
	}
	
	@DeleteMapping("/delete/{id}") //endpoint to delete a user by ID
	public void deleteUser(@PathVariable Long id) {
		// This method will handle the deletion of a user by ID
		userServices.deleteUser(id); //call the service to delete the user
		// No response body is returned, as the user is deleted
	}
}
