package com.example.visamonitoring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OverstayerDto {
    private String passportNumber;
    private String fullName;
    private String nationality;
    private String visaType;
    private String expiryDate;
}
