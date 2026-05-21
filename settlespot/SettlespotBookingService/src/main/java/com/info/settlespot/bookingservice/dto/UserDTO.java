package com.info.settlespot.bookingservice.dto;

import lombok.Data;

@Data
public class UserDTO {
	private Integer id;
	private String fullName;
	private String email;
	private String phoneNumber;
}