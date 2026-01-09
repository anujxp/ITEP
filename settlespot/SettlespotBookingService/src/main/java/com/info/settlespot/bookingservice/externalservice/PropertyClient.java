package com.info.settlespot.bookingservice.externalservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.info.settlespot.bookingservice.dto.PropertyDTO; // We'll create this next

@FeignClient(name = "SETTLESPOTPROPERTYSERVICE")
public interface PropertyClient {

    @GetMapping("/properties/{id}")
    PropertyDTO getPropertyById(@PathVariable("id") Integer id);
    @PutMapping("/properties/{id}/availability")
    void updateAvailability(@PathVariable("id") Integer id, @RequestParam("isAvailable") boolean isAvailable);
}