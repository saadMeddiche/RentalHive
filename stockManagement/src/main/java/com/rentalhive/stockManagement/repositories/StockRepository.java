package com.rentalhive.stockManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalhive.stockManagement.entities.Stock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByRegistrationNumber(String registrationNumber);
}
