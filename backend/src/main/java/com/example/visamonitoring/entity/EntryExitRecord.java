package com.example.visamonitoring.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "entry_exit_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntryExitRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String passportNumber;

    private LocalDate visaExpiryDate;

    private LocalDate entryDate;

    private LocalDate exitDate;

    private boolean isOverstayed;
}
