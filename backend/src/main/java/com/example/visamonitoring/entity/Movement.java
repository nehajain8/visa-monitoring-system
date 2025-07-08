package com.example.visamonitoring.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Movement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movementId;

    private String passportNo;
    private String visaId;

    @Enumerated(EnumType.STRING)
    private MovementType movementType; // ENTRY or EXIT

    private LocalDateTime movementTs;
}
