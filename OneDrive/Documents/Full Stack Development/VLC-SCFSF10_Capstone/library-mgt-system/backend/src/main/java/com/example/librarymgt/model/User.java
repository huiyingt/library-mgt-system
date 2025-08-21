package com.example.librarymgt.model;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


@Entity
@Table(name="users")//for SQL database

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto-generated 
	@Column(name = "user_id")
	private Long id; //a new ID will be generated automatically for each new user
	
	@ManyToOne(fetch = FetchType.EAGER)  
    @JoinColumn(name = "role_id", nullable = false) // FK in Users table
	@JsonIgnore // Prevent circular reference issues in JSON serialization
    private Role roleid; 
	
	@Column(name = "user_name", nullable = false, length = 255)
	private String username; //Username is a unique identifier for users
	
	@Column(name = "password_hash", nullable = false, length = 255)
	private String password; // Password should be stored as a hash for security reasons
	
	@Column(name = "email", unique = true, nullable = false, length = 255)
	private String email; // Email is unique and used for user identification and communication
	
	@Column(name = "contact_no", nullable = false, length = 50)
	private String contactNo; // Contact number for user communication
	
	@Column (name = "address", nullable = true, length = 255)
	private String address; // Address is optional and can be null
	
	@Temporal(TemporalType.DATE) // Store as date
	@Column (name = "membership_start_date", nullable = false)
	private Date membershipStartDate; // Date when the user became a member, default to current date
	
	@Temporal(TemporalType.DATE) // Store as date
	@Column (name = "membership_end_date", nullable = false)
	private Date membershipEndDate; // Date when the user's membership ends, default to one year from start date
	
	// Constructors, getters, and setters
	public User() {
		super();
	}

	public User(Role role, String username, String password, String email, String contactNo, String address, Date membershipStartDate, Date membershipEndDate) {
		super();
		this.roleid = role;
		this.username = username;
		this.password = password;
		this.email = email;
		this.contactNo = contactNo;
		this.address = address;
		this.membershipStartDate = new java.util.Date(); // Sets to current date
		this.membershipEndDate = new java.util.Date(this.membershipStartDate.getTime() + 365L * 24 * 60 * 60 * 1000); // Sets to one year later
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Role getRole() {
		return roleid;
	}

	public void setRole(Role roleid) {
		this.roleid = roleid;
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

	public Date getMembershipStartDate() {
		return membershipStartDate;
	}
	
	public void setMembershipStartDate(Date membershipStartDate) {
		this.membershipStartDate = membershipStartDate;
	}
	
	public Date getMembershipEndDate() {
		return membershipEndDate;
	}
	
	public void setMembershipEndDate(Date membershipEndDate) {
		this.membershipEndDate = membershipEndDate;
	}
	
	@Override
	public String toString() {
		return "User [ID = " + id + ", Role ID = " + roleid + ", Username = " + username + ", Email = " + email + ", Contact No = " + contactNo + ", Address = " + address + ", Membership Start Date = " + membershipStartDate + ", Membership End Date = " + membershipEndDate + "]";
	}
}


