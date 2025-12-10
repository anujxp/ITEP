package com.needconnect.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bookings")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String bookingDate; 
	private String status; 

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	
	@ManyToOne
	@JoinColumn(name = "listing_id")
	private Listing listing;

	// --- Constructors ---
	public Booking() {
		super();
	}

	public Booking(String bookingDate, String status, User user, Listing listing) {
		super();
		this.bookingDate = bookingDate;
		this.status = status;
		this.user = user;
		this.listing = listing;
	}

	// --- Getters and Setters ---
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Listing getListing() {
		return listing;
	}

	public void setListing(Listing listing) {
		this.listing = listing;
	}
}