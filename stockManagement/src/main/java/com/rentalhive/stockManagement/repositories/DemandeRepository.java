package com.rentalhive.stockManagement.repositories;

import com.rentalhive.stockManagement.entities.Demande;
import com.rentalhive.stockManagement.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalhive.stockManagement.entities.Equipment;

import java.util.Set;

public interface DemandeRepository extends JpaRepository<Demande, Long> {
    Set<Demande> findByStocks(Stock stock);
}
