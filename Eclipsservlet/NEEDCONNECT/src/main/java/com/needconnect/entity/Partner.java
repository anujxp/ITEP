package com.needconnect.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList; // Good practice to initialize lists

@Entity
@Table(name = "partners")
public class Partner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@Column(unique = true, nullable = false)
	private String email;

	private String password;

	private String phone;

	// RELATIONSHIP: One Partner -> Many Listings
	// 'mappedBy' MUST match the field name 'partner' in Listing.java
	@OneToMany(mappedBy = "partner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Listing> listings = new ArrayList<>();

	// --- Constructors ---
	public Partner() {
	}

	public Partner(String name, String email, String password, String phone) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
	}

	// --- Getters and Setters ---
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Listing> getListings() {
		return listings;
	}

	public void setListings(List<Listing> listings) {
		this.listings = listings;
	}
}