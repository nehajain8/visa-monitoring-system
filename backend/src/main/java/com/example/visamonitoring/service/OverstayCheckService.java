package com.example.visamonitoring.service;

import com.example.visamonitoring.entity.Movement;
import com.example.visamonitoring.entity.Overstayer;
import com.example.visamonitoring.entity.Visa;
import com.example.visamonitoring.entity.VisaId;
import com.example.visamonitoring.repository.MovementRepository;
import com.example.visamonitoring.repository.OverstayerRepository;
import com.example.visamonitoring.repository.VisaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OverstayCheckService {

    private final VisaRepository visaRepository;
    private final MovementRepository movementRepository;
    private final OverstayerRepository overstayerRepository;

    @Autowired
    public OverstayCheckService(VisaRepository visaRepository,
                                MovementRepository movementRepository,
                                OverstayerRepository overstayerRepository) {
        this.visaRepository = visaRepository;
        this.movementRepository = movementRepository;
        this.overstayerRepository = overstayerRepository;
    }

    public List<Overstayer> checkAndStoreOverstayers() {
        List<Visa> allVisas = visaRepository.findAll();

        List<Overstayer> overstayers = allVisas.stream()
            .filter(visa -> {
                VisaId id = new VisaId(visa.getPassportNumber(), visa.getVisaNumber());

                // Only consider expired visas
                if (visa.getExpiryDate().isAfter(LocalDate.now())) return false;

                List<Movement> movements = movementRepository
                        .findByPassportNumberAndVisaNumber(id.getPassportNumber(), id.getVisaNumber());

                // Has no exit record after entry → assume overstaying
                boolean hasExited = movements.stream()
                        .anyMatch(m -> m.getMovementType().equalsIgnoreCase("EXIT"));

                return !hasExited;
            })
            .map(visa -> {
                Overstayer o = new Overstayer();
                o.setPassportNumber(visa.getPassportNumber());
                o.setVisaNumber(visa.getVisaNumber());
                o.setOverstayDetectedDate(LocalDate.now());
                return o;
            })
            .collect(Collectors.toList());

        overstayerRepository.saveAll(overstayers);
        return overstayers;
    }

    public List<Overstayer> getAllOverstayers() {
        return overstayerRepository.findAll();
    }

    public Optional<Overstayer> getByVisa(String passportNumber, String visaNumber) {
        return overstayerRepository.findByPassportNumberAndVisaNumber(passportNumber, visaNumber);
    }
}
