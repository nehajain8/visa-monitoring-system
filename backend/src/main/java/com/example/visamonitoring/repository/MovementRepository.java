package com.example.visamonitoring.repository;

import com.example.visamonitoring.entity.Movement;
import com.example.visamonitoring.entity.Passenger;
import com.example.visamonitoring.entity.VisaId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovementRepository extends JpaRepository<Movement, Long> {
    
    List<Movement> findByPassengerAndVisaIdOrderByTimestampDesc(Passenger passenger, VisaId visaId);

    Movement findTopByPassengerAndVisaIdOrderByTimestampDesc(Passenger passenger, VisaId visaId);
}
