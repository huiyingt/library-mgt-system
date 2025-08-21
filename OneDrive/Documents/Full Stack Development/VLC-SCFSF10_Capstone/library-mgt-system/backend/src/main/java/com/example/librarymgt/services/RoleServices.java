package com.example.librarymgt.services;
//this is business logic layer
//perform CRUD operations on Book data
import com.example.librarymgt.repository.RoleRepository;
import com.example.librarymgt.model.Role;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //indicates that this class is a service component
public class RoleServices {

	@Autowired //to inject the RoleRepository bean
	private RoleRepository roleRepository; //repository to access Role data
	
	public Role saveRole(Role role) {
		// Need data: a role object
		// saveRole requires Role details
		// Use RoleRepository to save the role
		System.out.println("Role added: " + role);
		return roleRepository.save(role); //returns the saved role object
	}
	
	public Optional<Role> getRoleById(Long roleid) {
		// Use RoleRepository to find the role by ID
		return roleRepository.findById(roleid); //returns the role if found, otherwise null
	}
	
	public List<Role> getAllRoles() {
		// Use RoleRepository to find all roles
		return roleRepository.findAll(); //returns a list of all roles
	}
	
	public Role updateRole(Long roleid, Role roleDetails) {
		// Use RoleRepository to find the role by ID
		Role existingRole = roleRepository.findById(roleid)
				.orElseThrow(() -> new RuntimeException("Role not found with ID: " + roleid));
		// Update the existing role's details
		if (roleDetails.getRoleType() != null) //check if name is not null
			existingRole.setRoleType(roleDetails.getRoleType());
		// Save the updated role
		System.out.println("Role updated: " + existingRole);
		return roleRepository.save(existingRole); //returns the updated role object
	}
	
	public void deleteRole(Long roleid) {
		// Use RoleRepository to delete the role by ID
		roleRepository.deleteById(roleid); //deletes the role if found
		System.out.println("Role deleted with ID: " + roleid);
	}
}