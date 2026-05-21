package com.info.settlespot.bookingservice.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RatingRequestDTO {

    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private Integer rating;

    @Size(max = 500, message = "Comment too long")
    private String comment;
}