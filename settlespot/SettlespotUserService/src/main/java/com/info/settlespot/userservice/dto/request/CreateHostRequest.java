package com.info.settlespot.userservice.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateHostRequest {

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    private String phoneNumber;

    private String businessName;
    private String officeAddress;
}