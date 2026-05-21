package com.info.settlespot.propertyservice.entity;

import com.info.settlespot.propertyservice.enums.PropertyApprovalStatus;
import com.info.settlespot.propertyservice.enums.PropertyType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "properties")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Builder.Default
    private boolean isAvailable = true;

    @Column(nullable = false)
    private Integer hostId;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private PropertyApprovalStatus approvalStatus = PropertyApprovalStatus.PENDING_APPROVAL;

    private String rejectionReason;

    @Builder.Default
    private LocalDateTime postedOn = LocalDateTime.now();

    // Fixed: ElementCollection for proper List<String> storage
    @ElementCollection
    @CollectionTable(name = "property_images",
            joinColumns = @JoinColumn(name = "property_id"))
    @Column(name = "image_url")
    @Builder.Default
    private List<String> images = new ArrayList<>();

    // Average rating computed from bookings
    @Builder.Default
    private Double averageRating = 0.0;

    @Builder.Default
    private Integer totalRatings = 0;
}