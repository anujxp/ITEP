package com.info.settlespot.propertyservice.externalService;

import com.info.settlespot.propertyservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "SETTLESPOTUSERSERVICE")
public interface UserClient {

    @GetMapping("/internal/users/hosts/{id}")
    UserDTO getHostById(@PathVariable("id") Integer id);
}