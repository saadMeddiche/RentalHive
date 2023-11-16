package com.rentalhive.stockManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentalhive.stockManagement.entities.Category;
import com.rentalhive.stockManagement.entities.Equipment;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    Boolean existsByNameAndCategory(String name, Category category);
}
