package com.rentalhive.stockManagement.services.impls;

import com.rentalhive.stockManagement.entities.Equipment;
import com.rentalhive.stockManagement.exceptions.costums.ValidationException;
import com.rentalhive.stockManagement.repositories.EquipmentRepository;
import com.rentalhive.stockManagement.services.EquipmentService;
import com.rentalhive.stockManagement.services.helpers.EquipmentServiceHelper;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
// @AllArgsConstructor
public class EquipmentServiceImp extends EquipmentServiceHelper implements EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    public EquipmentServiceImp(EquipmentRepository equipmentRepository) {
        super(equipmentRepository);
    }

    public List<Equipment> getAllEquipments() {
        return equipmentRepository.findAll();
    }

    public Optional<Equipment> findById(Equipment equipment) {

        checkIfIdOfEquipmentIsNull(equipment);

        return equipmentRepository.findById(equipment.getId());
    }

    public Equipment addEquipment(Equipment equipment) {

        validateEquipmentOnAdding(equipment);

        return equipmentRepository.save(equipment);
    }

    public Equipment updateEquipment(Equipment equipment) {

        validateEquipmentOnUpdating(equipment);

        return equipmentRepository.save(equipment);
    }

    public void deleteEquipment(Equipment equipment) {

        validateEquipmentOnDeleting(equipment);

        equipmentRepository.delete(equipment);

    }
}
