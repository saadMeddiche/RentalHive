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

    public List<Equipment> getAllEquipments() {
        return equipmentRepository.findAll();
    }

    public Optional<Equipment> findById(Long id) {

        checkIfIdIsNull(id);

        return equipmentRepository.findById(id);
    }

    public Equipment addEquipment(Equipment equipment) {
        return null;
    }

    public Equipment updateEquipment(Equipment equipment) {
        return null;
    }

    public void deleteEquipment(Equipment equipment) {

    }
}
