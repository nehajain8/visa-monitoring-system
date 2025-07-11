package com.example.visamonitoring.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PassengerDTO {
    private String passportNumber;
    private String name;
    private String nationality;
    private LocalDate visaExpiryDate;
    private LocalDate entryDate;
    private LocalDate exitDate;
}