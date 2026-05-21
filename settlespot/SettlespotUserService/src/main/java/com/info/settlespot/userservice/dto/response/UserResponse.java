package com.info.settlespot.userservice.dto.response;

import com.info.settlespot.userservice.entity.AppUser;
import com.info.settlespot.userservice.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Integer id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private UserRole role;
    private boolean isActive;

    // Host specific
    private String businessName;
    private String officeAddress;
    private boolean isHostApproved;

    // Tenant specific
    private Integer age;
    private String occupation;
    private String permanentAddress;

    private LocalDateTime createdAt;

    public static UserResponse from(AppUser user) {
        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .isActive(user.isActive())
                .businessName(user.getBusinessName())
                .officeAddress(user.getOfficeAddress())
                .isHostApproved(user.isHostApproved())
                .age(user.getAge())
                .occupation(user.getOccupation())
                .permanentAddress(user.getPermanentAddress())
                .createdAt(user.getCreatedAt())
                .build();
    }
}