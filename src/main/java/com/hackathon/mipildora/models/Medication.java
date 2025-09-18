package com.hackathon.mipildora.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "medications")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Dosage is required")
    @Size(max = 50, message = "Dosage must not exceed 50 characters")
    @Column(nullable = false, length = 50)
    private String dosage;

    @NotNull(message = "Frequency is required")
    @Min(value = 1, message = "Frequency must be at least 1 time per day")
    @Max(value = 24, message = "Frequency cannot exceed 24 times per day")
    @Column(nullable = false)
    private Integer frequency;

    @NotNull(message = "Time is required")
    @Column(nullable = false)
    private LocalTime time;

    @NotNull(message = "Init date is required")
    @Column(name = "init_date", nullable = false)
    private LocalDate initDate;

    @Builder.Default
    @Column(nullable = false)
    private Boolean taken = false;

    @Builder.Default
    @Column(nullable = false)
    private Boolean alert = true;

    @CreationTimestamp
    @Column(name = "create_date", nullable = false, updatable = false)
    private LocalDateTime createDate;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    @Column(length = 500)
    private String description;

    @Column(name = "final_date")
    private LocalDateTime finalDate;
}
