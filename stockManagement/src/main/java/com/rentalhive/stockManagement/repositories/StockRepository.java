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
/*    @Query("SELECT DISTINCT s FROM Stock s LEFT JOIN FETCH s.demandes d " +
            "WHERE s.equipment = :equipment " +
            "AND (d IS EMPTY OR (d.date_reservation > :endDate AND d.date_expiration < :givenDate) )")*/
/*    List<Stock> findStocksWithSpecificEquipmentAndDemandConditions(
             Equipment equipment,
             LocalDateTime endDate,
             LocalDateTime givenDate,
             Pageable pageable
    );*/
/*    @Query("SELECT DISTINCT count(s) FROM Stock s LEFT JOIN FETCH s.demandes d " +
            "WHERE s.equipment = :equipment " +
            "AND (d IS EMPTY OR (d.date_reservation > :endDate AND d.date_expiration < :givenDate))")*/
/*    Integer countStocksWithSpecificEquipmentAndDemandConditions(
             Equipment equipment,
             LocalDateTime endDate,
             LocalDateTime givenDate
    );*/

}
