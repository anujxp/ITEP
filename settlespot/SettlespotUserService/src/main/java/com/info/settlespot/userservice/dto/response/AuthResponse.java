package com.info.settlespot.userservice.dto.response;

import com.info.settlespot.userservice.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String tokenType = "Bearer";
    private Integer userId;
    private String fullName;
    private String email;
    private UserRole role;
}