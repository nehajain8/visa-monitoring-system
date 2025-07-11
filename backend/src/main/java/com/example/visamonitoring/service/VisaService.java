package com.example.visamonitoring.service;

import com.example.visamonitoring.dto.VisaDTO;
import com.example.visamonitoring.entity.Passenger;
import com.example.visamonitoring.entity.Visa;
import com.example.visamonitoring.entity.VisaId;
import com.example.visamonitoring.repository.PassengerRepository;
import com.example.visamonitoring.repository.VisaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisaService {

    private final VisaRepository visaRepository;
    private final PassengerRepository passengerRepository;

    @Autowired
    public VisaService(VisaRepository visaRepository, PassengerRepository passengerRepository) {
        this.visaRepository = visaRepository;
        this.passengerRepository = passengerRepository;
    }

    public Visa saveVisa(VisaDTO dto) {
        Passenger passenger = passengerRepository.findById(dto.getPassportNumber())
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        Visa visa = new Visa();
        visa.setId(new VisaId(dto.getPassportNumber(), dto.getVisaNumber()));
        visa.setVisaType(dto.getVisaType());
        visa.setExpiryDate(dto.getExpiryDate());
        visa.setPassenger(passenger);

        return visaRepository.save(visa);
    }

    public List<VisaDTO> getAllVisas() {
        return visaRepository.findAll().stream()
                .map(visa -> new VisaDTO(
                        visa.getId().getPassportNumber(),
                        visa.getId().getVisaNumber(),
                        visa.getVisaType(),
                        visa.getExpiryDate()
                ))
                .collect(Collectors.toList());
    }

    public Visa getVisa(String passportNumber, String visaNumber) {
        return visaRepository.findById(new VisaId(passportNumber, visaNumber)).orElse(null);
    }
}
