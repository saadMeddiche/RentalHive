package com.rentalhive.stockManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalhive.stockManagement.entities.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {

}
