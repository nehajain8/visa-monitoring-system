package com.example.visamonitoring.controller;

import com.example.visamonitoring.dto.MovementDto;
import com.example.visamonitoring.dto.PassengerDto;
import com.example.visamonitoring.dto.VisaDto;
import com.example.visamonitoring.service.MovementService;
import com.example.visamonitoring.service.PassengerService;
import com.example.visamonitoring.service.VisaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final PassengerService passengerService;
    private final VisaService visaService;
    private final MovementService movementService;

    @PostMapping("/passengers")
    public ResponseEntity<String> addPassenger(@RequestBody PassengerDto passengerDto) {
        passengerService.addPassenger(passengerDto);
        return ResponseEntity.ok("Passenger added successfully");
    }

    @PostMapping("/visas")
    public ResponseEntity<String> addVisa(@RequestBody VisaDto visaDto) {
        visaService.addVisa(visaDto);
        return ResponseEntity.ok("Visa added successfully");
    }

    @PostMapping("/movements")
    public ResponseEntity<String> recordMovement(@RequestBody MovementDto movementDto) {
        movementService.recordMovement(movementDto);
        return ResponseEntity.ok("Movement recorded successfully");
    }
}
