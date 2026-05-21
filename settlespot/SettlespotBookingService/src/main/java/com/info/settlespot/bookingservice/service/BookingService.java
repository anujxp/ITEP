package com.info.settlespot.bookingservice.service;

import com.info.settlespot.bookingservice.dto.*;
import com.info.settlespot.bookingservice.entity.Booking;
import com.info.settlespot.bookingservice.enums.BookingStatus;
import com.info.settlespot.bookingservice.exception.BookingException;
import com.info.settlespot.bookingservice.exception.ResourceNotFoundException;
import com.info.settlespot.bookingservice.externalservice.PropertyClient;
import com.info.settlespot.bookingservice.externalservice.UserClient;
import com.info.settlespot.bookingservice.repo.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final PropertyClient propertyClient;
    private final UserClient userClient;

    @Transactional
    public BookingResponseDTO createBooking(BookingRequestDTO req, Integer tenantId) {
        PropertyDTO property = propertyClient.getPropertyById(req.getPropertyId());

        if (!"APPROVED".equals(property.getApprovalStatus())) {
            throw new BookingException("Property is not yet approved for booking.");
        }
        if (!property.isAvailable()) {
            throw new BookingException("Property is already occupied.");
        }

        UserDTO tenant = userClient.getTenantById(tenantId);
        UserDTO host = userClient.getHostById(property.getHostId());

        long days = ChronoUnit.DAYS.between(req.getCheckInDate(), req.getCheckOutDate());
        if (days <= 0) throw new BookingException("Check-out must be after check-in.");

        double totalAmount = property.getRentAmount() * days;

        Booking booking = Booking.builder()
                .propertyId(req.getPropertyId())
                .tenantId(tenantId)
                .hostId(property.getHostId())
                .propertyTitle(property.getTitle())
                .tenantName(tenant.getFullName())
                .hostName(host.getFullName())
                .checkInDate(req.getCheckInDate())
                .checkOutDate(req.getCheckOutDate())
                .totalAmount(totalAmount)
                .status(BookingStatus.PENDING)
                .build();

        return BookingResponseDTO.from(bookingRepository.save(booking));
    }

    public List<BookingResponseDTO> getBookingsByTenant(Integer tenantId) {
        return bookingRepository.findByTenantId(tenantId)
                .stream().map(BookingResponseDTO::from).collect(Collectors.toList());
    }

    public List<BookingResponseDTO> getBookingsByHost(Integer hostId) {
        return bookingRepository.findByHostId(hostId)
                .stream().map(BookingResponseDTO::from).collect(Collectors.toList());
    }

    public List<BookingResponseDTO> getPendingBookingsByHost(Integer hostId) {
        return bookingRepository.findByHostIdAndStatus(hostId, BookingStatus.PENDING)
                .stream().map(BookingResponseDTO::from).collect(Collectors.toList());
    }

    @Transactional
    public BookingResponseDTO approveOrRejectBooking(Integer bookingId,
                                                     String action,
                                                     Integer hostId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found: " + bookingId));

        if (!booking.getHostId().equals(hostId)) {
            throw new BookingException("You are not authorized to manage this booking.");
        }

        if (booking.getStatus() != BookingStatus.PENDING) {
            throw new BookingException("Booking is no longer pending.");
        }

        if ("APPROVE".equalsIgnoreCase(action)) {
            booking.setStatus(BookingStatus.APPROVED);
            // Mark property as unavailable
            propertyClient.updateAvailability(booking.getPropertyId(), false);
        } else if ("REJECT".equalsIgnoreCase(action)) {
            booking.setStatus(BookingStatus.REJECTED);
        } else {
            throw new BookingException("Invalid action. Use APPROVE or REJECT.");
        }

        return BookingResponseDTO.from(bookingRepository.save(booking));
    }

    @Transactional
    public BookingResponseDTO cancelBooking(Integer bookingId, Integer tenantId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found: " + bookingId));

        if (!booking.getTenantId().equals(tenantId)) {
            throw new BookingException("You are not authorized to cancel this booking.");
        }

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new BookingException("Booking is already cancelled.");
        }

        // Free the property if it was approved
        if (booking.getStatus() == BookingStatus.APPROVED) {
            propertyClient.updateAvailability(booking.getPropertyId(), true);
        }

        booking.setStatus(BookingStatus.CANCELLED);
        return BookingResponseDTO.from(bookingRepository.save(booking));
    }

    @Transactional
    public BookingResponseDTO rateBooking(Integer bookingId,
                                          RatingRequestDTO req,
                                          Integer tenantId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found: " + bookingId));

        if (!booking.getTenantId().equals(tenantId)) {
            throw new BookingException("You can only rate your own bookings.");
        }

        if (booking.getStatus() != BookingStatus.APPROVED) {
            throw new BookingException("Only approved/completed bookings can be rated.");
        }

        if (booking.isRated()) {
            throw new BookingException("You have already rated this booking.");
        }

        if (booking.getCheckOutDate().isAfter(LocalDate.now())) {
            throw new BookingException("You can only rate after your check-out date.");
        }

        booking.setRating(req.getRating());
        booking.setRatingComment(req.getComment());
        booking.setRated(true);

        // Update property average rating
        try {
            propertyClient.updateRating(booking.getPropertyId(), req.getRating());
        } catch (Exception ignored) {} // Non-critical

        return BookingResponseDTO.from(bookingRepository.save(booking));
    }

    public List<BookingResponseDTO> getAllBookings() {
        return bookingRepository.findAll()
                .stream().map(BookingResponseDTO::from).collect(Collectors.toList());
    }
}