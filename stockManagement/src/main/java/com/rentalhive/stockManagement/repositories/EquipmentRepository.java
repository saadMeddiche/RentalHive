package com.rentalhive.stockManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalhive.stockManagement.entities.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

}
