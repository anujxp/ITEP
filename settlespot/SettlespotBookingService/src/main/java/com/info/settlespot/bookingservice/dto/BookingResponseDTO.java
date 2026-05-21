package com.info.settlespot.bookingservice.dto;

import com.info.settlespot.bookingservice.entity.Booking;
import com.info.settlespot.bookingservice.enums.BookingStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponseDTO {

    private Integer id;
    private Integer propertyId;
    private String propertyTitle;
    private Integer tenantId;
    private String tenantName;
    private Integer hostId;
    private String hostName;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double totalAmount;
    private BookingStatus status;
    private Integer rating;
    private String ratingComment;
    private boolean isRated;
    private LocalDateTime createdAt;

    public static BookingResponseDTO from(Booking b) {
        return BookingResponseDTO.builder()
                .id(b.getId())
                .propertyId(b.getPropertyId())
                .propertyTitle(b.getPropertyTitle())
                .tenantId(b.getTenantId())
                .tenantName(b.getTenantName())
                .hostId(b.getHostId())
                .hostName(b.getHostName())
                .checkInDate(b.getCheckInDate())
                .checkOutDate(b.getCheckOutDate())
                .totalAmount(b.getTotalAmount())
                .status(b.getStatus())
                .rating(b.getRating())
                .ratingComment(b.getRatingComment())
                .isRated(b.isRated())
                .createdAt(b.getCreatedAt())
                .build();
    }
}