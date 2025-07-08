package com.example.visamonitoring.service;

import com.example.visamonitoring.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class OverstayerService {
    private final MovementRepository movementRepo;
    private final VisaRepository visaRepo;

    public OverstayerService(MovementRepository movementRepo, VisaRepository visaRepo) {
        this.movementRepo = movementRepo;
        this.visaRepo = visaRepo;
    }

    // TODO: Implement logic
}
