package com.info.settlespot.bookingservice.controller;

import com.info.settlespot.bookingservice.dto.BookingRequestDTO;
import com.info.settlespot.bookingservice.entity.Booking;
import com.info.settlespot.bookingservice.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody BookingRequestDTO dto) {
        Booking booking = bookingService.createBooking(dto);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }


    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<Booking>> getByTenant(@PathVariable Integer tenantId) {
        return ResponseEntity.ok(bookingService.getBookingsByTenant(tenantId));
    }

    // 3. Get Bookings for a Host
    @GetMapping("/host/{hostId}")
    public ResponseEntity<List<Booking>> getByHost(@PathVariable Integer hostId) {
        return ResponseEntity.ok(bookingService.getBookingsByHost(hostId));
    }

    // 4. Cancel a Booking
    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelBooking(@PathVariable Integer id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.ok("Booking cancelled successfully and property is now available.");
    }
    
}