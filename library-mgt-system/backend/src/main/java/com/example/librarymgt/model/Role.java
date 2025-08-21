package com.example.librarymgt.model;
import jakarta.persistence.*;

@Entity
@Table(name="roles")//for SQL database

public class Role {

	public enum roleType {
        System_Admin,
        Staff_Admin,
        Member
    }
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto-generated
	@Column(name = "RoleId")
	private Long id; //a new ID will be generated automatically for each new role
	
	@Enumerated(EnumType.STRING)   // store as string "System_Admin" / "Staff_Admin" / "Member"
	@Column(name = "RoleType", nullable = false, unique = true)
	private roleType roleType; //roleType is a unique identifier for roles
	
	// Constructors, getters, and setters
	public Role() {
		super();
	}
	
	public Role(roleType roleType) {
		super();
		this.roleType = roleType;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public roleType getRoleType() {
		return roleType;
	}
	
	public void setRoleType(roleType roleType) {
		this.roleType = roleType;
	}
	
	@Override
	public String toString() {
		return "Role [ID = " + id + ", Role Type = " + roleType + "]";
	}
	
}
