package com.info.settlespot.propertyservice.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import com.info.settlespot.propertyservice.entity.Property;
import com.info.settlespot.propertyservice.enums.PropertyType;

public class PropertyDTO {

	private Integer id;
	private String title;
	private String description;
	private String city;
	private String area; // New field
	private String address;
	private Double rentAmount;
	private PropertyType propertyType;
	private boolean isAvailable;
	private Integer hostId; // We need this to know who posted it
	private LocalDateTime postedOn;
	private List<String> images = new ArrayList<>();

	// --- Constructors ---

	public PropertyDTO() {
	}

	public PropertyDTO(Integer id, String title, String description, String city, String area, String address,
			Double rentAmount, PropertyType propertyType, boolean isAvailable, Integer hostId, LocalDateTime postedOn,
			List<String> images) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.city = city;
		this.area = area;
		this.address = address;
		this.rentAmount = rentAmount;
		this.propertyType = propertyType;
		this.isAvailable = isAvailable;
		this.hostId = hostId;
		this.postedOn = postedOn;
		this.images = images;
	}

	public PropertyDTO(Property entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.description = entity.getDescription();
		this.city = entity.getCity();
		this.area = entity.getArea();
		this.address = entity.getAddress();
		this.rentAmount = entity.getRentAmount();
		this.propertyType = entity.getPropertyType();
		this.isAvailable = entity.isAvailable();
		this.hostId = entity.getHostId();
		this.postedOn = entity.getPostedOn();
		this.images = entity.getImages();
	}

	// --- NEW: Mapper Method (DTO -> Entity) ---
	public Property toEntity() {
		Property prop = new Property(); // Uses the default no-args constructor

		prop.setTitle(this.title);
		prop.setDescription(this.description);
		prop.setCity(this.city);
		prop.setArea(this.area);
		prop.setAddress(this.address);
		prop.setRentAmount(this.rentAmount);
		prop.setPropertyType(this.propertyType);
		prop.setHostId(this.hostId);
		prop.setAvailable(this.isAvailable);
		prop.setImages(this.images);

		return prop;
	}

	// Inside PropertyDTO.java

	public void updateEntity(Property existingProperty) {
	    existingProperty.setTitle(this.title);
	    existingProperty.setDescription(this.description);
	    existingProperty.setCity(this.city);
	    existingProperty.setArea(this.area);
	    existingProperty.setAddress(this.address);
	    existingProperty.setRentAmount(this.rentAmount);
	    existingProperty.setPropertyType(this.propertyType);
	    existingProperty.setAvailable(this.isAvailable);
	    existingProperty.setImages(this.images);
	}
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getRentAmount() {
		return rentAmount;
	}

	public void setRentAmount(Double rentAmount) {
		this.rentAmount = rentAmount;
	}

	public PropertyType getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean available) {
		isAvailable = available;
	}

	public Integer getHostId() {
		return hostId;
	}

	public void setHostId(Integer hostId) {
		this.hostId = hostId;
	}

	public LocalDateTime getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(LocalDateTime postedOn) {
		this.postedOn = postedOn;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}
}