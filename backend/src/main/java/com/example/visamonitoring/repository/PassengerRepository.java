package com.example.visamonitoring.repository;

import com.example.visamonitoring.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, String> {}
