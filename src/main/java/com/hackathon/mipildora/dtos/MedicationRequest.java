package com.hackathon.mipildora.dtos;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record MedicationRequest(
        @NotBlank(message = "Name is required")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        String name,

        @NotBlank(message = "Dosage is required")
        @Size(max = 50, message = "Dosage must not exceed 50 characters")
        String dosage,

        @NotNull(message = "Frequency is required")
        @Min(value = 1, message = "Frequency must be at least 1 time per day")
        @Max(value = 24, message = "Frequency cannot exceed 24 times per day")
        Integer frequency,

        @NotNull(message = "Time is required")
        LocalTime time,

        @NotNull(message = "Init date is required")
        LocalDate initDate,

        LocalDateTime finalDate,

        @Size(max = 500, message = "Description must not exceed 500 characters")
        String description
        ) {
}
