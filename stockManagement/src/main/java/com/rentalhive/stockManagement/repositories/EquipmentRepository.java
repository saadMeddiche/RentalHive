package com.rentalhive.stockManagement.repositories;

import com.rentalhive.stockManagement.entities.Stock;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rentalhive.stockManagement.entities.Category;
import com.rentalhive.stockManagement.entities.Equipment;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    String QUERY="SELECT COUNT(s) FROM Stock s " +
            "JOIN s.equipment e " +
            "JOIN s.status st " +
            "WHERE e.id = :equipmentId AND st.name = 'disponible'";
    Boolean existsByNameAndCategory(String name, Category category);

    @Query(QUERY)
    Integer CountAvailableStocksForEquipment(@Param("equipmentId") Long equipmentId);
    @Query(QUERY)
    List<Stock> findAvailableStocksForEquipment(@Param("equipmentId") Long equipmentId, Pageable pageable);
}
