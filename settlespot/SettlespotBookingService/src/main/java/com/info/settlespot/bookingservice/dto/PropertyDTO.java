package com.info.settlespot.bookingservice.dto;

public class PropertyDTO {
	private Integer id;
	private String title;
	private double rentAmount;
	private Integer hostId;
	private boolean isAvailable;

	// --- Getters and Setters ---

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getRentAmount() {
		return rentAmount;
	}

	public void setRentAmount(double rentAmount) {
		this.rentAmount = rentAmount;
	}

	public Integer getHostId() {
		return hostId;
	}

	public void setHostId(Integer hostId) {
		this.hostId = hostId;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean available) {
		isAvailable = available;
	}
}