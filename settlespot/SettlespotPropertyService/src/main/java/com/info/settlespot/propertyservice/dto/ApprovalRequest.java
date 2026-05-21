package com.info.settlespot.propertyservice.dto;

import lombok.Data;

@Data
public class ApprovalRequest {
    private String action;     // "APPROVE" or "REJECT"
    private String reason;     // Required when rejecting
}