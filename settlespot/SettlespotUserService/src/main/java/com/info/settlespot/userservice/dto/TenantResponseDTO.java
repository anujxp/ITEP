package com.info.settlespot.userservice.dto;

import com.info.settlespot.userservice.entity.Tenant;

public class TenantResponseDTO {

	private Integer id;
	private String fullName;
	private String email;
	private String phoneNumber;
	private Integer age;
	private String occupation;
	private String permanentAddress;

	public TenantResponseDTO() {
	}

	public TenantResponseDTO(Tenant tenant) {
		this.id = tenant.getId();
		this.fullName = tenant.getFullName();
		this.email = tenant.getEmail();
		this.phoneNumber = tenant.getPhoneNumber();
		this.age = tenant.getAge();
		this.occupation = tenant.getOccupation();
		this.permanentAddress = tenant.getPermanentAddress();

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

	public Integer getAge() {
		return age;
	}

	public String getOccupation() {
		return occupation;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}
}