package com.info.settlespot.bookingservice.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingRequestDTO {

	@NotNull(message = "Property ID is required")
	private Integer propertyId;

	@NotNull(message = "Check-in date is required")
	private LocalDate checkInDate;

	@NotNull(message = "Check-out date is required")
	private LocalDate checkOutDate;
	// tenantId is extracted from JWT header, not from body
}