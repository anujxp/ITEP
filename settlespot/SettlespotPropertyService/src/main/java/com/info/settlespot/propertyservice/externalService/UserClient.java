package com.info.settlespot.propertyservice.externalService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.info.settlespot.propertyservice.dto.UserDTO;

// Matches the application name of your User Service in Eureka
@FeignClient(name = "SETTLESPOTUSERSERVICE") 
public interface UserClient {

    // URL becomes: http://USER-SERVICE/users/hosts/{id}
    @GetMapping("/users/hosts/{id}")
    UserDTO getHostById(@PathVariable("id") Integer id);

}