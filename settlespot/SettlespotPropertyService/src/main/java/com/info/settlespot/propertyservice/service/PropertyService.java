package com.info.settlespot.propertyservice.service;

import com.info.settlespot.propertyservice.dto.ApprovalRequest;
import com.info.settlespot.propertyservice.dto.PropertyDTO;
import com.info.settlespot.propertyservice.entity.Property;
import com.info.settlespot.propertyservice.enums.PropertyApprovalStatus;
import com.info.settlespot.propertyservice.enums.PropertyType;
import com.info.settlespot.propertyservice.exception.ResourceNotFoundException;
import com.info.settlespot.propertyservice.externalService.UserClient;
import com.info.settlespot.propertyservice.repo.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserClient userClient;

    @Transactional
    public PropertyDTO addProperty(PropertyDTO dto) {
        // Verify host exists via feign client
        userClient.getHostById(dto.getHostId());

        Property property = dto.toEntity();
        // Newly added property goes to PENDING_APPROVAL
        property.setApprovalStatus(PropertyApprovalStatus.PENDING_APPROVAL);

        return PropertyDTO.from(propertyRepository.save(property));
    }

    public PropertyDTO getPropertyById(Integer id) {
        Property p = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + id));
        return PropertyDTO.from(p);
    }

    // Only return APPROVED and available properties to tenants
    public List<PropertyDTO> getAvailableProperties() {
        return propertyRepository.findByApprovalStatusAndIsAvailable(
                        PropertyApprovalStatus.APPROVED, true)
                .stream().map(PropertyDTO::from).collect(Collectors.toList());
    }

    // Host can see their own properties (all statuses)
    public List<PropertyDTO> getPropertiesByHost(Integer hostId) {
        return propertyRepository.findByHostId(hostId)
                .stream().map(PropertyDTO::from).collect(Collectors.toList());
    }

    // Admin sees PENDING properties
    public List<PropertyDTO> getPendingProperties() {
        return propertyRepository.findByApprovalStatus(PropertyApprovalStatus.PENDING_APPROVAL)
                .stream().map(PropertyDTO::from).collect(Collectors.toList());
    }

    public List<PropertyDTO> findByCityAndTypeAndArea(String city, PropertyType type, String area) {
        return propertyRepository.findByCityAndPropertyTypeAndArea(city, type, area)
                .stream()
                .filter(p -> p.getApprovalStatus() == PropertyApprovalStatus.APPROVED)
                .map(PropertyDTO::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public PropertyDTO updateProperty(Integer id, PropertyDTO dto) {
        Property existing = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + id));
        dto.updateEntity(existing);
        // Reset to PENDING after update so admin re-approves
        existing.setApprovalStatus(PropertyApprovalStatus.PENDING_APPROVAL);
        return PropertyDTO.from(propertyRepository.save(existing));
    }

    @Transactional
    public void deleteProperty(Integer id) {
        if (!propertyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Property not found with id: " + id);
        }
        propertyRepository.deleteById(id);
    }

    @Transactional
    public void updateAvailability(Integer id, boolean isAvailable) {
        Property p = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found: " + id));
        p.setAvailable(isAvailable);
        propertyRepository.save(p);
    }

    @Transactional
    public PropertyDTO approveOrRejectProperty(Integer id, ApprovalRequest req) {
        Property p = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found: " + id));

        if ("APPROVE".equalsIgnoreCase(req.getAction())) {
            p.setApprovalStatus(PropertyApprovalStatus.APPROVED);
            p.setRejectionReason(null);
        } else if ("REJECT".equalsIgnoreCase(req.getAction())) {
            p.setApprovalStatus(PropertyApprovalStatus.REJECTED);
            p.setRejectionReason(req.getReason());
        } else {
            throw new RuntimeException("Invalid action. Use APPROVE or REJECT");
        }

        return PropertyDTO.from(propertyRepository.save(p));
    }

    @Transactional
    public PropertyDTO updateRating(Integer id, double newRating) {
        Property p = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found: " + id));

        int total = p.getTotalRatings() + 1;
        double avg = ((p.getAverageRating() * p.getTotalRatings()) + newRating) / total;
        p.setTotalRatings(total);
        p.setAverageRating(Math.round(avg * 10.0) / 10.0);

        return PropertyDTO.from(propertyRepository.save(p));
    }

    public List<PropertyDTO> getFilteredProperties(String city, String type,
                                                   Double minPrice, Double maxPrice) {
        PropertyType pType = null;
        if (type != null && !type.isBlank()) {
            try { pType = PropertyType.valueOf(type.toUpperCase()); }
            catch (IllegalArgumentException ignored) {}
        }
        return propertyRepository.findByFilters(city, pType != null ? pType.name() : null,
                        minPrice, maxPrice)
                .stream()
                .filter(p -> p.getApprovalStatus() == PropertyApprovalStatus.APPROVED)
                .map(PropertyDTO::from)
                .collect(Collectors.toList());
    }
}