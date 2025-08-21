package com.example.librarymgt.controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.librarymgt.services.RoleServices;
import com.example.librarymgt.model.Role;
@RestController
@RequestMapping("/api/roles") //base URL for role-related endpoints
@CrossOrigin(origins= "http://localhost:5173")
//CrossOrigin to connect to ReactJS frontend
public class RoleController {

	@Autowired //to inject the RoleServices bean
	private RoleServices roleServices; //service to handle business logic
	
	@PostMapping("/create") //endpoint to create a new role
	public Role createRole(@RequestBody Role role) {
		// This method will handle the creation of a new role
		// It will receive a Role object from the request body
		return roleServices.saveRole(role); //call the service to save the role
		// The saved role object will be returned as the response	
	}
	
	@GetMapping("/{id}") //endpoint to get a role by ID
	public Optional<Role> getRoleById(@PathVariable Long id) {
		// This method will handle the retrieval of a role by ID
		return roleServices.getRoleById(id); //call the service to get the role by ID
		// The role object will be returned as the response
	}
	
	@GetMapping("/all") //endpoint to get all roles
	public List<Role> getAllRoles() {
		// This method will handle the retrieval of all roles
		return roleServices.getAllRoles(); //call the service to get all roles
		// A list of role objects will be returned as the response
	}
	
	@PutMapping("/update/{id}") //endpoint to update a role by ID (pathvariable)
	public Role updateRole(@PathVariable Long id, @RequestBody Role role) {
		// This method will handle the update of an existing role
		return roleServices.updateRole(id, role); //call the service to update the role
		// The updated role object will be returned as the response
	}
	
	@DeleteMapping("/delete/{id}") //endpoint to delete a role by ID (pathvariable)
	public void deleteRole(@PathVariable Long id) {
		roleServices.deleteRole(id); //call the service to delete the role by ID
	}
}
