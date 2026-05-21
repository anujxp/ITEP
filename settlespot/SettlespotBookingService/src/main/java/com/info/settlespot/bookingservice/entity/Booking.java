package com.info.settlespot.bookingservice.entity;

import com.info.settlespot.bookingservice.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private Integer propertyId;

	@Column(nullable = false)
	private Integer tenantId;

	@Column(nullable = false)
	private Integer hostId;

	private String propertyTitle;   // Snapshot at booking time
	private String tenantName;
	private String hostName;

	private LocalDate checkInDate;
	private LocalDate checkOutDate;

	@Builder.Default
	private LocalDateTime createdAt = LocalDateTime.now();

	private double totalAmount;

	@Enumerated(EnumType.STRING)
	@Builder.Default
	private BookingStatus status = BookingStatus.PENDING;

	// Rating fields (tenant rates after stay)
	private Integer rating;        // 1–5
	private String ratingComment;

	@Builder.Default
	private boolean isRated = false;
}