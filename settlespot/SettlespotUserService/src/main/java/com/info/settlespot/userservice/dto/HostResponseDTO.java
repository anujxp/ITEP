package com.info.settlespot.userservice.dto;

import com.info.settlespot.userservice.entity.Host;

public class HostResponseDTO {

	private Integer id;
	private String fullName;
	private String email;
	private String phoneNumber;
	private String businessName;
	private String officeAddress;
	private boolean isVerified;

	public HostResponseDTO(Host host) {
		this.id = host.getId();
		this.fullName = host.getFullName();
		this.email = host.getEmail();
		this.phoneNumber = host.getPhoneNumber();
		this.businessName = host.getBusinessName();
		this.officeAddress = host.getOfficeAddress();
		this.isVerified = host.isVerified();
	}

	public Integer getId() {
		return id;
	}

	public String getFullName() {
		return fullName;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getBusinessName() {
		return businessName;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public boolean isVerified() {
		return isVerified;
	}
}