package com.info.settlespot.bookingservice.service;

import com.info.settlespot.bookingservice.dto.BookingRequestDTO;
import com.info.settlespot.bookingservice.dto.PropertyDTO;
import com.info.settlespot.bookingservice.entity.Booking;
import com.info.settlespot.bookingservice.enums.BookingStatus;
import com.info.settlespot.bookingservice.exception.BookingException;
import com.info.settlespot.bookingservice.exception.ResourceNotFoundException;
import com.info.settlespot.bookingservice.externalservice.PropertyClient;
import com.info.settlespot.bookingservice.externalservice.UserClient;
import com.info.settlespot.bookingservice.repo.BookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final PropertyClient propertyClient;
    private final UserClient userClient;

    public BookingService(BookingRepository bookingRepository, PropertyClient propertyClient, UserClient userClient) {
        this.bookingRepository = bookingRepository;
        this.propertyClient = propertyClient;
        this.userClient = userClient;
    }
    @Transactional
    public Booking createBooking(BookingRequestDTO request) {

        PropertyDTO property = propertyClient.getPropertyById(request.getPropertyId());
        if (!property.isAvailable()) {
            throw new BookingException("Property is already occupied.");
        }
        userClient.getTenantById(request.getTenantId());
        long days = ChronoUnit.DAYS.between(request.getCheckInDate(), request.getCheckOutDate());
        if (days <= 0) throw new BookingException("Invalid dates provided.");
        
        double totalAmount = property.getRentAmount() * days;

        Booking booking = new Booking();
        booking.setPropertyId(request.getPropertyId());
        booking.setTenantId(request.getTenantId());
        booking.setHostId(property.getHostId());
        booking.setCheckInDate(request.getCheckInDate());
        booking.setCheckOutDate(request.getCheckOutDate());
        booking.setTotalAmount(totalAmount);
        booking.setStatus(BookingStatus.CONFIRMED);

        propertyClient.updateAvailability(request.getPropertyId(), false);

        return bookingRepository.save(booking);
    }
    
    
    public List<Booking> getBookingsByTenant(Integer tenantId) {
        return bookingRepository.findByTenantId(tenantId);
    }

    public List<Booking> getBookingsByHost(Integer hostId) {
        return bookingRepository.findByHostId(hostId);
    }
    
    @Transactional
    public void cancelBooking(Integer bookingId) {
        // 1. Find the booking
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with ID: " + bookingId));

        // 2. Update status to CANCELLED
        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);

        // 3. IMPORTANT: Tell Property Service to make it available again
        propertyClient.updateAvailability(booking.getPropertyId(), true);
    }
    
}