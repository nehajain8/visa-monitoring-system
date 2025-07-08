package com.example.visamonitoring.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Passenger {
    @Id
    private String passportNo;
    private String name;
    private String nationality;
    private String dob;
}
