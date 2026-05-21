package com.info.settlespot.bookingservice.externalservice;

import com.info.settlespot.bookingservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "SETTLESPOTUSERSERVICE")
public interface UserClient {

    @GetMapping("/internal/users/tenants/{id}")
    UserDTO getTenantById(@PathVariable("id") Integer id);

    @GetMapping("/internal/users/hosts/{id}")
    UserDTO getHostById(@PathVariable("id") Integer id);
}