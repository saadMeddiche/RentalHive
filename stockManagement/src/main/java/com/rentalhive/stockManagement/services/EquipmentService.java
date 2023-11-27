package com.rentalhive.stockManagement.services;

import com.rentalhive.stockManagement.entities.Demande;
import com.rentalhive.stockManagement.entities.Equipment;
import com.rentalhive.stockManagement.entities.Stock;

import java.util.List;
import java.util.Optional;

public interface EquipmentService {


    public Equipment findById(Long id);

    public List<Equipment> getAllEquipments();

    public Equipment addEquipment(Equipment equipment);

    public Equipment updateEquipment(Equipment equipment);

    public void deleteEquipment(Equipment equipment);
    public boolean isExist(Equipment equipment);

    public Optional<Equipment> findEquipmentById(Long id);

}
