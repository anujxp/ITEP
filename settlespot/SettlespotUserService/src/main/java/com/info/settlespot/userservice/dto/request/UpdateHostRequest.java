package com.info.settlespot.userservice.dto.request;

import lombok.Data;

@Data
public class UpdateHostRequest {
    private String fullName;
    private String phoneNumber;
    private String businessName;
    private String officeAddress;
    private Boolean isActive;
}