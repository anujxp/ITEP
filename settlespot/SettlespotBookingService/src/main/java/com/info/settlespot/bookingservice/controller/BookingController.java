package com.info.settlespot.bookingservice.controller;

import com.info.settlespot.bookingservice.dto.*;
import com.info.settlespot.bookingservice.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    // ─── TENANT: Create booking ────────────────────────────────────────────────
    @PostMapping
    public ResponseEntity<ApiResponse<BookingResponseDTO>> create(
            @Valid @RequestBody BookingRequestDTO req,
            @RequestHeader("X-User-Id") String tenantId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Booking request sent to host",
                        bookingService.createBooking(req, Integer.parseInt(tenantId))));
    }

    // ─── TENANT: View own bookings ─────────────────────────────────────────────
    @GetMapping("/my-bookings")
    public ResponseEntity<ApiResponse<List<BookingResponseDTO>>> myBookings(
            @RequestHeader("X-User-Id") String tenantId) {
        return ResponseEntity.ok(
                ApiResponse.success("Your bookings",
                        bookingService.getBookingsByTenant(Integer.parseInt(tenantId))));
    }

    // ─── TENANT: Cancel a booking ──────────────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<BookingResponseDTO>> cancel(
            @PathVariable Integer id,
            @RequestHeader("X-User-Id") String tenantId) {
        return ResponseEntity.ok(
                ApiResponse.success("Booking cancelled",
                        bookingService.cancelBooking(id, Integer.parseInt(tenantId))));
    }

    // ─── TENANT: Rate a booking (after checkout date) ─────────────────────────
    @PostMapping("/{id}/rate")
    public ResponseEntity<ApiResponse<BookingResponseDTO>> rate(
            @PathVariable Integer id,
            @Valid @RequestBody RatingRequestDTO req,
            @RequestHeader("X-User-Id") String tenantId) {
        return ResponseEntity.ok(
                ApiResponse.success("Thank you for your rating!",
                        bookingService.rateBooking(id, req, Integer.parseInt(tenantId))));
    }

    // ─── HOST: View all bookings for my properties ─────────────────────────────
    @GetMapping("/host/all")
    public ResponseEntity<ApiResponse<List<BookingResponseDTO>>> hostBookings(
            @RequestHeader("X-User-Id") String hostId) {
        return ResponseEntity.ok(
                ApiResponse.success("Host bookings",
                        bookingService.getBookingsByHost(Integer.parseInt(hostId))));
    }

    // ─── HOST: View pending bookings ───────────────────────────────────────────
    @GetMapping("/host/pending")
    public ResponseEntity<ApiResponse<List<BookingResponseDTO>>> pendingBookings(
            @RequestHeader("X-User-Id") String hostId) {
        return ResponseEntity.ok(
                ApiResponse.success("Pending bookings",
                        bookingService.getPendingBookingsByHost(Integer.parseInt(hostId))));
    }

    // ─── HOST: Approve or Reject a booking ────────────────────────────────────
    @PatchMapping("/{id}/review")
    public ResponseEntity<ApiResponse<BookingResponseDTO>> reviewBooking(
            @PathVariable Integer id,
            @RequestParam String action,
            @RequestHeader("X-User-Id") String hostId) {
        return ResponseEntity.ok(
                ApiResponse.success("Booking " + action.toLowerCase() + "d",
                        bookingService.approveOrRejectBooking(
                                id, action, Integer.parseInt(hostId))));
    }

    // ─── ADMIN: View all bookings ──────────────────────────────────────────────
    @GetMapping("/admin/all")
    public ResponseEntity<ApiResponse<List<BookingResponseDTO>>> allBookings() {
        return ResponseEntity.ok(
                ApiResponse.success("All bookings", bookingService.getAllBookings()));
    }
}