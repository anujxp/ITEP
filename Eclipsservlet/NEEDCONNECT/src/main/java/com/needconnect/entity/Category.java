package com.needconnect.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// e.g., "PG", "Hostel", "Room", "Flat"
	@Column(nullable = false, unique = true)
	private String name;

	// RELATIONSHIP: One Category -> Many Listings
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Listing> listings = new ArrayList<>();

	// --- Constructors ---
	public Category() {
	}

	public Category(String name) {
		this.name = name;
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

	public List<Listing> getListings() {
		return listings;
	}

	public void setListings(List<Listing> listings) {
		this.listings = listings;
	}
}