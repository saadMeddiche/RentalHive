package com.rentalhive.stockManagement.services.impls;

import com.rentalhive.stockManagement.entities.Equipment;
import com.rentalhive.stockManagement.repositories.EquipmentRepository;
import com.rentalhive.stockManagement.services.EquipmentService;
import com.rentalhive.stockManagement.services.helpers.EquipmentServiceHelper;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImp extends EquipmentServiceHelper implements EquipmentService {

    private EquipmentRepository equipmentRepository;

    public List<Equipment> getAllEquipments() {

        List<Equipment> equipments = equipmentRepository.findAll();

        validationAfterGettingAllEquipments(equipments);

        return equipments;
    }

    public Optional<Equipment> findById(Equipment equipment) {

        throwExceptionIfIdOfEquipmentIsNull(equipment);

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
