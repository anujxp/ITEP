package com.info.settlespot.propertyservice.dto;

import com.info.settlespot.propertyservice.entity.Property;
import com.info.settlespot.propertyservice.enums.PropertyApprovalStatus;
import com.info.settlespot.propertyservice.enums.PropertyType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDTO {

	private Integer id;

	@NotBlank(message = "Title is required")
	private String title;

	@NotBlank(message = "Description is required")
	private String description;

	@NotBlank(message = "City is required")
	private String city;

	@NotBlank(message = "Area is required")
	private String area;

	@NotBlank(message = "Address is required")
	private String address;

	@NotNull(message = "Rent amount is required")
	@Positive(message = "Rent amount must be positive")
	private Double rentAmount;

	@NotNull(message = "Property type is required")
	private PropertyType propertyType;

	private boolean isAvailable;
	private Integer hostId;
	private PropertyApprovalStatus approvalStatus;
	private String rejectionReason;
	private LocalDateTime postedOn;
	private Double averageRating;
	private Integer totalRatings;

	@Builder.Default
	private List<String> images = new ArrayList<>();

	public static PropertyDTO from(Property p) {
		return PropertyDTO.builder()
				.id(p.getId())
				.title(p.getTitle())
				.description(p.getDescription())
				.city(p.getCity())
				.area(p.getArea())
				.address(p.getAddress())
				.rentAmount(p.getRentAmount())
				.propertyType(p.getPropertyType())
				.isAvailable(p.isAvailable())
				.hostId(p.getHostId())
				.approvalStatus(p.getApprovalStatus())
				.rejectionReason(p.getRejectionReason())
				.postedOn(p.getPostedOn())
				.averageRating(p.getAverageRating())
				.totalRatings(p.getTotalRatings())
				.images(p.getImages())
				.build();
	}

	public Property toEntity() {
		return Property.builder()
				.title(this.title)
				.description(this.description)
				.city(this.city)
				.area(this.area)
				.address(this.address)
				.rentAmount(this.rentAmount)
				.propertyType(this.propertyType)
				.hostId(this.hostId)
				.isAvailable(true)
				.images(this.images != null ? this.images : new ArrayList<>())
				.build();
	}

	public void updateEntity(Property p) {
		p.setTitle(this.title);
		p.setDescription(this.description);
		p.setCity(this.city);
		p.setArea(this.area);
		p.setAddress(this.address);
		p.setRentAmount(this.rentAmount);
		p.setPropertyType(this.propertyType);
		p.setAvailable(this.isAvailable);
		if (this.images != null) p.setImages(this.images);
	}
}