package com.example.librarymgt.model;
import jakarta.persistence.*;


@Entity
@Table(name="users")//for SQL database

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto-generated 
	@Column(name = "UserId")
	private Long id; //a new ID will be generated automatically for each new user
	
	@Column(name = "UserName", nullable = false, length = 255)
	private String username; //Username is a unique identifier for users
	
	@Column(name = "PasswordHash", nullable = false, length = 255)
	private String password; // Password should be stored as a hash for security reasons
	
	@Column(name = "Email", unique = true, nullable = false, length = 255)
	private String email; // Email is unique and used for user identification and communication
	
	@Column(name = "ContactNo", nullable = false, length = 50)
	private String contactNo; // Contact number for user communication
	
	@Column (name = "Address", nullable = true, length = 255)
	private String address; // Address is optional and can be null
	
	@ManyToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name = "RoleId", nullable = false) // FK in Users table
    private Role role;
	
	
	// Constructors, getters, and setters
	public User() {
		super();
	}

	public User(String username, String password, String email, String contactNo, String address, Role role) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.contactNo = contactNo;
		this.address = address;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getContactNo() {
		return contactNo;
	}
	
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "User [ID = " + id + ", Username = " + username + ", Email = " + email + ", Contact No = " + contactNo + ", Address = " + address + ", Role = " + role + "]";
	}
}


