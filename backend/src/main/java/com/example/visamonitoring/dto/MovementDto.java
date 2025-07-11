package com.example.visamonitoring.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovementDto {
    private String passportNumber;
    private String movementType; // ENTRY or EXIT
    private LocalDateTime movementTime;
}
