package com.info.settlespot.bookingservice.entity;

import com.info.settlespot.bookingservice.enums.BookingStatus;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// References to other services
	private Integer propertyId;
	private Integer tenantId;
	private Integer hostId;

	// Booking Details
	private LocalDate checkInDate;
	private LocalDate checkOutDate;

	private LocalDateTime createdAt;
	private double totalAmount;

	@Enumerated(EnumType.STRING)
	private BookingStatus status;

	// Default Constructor
	public Booking() {
		this.createdAt = LocalDateTime.now();
		this.status = BookingStatus.PENDING;
	}

	// Parameterized Constructor
	public Booking(Integer propertyId, Integer tenantId, Integer hostId, LocalDate checkInDate, LocalDate checkOutDate,
			double totalAmount, BookingStatus status) {
		this.propertyId = propertyId;
		this.tenantId = tenantId;
		this.hostId = hostId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.totalAmount = totalAmount;
		this.status = status != null ? status : BookingStatus.PENDING;
		this.createdAt = LocalDateTime.now();
	}

	// --- Getters and Setters ---

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public Integer getHostId() {
		return hostId;
	}

	public void setHostId(Integer hostId) {
		this.hostId = hostId;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}
}