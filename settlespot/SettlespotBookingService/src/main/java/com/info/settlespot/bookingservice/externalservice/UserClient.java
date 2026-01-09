package com.info.settlespot.bookingservice.externalservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.info.settlespot.bookingservice.dto.UserDTO;

@FeignClient(name = "SETTLESPOTUSERSERVICE")
public interface UserClient {

    // Gets Tenant details
    @GetMapping("/users/tenants/{id}")
    UserDTO getTenantById(@PathVariable("id") Integer id);

    // Gets Host details
    @GetMapping("/users/hosts/{id}")
    UserDTO getHostById(@PathVariable("id") Integer id);
}