package com.rentalhive.stockManagement.services;

import com.rentalhive.stockManagement.entities.Equipment;

import java.util.List;
import java.util.Optional;

public interface EquipmentService {

    public List<Equipment> getAllEquipments();

    public Equipment addEquipment(Equipment equipment);

    public Equipment updateEquipment(Equipment equipment);

    public void deleteEquipment(Equipment equipment);

    public Optional<Equipment> findById(long id);
}

