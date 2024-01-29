package com.rentalhive.stockManagement.repositories;

import com.rentalhive.stockManagement.entities.Equipment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalhive.stockManagement.entities.Stock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {


   Optional<Stock> findByRegistrationNumber(String registrationNumber);

    List<Stock> findByEquipmentAndStatusName(Equipment equipment, String statusName,Pageable pageable);
    List<Stock> findByEquipmentAndStatusName(Equipment equipment, String statusName);
    Integer  countByEquipmentAndStatusName(Equipment equipment, String statusName);
    @Query("SELECT s FROM Stock s WHERE s.equipment.id = :equipmentId " +
            "AND NOT EXISTS (" +
            "    SELECT 1 FROM StockDates sd " +
            "    WHERE sd.stock.id = s.id " +
            "      AND (" +
            "          :dateReservation BETWEEN sd.date_reservation AND sd.date_expiration " +
            "          OR :dateExpiration BETWEEN sd.date_reservation AND sd.date_expiration " +
            "          OR sd.date_reservation BETWEEN :dateReservation AND :dateExpiration" +
            "      )" +
            ")" )
    public List<Stock> getAvailableStocks(Long equipmentId , LocalDateTime dateReservation, LocalDateTime dateExpiration);

}
