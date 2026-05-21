package com.info.settlespot.bookingservice.externalservice;

import com.info.settlespot.bookingservice.dto.PropertyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "SETTLESPOTPROPERTYSERVICE")
public interface PropertyClient {

    @GetMapping("/properties/{id}")
    PropertyDTO getPropertyById(@PathVariable("id") Integer id);

    @PutMapping("/properties/{id}/availability")
    void updateAvailability(@PathVariable Integer id, @RequestParam boolean isAvailable);

    @PutMapping("/properties/internal/{id}/rating")
    void updateRating(@PathVariable Integer id, @RequestParam double rating);
}