package com.example.librarymgt.dto;

import java.util.Date;

public class UserDTO {
    private String username;
    private String email;
    private String password;
    private String contactNo;
    private String address;
    private Long roleId;
    private Date membershipStartDate;
    private Date membershipEndDate;

    // getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getContactNo() { return contactNo; }
    public void setContactNo(String contactNo) { this.contactNo = contactNo; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public Long getRoleId() { return roleId; }
    public void setRoleId(Long roleId) { this.roleId = roleId; }
    
    public Date getMembershipStartDate() { return membershipStartDate; }
    public void setMembershipStartDate(Date membershipStartDate) { this.membershipStartDate = membershipStartDate; }
    
    public Date getMembershipEndDate() { return membershipEndDate; }
    public void setMembershipEndDate(Date membershipEndDate) { this.membershipEndDate = membershipEndDate; }
    
}