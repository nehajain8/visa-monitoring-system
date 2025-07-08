package com.example.visamonitoring.entity;

import java.io.Serializable;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class VisaId implements Serializable {
    private String passportNo;
    private String visaId;
}
