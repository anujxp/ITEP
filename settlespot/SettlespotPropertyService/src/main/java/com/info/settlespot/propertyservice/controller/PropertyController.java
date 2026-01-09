package com.info.settlespot.propertyservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.info.settlespot.propertyservice.dto.PropertyDTO;
import com.info.settlespot.propertyservice.enums.PropertyType;
import com.info.settlespot.propertyservice.service.PropertyService;

import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping
    public ResponseEntity<PropertyDTO> addProperty(@RequestBody PropertyDTO propertyDTO) {
        PropertyDTO savedProperty = propertyService.addProperty(propertyDTO);
        return new ResponseEntity<>(savedProperty, HttpStatus.CREATED);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<PropertyDTO> getPropertyById(@PathVariable Integer id) {
        PropertyDTO property = propertyService.getPropertyById(id);
        return ResponseEntity.ok(property);
    }

    
    // Endpoint: GET /properties/search?city=Pune&type=PG&area=Hinjewadi
    @GetMapping("/search")
    public ResponseEntity<List<PropertyDTO>> searchProperties(
            @RequestParam String city,
            @RequestParam PropertyType type,
            @RequestParam String area) {
        List<PropertyDTO> properties = propertyService.findByCityAndTypeAndArea(city, type, area);
        return ResponseEntity.ok(properties);
    }
    
    
 // 4. Update Property
    // Endpoint: PUT /properties/{id}
    @PutMapping("/{id}")
    public ResponseEntity<PropertyDTO> updateProperty(
            @PathVariable Integer id, 
            @RequestBody PropertyDTO propertyDTO) {
        
        PropertyDTO updatedProperty = propertyService.updateProperty(id, propertyDTO);
        return ResponseEntity.ok(updatedProperty);
    }

    // 5. Delete Property
    // Endpoint: DELETE /properties/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProperty(@PathVariable Integer id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.ok("Property deleted successfully with ID: " + id);
    }
 // Inside PropertyController.java (in the Property Service project)
    @PutMapping("/{id}/availability")
    public ResponseEntity<Void> updateAvailability(@PathVariable Integer id, @RequestParam boolean isAvailable) {
        propertyService.updateAvailability(id, isAvailable);
        return ResponseEntity.ok().build();
    }
}