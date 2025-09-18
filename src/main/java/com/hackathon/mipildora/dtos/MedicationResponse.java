package com.hackathon.mipildora.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record MedicationResponse(
        Long id,
        String name,
        String dosage,
        Integer frequency,
        LocalTime time,
        LocalDate initDate,
        LocalDateTime finalDate,
        Boolean taken,
        Boolean alert,
        LocalDateTime createDate,
        String description) {
}
