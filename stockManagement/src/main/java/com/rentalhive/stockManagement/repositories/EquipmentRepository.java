package com.rentalhive.stockManagement.repositories;

import com.rentalhive.stockManagement.entities.Stock;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rentalhive.stockManagement.entities.Category;
import com.rentalhive.stockManagement.entities.Equipment;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    Boolean existsByNameAndCategory(String name, Category category);

    @Query("SELECT s FROM Stock s " +
            "LEFT JOIN FETCH s.demandes d " +
            "WHERE s.equipment = :equipment " +
            "AND (d IS EMPTY OR " +
            "(d.date_reservation > :endDate AND d.date_expiration < :givenDate))")
    List<Stock> findStocksWithSpecificEquipmentAndDemandConditions(
            @Param("equipment") Equipment equipment,
            @Param("endDate") LocalDateTime endDate,
            @Param("givenDate") LocalDateTime givenDate, Pageable pageable
    );
    @Query("SELECT count(s) FROM Stock s " +
            "LEFT JOIN FETCH s.demandes d " +
            "WHERE s.equipment = :equipment " +
            "AND (d IS EMPTY OR " +
            "(d.date_reservation > :endDate AND d.date_expiration < :givenDate))")
    Integer CountStocksWithSpecificEquipmentAndDemandConditions(
            @Param("equipment") Equipment equipment,
            @Param("endDate") LocalDateTime endDate,
            @Param("givenDate") LocalDateTime givenDate
    );


}
