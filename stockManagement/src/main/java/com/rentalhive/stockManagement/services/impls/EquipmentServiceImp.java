package com.rentalhive.stockManagement.services.impls;

import com.rentalhive.stockManagement.entities.Equipment;
import com.rentalhive.stockManagement.repositories.CategoryRepository;
import com.rentalhive.stockManagement.repositories.EquipmentRepository;
import com.rentalhive.stockManagement.repositories.UserRepository;
import com.rentalhive.stockManagement.services.EquipmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EquipmentServiceImp implements EquipmentService {
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private UserServiceImp userService;
    @Autowired
    private CategoryServiceImp categoryService;
    public List<Equipment> getAllEquipments() {
        return null;
    }
    public Optional<Equipment> findById(Long id) {
        return Optional.empty();
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
