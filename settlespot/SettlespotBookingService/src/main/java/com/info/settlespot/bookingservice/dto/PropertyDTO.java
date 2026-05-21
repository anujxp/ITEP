package com.info.settlespot.bookingservice.dto;

import lombok.Data;

@Data
public class PropertyDTO {
	private Integer id;
	private String title;
	private double rentAmount;
	private Integer hostId;
	private boolean isAvailable;
	private String approvalStatus; // PENDING_APPROVAL, APPROVED, REJECTED
}