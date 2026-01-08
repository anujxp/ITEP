package com.info.settlespot.userservice.dto;

import java.time.LocalDateTime;

public class ErrorResponseDTO {
    private String message;
    private int statusCode;
    public ErrorResponseDTO(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

  
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public int getStatusCode() { return statusCode; }
    public void setStatusCode(int statusCode) { this.statusCode = statusCode; }

    
}