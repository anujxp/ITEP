package com.info.settlespot.propertyservice.dto;

public class UserDTO {
    
    private Integer id;
    private String fullName;      // Changed from 'name'
    private String email;
    private String phoneNumber;   // Changed from 'phone'
    private String businessName;  // Added
    private String officeAddress; // Added
    private boolean isVerified;   // Added

    // --- Getters and Setters ---

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getBusinessName() { return businessName; }
    public void setBusinessName(String businessName) { this.businessName = businessName; }

    public String getOfficeAddress() { return officeAddress; }
    public void setOfficeAddress(String officeAddress) { this.officeAddress = officeAddress; }

    public boolean isVerified() { return isVerified; }
    public void setVerified(boolean verified) { isVerified = verified; }
}