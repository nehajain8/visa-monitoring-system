package com.example.visamonitoring.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@IdClass(VisaId.class)
public class Visa {
    @Id
    private String passportNo;
    @Id
    private String visaId;

    private String visaType;
    private LocalDate expiryDate;
}
