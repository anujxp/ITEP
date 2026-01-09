package com.info.settlespot.propertyservice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import com.info.settlespot.propertyservice.dto.PropertyDTO;
import com.info.settlespot.propertyservice.entity.Property;
import com.info.settlespot.propertyservice.enums.PropertyType;
import com.info.settlespot.propertyservice.exception.ResourceNotFoundException;
import com.info.settlespot.propertyservice.externalService.UserClient;
import com.info.settlespot.propertyservice.repo.PropertyRepository;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserClient userClient;

    public PropertyService(PropertyRepository propertyRepository, UserClient userClient) {
        this.propertyRepository = propertyRepository;
        this.userClient = userClient;
    }

    @Transactional
    public PropertyDTO addProperty(PropertyDTO propertyDTO) {
        
        
        userClient.getHostById(propertyDTO.getHostId());
        Property property = propertyDTO.toEntity();
        Property savedProperty = propertyRepository.save(property);
        if(savedProperty == null)
        	throw new ResourceNotFoundException("peroperty not saved  to database ");
        return new PropertyDTO(savedProperty);
    }
 
 
    public PropertyDTO getPropertyById(Integer id) {
        Property property = propertyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Property not found with ID: " + id));
        return new PropertyDTO(property);
    }

 
    public List<PropertyDTO> findByCityAndTypeAndArea(String city, PropertyType type, String area) {
        List<Property> properties = propertyRepository.findByCityAndPropertyTypeAndArea(city, type, area);
        if (properties.isEmpty()) {
            throw new ResourceNotFoundException("No properties found for the selected filters.");
        }
        return properties.stream()
                .map(PropertyDTO::new) // Uses the Copy Constructor: new PropertyDTO(property)
                .collect(Collectors.toList());
    }
    
    
    @Transactional
    public PropertyDTO updateProperty(Integer id, PropertyDTO propertyDTO) {
        Property existingProperty = propertyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Property not found with ID: " + id));
        propertyDTO.updateEntity(existingProperty);
        Property updatedProperty = propertyRepository.save(existingProperty);
        return new PropertyDTO(updatedProperty);
    }
    
    @Transactional
    public void deleteProperty(Integer id) {
        // Check if exists first to throw custom exception
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with ID: " + id));
        
        propertyRepository.delete(property);
    }
    
 // Inside PropertyService.java

    @Transactional
    public void updateAvailability(Integer id, boolean isAvailable) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with ID: " + id));
        
        property.setAvailable(isAvailable);
        propertyRepository.save(property);
    }
}