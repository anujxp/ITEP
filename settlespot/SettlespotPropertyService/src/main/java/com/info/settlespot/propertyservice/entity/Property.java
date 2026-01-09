package com.info.settlespot.propertyservice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import com.info.settlespot.propertyservice.enums.PropertyType;

@Entity
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;       

    @Column(nullable = false, length = 1000) 
    private String description;

    @Column(nullable = false)
    private String city;        

    @Column(nullable = false)
    private String area;
    
    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Double rentAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PropertyType propertyType; 

    private boolean isAvailable = true; 

    @Column(nullable = false)
    private Integer hostId; // Storing Host ID as Integer

    private LocalDateTime postedOn;

    // --- NEW ADDITION: IMAGES ---
    // This creates a separate table 'property_images' linked by 'property_id'
//    @ElementCollection
//    @CollectionTable(name = "property_images", joinColumns = @JoinColumn(name = "property_id"))
    @Column(name = "image_url")
    private List<String> images = new ArrayList<>();

    // --- Constructors ---
    
    // 1. No-Args Constructor
    public Property() {
        this.postedOn = LocalDateTime.now(); 
    }

    // 2. Parameterized Constructor
    public Property(String title, String description, String city, String address, 
                    Double rentAmount, PropertyType propertyType, Integer hostId, List<String> images,String area) {
        this.title = title;
        this.description = description;
        this.city = city;
        this.address = address;
        this.rentAmount = rentAmount;
        this.propertyType = propertyType;
        this.hostId = hostId;
        this.images = images;
        this.isAvailable = true;
        this.area = area;
        this.postedOn = LocalDateTime.now();
    }

    // --- Getters and Setters ---

    public Integer getId() { return id; }
    public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Double getRentAmount() { return rentAmount; }
    public void setRentAmount(Double rentAmount) { this.rentAmount = rentAmount; }

    public PropertyType getPropertyType() { return propertyType; }
    public void setPropertyType(PropertyType propertyType) { this.propertyType = propertyType; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public Integer getHostId() { return hostId; }
    public void setHostId(Integer hostId) { this.hostId = hostId; }

    public LocalDateTime getPostedOn() { return postedOn; }
    public void setPostedOn(LocalDateTime postedOn) { this.postedOn = postedOn; }

    // Getters/Setters for Images
    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }
}