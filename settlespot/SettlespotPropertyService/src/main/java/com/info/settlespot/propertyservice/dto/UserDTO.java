package com.info.settlespot.propertyservice.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String businessName;
    private String officeAddress;
    private boolean isHostApproved;
}