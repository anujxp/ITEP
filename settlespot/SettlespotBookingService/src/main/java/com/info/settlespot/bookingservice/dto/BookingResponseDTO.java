package com.info.settlespot.bookingservice.dto;

import com.info.settlespot.bookingservice.enums.BookingStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingResponseDTO {
    private Integer bookingId;
    private String propertyName;
    private String tenantName;
    private String hostName;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double totalAmount;
    private BookingStatus status;
    private LocalDateTime createdAt;

    // --- Getters and Setters ---
    // (Generate these for all fields)
}