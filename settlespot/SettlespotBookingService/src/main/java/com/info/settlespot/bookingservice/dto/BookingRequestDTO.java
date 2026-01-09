package com.info.settlespot.bookingservice.dto;

import java.time.LocalDate;

public class BookingRequestDTO {
    private Integer propertyId;
    private Integer tenantId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    // --- Getters and Setters ---

    public Integer getPropertyId() { return propertyId; }
    public void setPropertyId(Integer propertyId) { this.propertyId = propertyId; }

    public Integer getTenantId() { return tenantId; }
    public void setTenantId(Integer tenantId) { this.tenantId = tenantId; }

    public LocalDate getCheckInDate() { return checkInDate; }
    public void setCheckInDate(LocalDate checkInDate) { this.checkInDate = checkInDate; }

    public LocalDate getCheckOutDate() { return checkOutDate; }
    public void setCheckOutDate(LocalDate checkOutDate) { this.checkOutDate = checkOutDate; }
}