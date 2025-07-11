package com.example.visamonitoring.service;

import com.example.visamonitoring.dto.PassengerDTO;
import com.example.visamonitoring.entity.Passenger;
import com.example.visamonitoring.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassengerService {

    private final PassengerRepository passengerRepository;

    @Autowired
    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public Passenger savePassenger(PassengerDTO dto) {
        Passenger passenger = new Passenger();
        passenger.setPassportNumber(dto.getPassportNumber());
        passenger.setFullName(dto.getFullName());
        return passengerRepository.save(passenger);
    }

    public List<PassengerDTO> getAllPassengers() {
        return passengerRepository.findAll().stream()
                .map(p -> new PassengerDTO(p.getPassportNumber(), p.getFullName()))
                .collect(Collectors.toList());
    }

    public Passenger getPassengerByPassport(String passportNumber) {
        return passengerRepository.findById(passportNumber).orElse(null);
    }
}
