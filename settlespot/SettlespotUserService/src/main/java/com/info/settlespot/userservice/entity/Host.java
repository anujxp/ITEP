package com.info.settlespot.userservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hosts")
public class Host {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String fullName;
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
    private String password;

    private String phoneNumber;
    private String businessName;
    private String officeAddress;
    private boolean isVerified = false;
	
}
