package com.example.visamonitoring.controller;

import com.example.visamonitoring.dto.MovementRequest;
import com.example.visamonitoring.dto.PassengerRequest;
import com.example.visamonitoring.dto.VisaRequest;
import com.example.visamonitoring.entity.Passenger;
import com.example.visamonitoring.entity.Visa;
import com.example.visamonitoring.entity.Movement;
import com.example.visamonitoring.service.PassengerService;
import com.example.visamonitoring.service.VisaService;
import com.example.visamonitoring.service.MovementService;
import com.example.visamonitoring.service.OverstayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class VisaController {

    private final PassengerService passengerService;
    private final VisaService visaService;
    private final MovementService movementService;
    private final OverstayService overstayService;

    // ------------------- Passenger ------------------------

    @PostMapping("/passengers")
    public ResponseEntity<Passenger> addPassenger(@RequestBody PassengerRequest request) {
        return ResponseEntity.ok(passengerService.addPassenger(request));
    }

    // ------------------- Visa ------------------------

    @PostMapping("/visas")
    public ResponseEntity<Visa> addVisa(@RequestBody VisaRequest request) {
        return ResponseEntity.ok(visaService.addVisa(request));
    }

    // ------------------- Movement ------------------------

    @PostMapping("/movements")
    public ResponseEntity<Movement> addMovement(@RequestBody MovementRequest request) {
        return ResponseEntity.ok(movementService.addMovement(request));
    }

    // ------------------- Overstayers ------------------------

    @GetMapping("/overstayers")
    public ResponseEntity<List<String>> getOverstayers() {
        return ResponseEntity.ok(overstayService.findOverstayers());
    }
}
