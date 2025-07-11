package com.example.visamonitoring.repository;

import com.example.visamonitoring.entity.Visa;
import com.example.visamonitoring.entity.VisaId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisaRepository extends JpaRepository<Visa, VisaId> {
    List<Visa> findByExpiryDate(String expiryDate);
}
