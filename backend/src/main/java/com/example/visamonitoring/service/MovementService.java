package com.example.visamonitoring.service;

import com.example.visamonitoring.dto.MovementDTO;
import com.example.visamonitoring.entity.Movement;
import com.example.visamonitoring.entity.Passenger;
import com.example.visamonitoring.entity.Visa;
import com.example.visamonitoring.entity.VisaId;
import com.example.visamonitoring.repository.MovementRepository;
import com.example.visamonitoring.repository.PassengerRepository;
import com.example.visamonitoring.repository.VisaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovementService {

    private final MovementRepository movementRepository;
    private final PassengerRepository passengerRepository;
    private final VisaRepository visaRepository;

    @Autowired
    public MovementService(MovementRepository movementRepository,
                           PassengerRepository passengerRepository,
                           VisaRepository visaRepository) {
        this.movementRepository = movementRepository;
        this.passengerRepository = passengerRepository;
        this.visaRepository = visaRepository;
    }

    public Movement saveMovement(MovementDTO dto) {
        Passenger passenger = passengerRepository.findById(dto.getPassportNumber())
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        Visa visa = visaRepository.findById(new VisaId(dto.getPassportNumber(), dto.getVisaNumber()))
                .orElseThrow(() -> new RuntimeException("Visa not found"));

        Movement movement = new Movement();
        movement.setPassportNumber(dto.getPassportNumber());
        movement.setVisaNumber(dto.getVisaNumber());
        movement.setMovementType(dto.getMovementType());
        movement.setMovementDate(dto.getMovementDate());
        movement.setPassenger(passenger);
        movement.setVisa(visa);

        return movementRepository.save(movement);
    }

    public List<MovementDTO> getAllMovements() {
        return movementRepository.findAll().stream()
                .map(m -> new MovementDTO(
                        m.getPassportNumber(),
                        m.getVisaNumber(),
                        m.getMovementType(),
                        m.getMovementDate()
                ))
                .collect(Collectors.toList());
    }

    public List<Movement> getMovementsByPassport(String passportNumber) {
        return movementRepository.findByPassportNumber(passportNumber);
    }

    public List<Movement> getMovementsByVisa(String passportNumber, String visaNumber) {
        return movementRepository.findByPassportNumberAndVisaNumber(passportNumber, visaNumber);
    }
}
