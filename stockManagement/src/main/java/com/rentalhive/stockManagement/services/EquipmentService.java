package com.rentalhive.stockManagement.services;

public interface EquipmentService {

    public void saveEquipment(Equipment equipment);
    public void updateEquipment(Equipment equipment);
    public void deleteEquipment(Long id);
}