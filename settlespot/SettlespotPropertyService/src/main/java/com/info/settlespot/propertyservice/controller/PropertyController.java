package com.info.settlespot.propertyservice.controller;

import com.info.settlespot.propertyservice.dto.ApiResponse;
import com.info.settlespot.propertyservice.dto.ApprovalRequest;
import com.info.settlespot.propertyservice.dto.PropertyDTO;
import com.info.settlespot.propertyservice.enums.PropertyType;
import com.info.settlespot.propertyservice.service.PropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    // ─── HOST: Add new property (goes to pending approval) ────────────────────
    @PostMapping
    public ResponseEntity<ApiResponse<PropertyDTO>> addProperty(
            @Valid @RequestBody PropertyDTO dto,
            @RequestHeader("X-User-Id") String hostId) {
        dto.setHostId(Integer.parseInt(hostId));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Property submitted for admin approval",
                        propertyService.addProperty(dto)));
    }

    // ─── PUBLIC: Get single property ──────────────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PropertyDTO>> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(
                ApiResponse.success("Property found", propertyService.getPropertyById(id)));
    }

    // ─── TENANT: Browse approved + available properties ───────────────────────
    @GetMapping("/available")
    public ResponseEntity<ApiResponse<List<PropertyDTO>>> getAvailable() {
        return ResponseEntity.ok(
                ApiResponse.success("Available properties", propertyService.getAvailableProperties()));
    }

    // ─── HOST: View own properties ────────────────────────────────────────────
    @GetMapping("/my-properties")
    public ResponseEntity<ApiResponse<List<PropertyDTO>>> getMyProperties(
            @RequestHeader("X-User-Id") String hostId) {
        return ResponseEntity.ok(
                ApiResponse.success("Your properties",
                        propertyService.getPropertiesByHost(Integer.parseInt(hostId))));
    }

    // ─── ADMIN: View pending properties ──────────────────────────────────────
    @GetMapping("/admin/pending")
    public ResponseEntity<ApiResponse<List<PropertyDTO>>> getPending() {
        return ResponseEntity.ok(
                ApiResponse.success("Pending properties", propertyService.getPendingProperties()));
    }

    // ─── ADMIN: Approve / Reject a property ──────────────────────────────────
    @PatchMapping("/admin/{id}/review")
    public ResponseEntity<ApiResponse<PropertyDTO>> reviewProperty(
            @PathVariable Integer id,
            @RequestBody ApprovalRequest req) {
        return ResponseEntity.ok(
                ApiResponse.success("Property " + req.getAction().toLowerCase() + "d",
                        propertyService.approveOrRejectProperty(id, req)));
    }

    // ─── SEARCH ───────────────────────────────────────────────────────────────
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<PropertyDTO>>> search(
            @RequestParam String city,
            @RequestParam PropertyType type,
            @RequestParam String area) {
        return ResponseEntity.ok(
                ApiResponse.success("Search results",
                        propertyService.findByCityAndTypeAndArea(city, type, area)));
    }

    // ─── FILTER ───────────────────────────────────────────────────────────────
    @GetMapping("/filter")
    public ResponseEntity<ApiResponse<List<PropertyDTO>>> filter(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {
        return ResponseEntity.ok(
                ApiResponse.success("Filtered results",
                        propertyService.getFilteredProperties(city, type, minPrice, maxPrice)));
    }

    // ─── HOST: Update property ────────────────────────────────────────────────
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PropertyDTO>> update(
            @PathVariable Integer id,
            @Valid @RequestBody PropertyDTO dto) {
        return ResponseEntity.ok(
                ApiResponse.success("Property updated (re-submitted for approval)",
                        propertyService.updateProperty(id, dto)));
    }

    // ─── HOST: Delete property ────────────────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.ok(ApiResponse.success("Property deleted"));
    }

    // ─── INTERNAL: Update availability (called by Booking Service) ────────────
    @PutMapping("/{id}/availability")
    public ResponseEntity<Void> updateAvailability(
            @PathVariable Integer id,
            @RequestParam boolean isAvailable) {
        propertyService.updateAvailability(id, isAvailable);
        return ResponseEntity.ok().build();
    }

    // ─── INTERNAL: Update rating (called by Booking Service) ──────────────────
    @PutMapping("/internal/{id}/rating")
    public ResponseEntity<Void> updateRating(
            @PathVariable Integer id,
            @RequestParam double rating) {
        propertyService.updateRating(id, rating);
        return ResponseEntity.ok().build();
    }
}