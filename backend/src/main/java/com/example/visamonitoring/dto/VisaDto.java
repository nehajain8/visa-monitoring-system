package com.example.visamonitoring.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VisaDto {
    private String passportNumber;
    private String visaType;
    private LocalDate issueDate;
    private LocalDate expiryDate;
}
