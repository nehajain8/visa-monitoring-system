package com.example.visamonitoring.repository;

import com.example.visamonitoring.entity.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementRepository extends JpaRepository<Movement, Long> {}
