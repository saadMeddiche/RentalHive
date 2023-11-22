package com.rentalhive.stockManagement.converter;

import com.rentalhive.stockManagement.entities.Equipment;

import com.rentalhive.stockManagement.entities.User;
import com.rentalhive.stockManagement.services.CategoryService;
import com.rentalhive.stockManagement.services.UserService;

import com.rentalhive.stockManagement.services.EquipmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.rentalhive.stockManagement.dto.equipmentDtos.EquipmentAddDto;
import com.rentalhive.stockManagement.dto.equipmentDtos.EquipmentUpdateDto;

import com.rentalhive.stockManagement.entities.Category;
import org.springframework.stereotype.Component;

@Component
public class EquipmentConverter {

    UserService userservice;
    CategoryService categoryService;
    EquipmentService equipmentService;

    @Autowired
    public void setUserService(@Qualifier("userServiceImp") UserService userservice) {
        this.userservice = userservice;
    }

    @Autowired
    public void setCategoryService(@Qualifier("categoryServiceImp") CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setEquipmentService(@Qualifier("equipmentService") EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    public Equipment convertToEntity(EquipmentAddDto equipmentAddDto) {

        User user = userservice.findById(equipmentAddDto.getAdded_by_id());
        Category category = categoryService.findById(equipmentAddDto.getCategory_Id());

        // Set the data to equipment
        Equipment equipment = new Equipment();
        equipment.setName(equipmentAddDto.getName());
        equipment.setPrice_per_day(equipmentAddDto.getPrice_per_day());
        equipment.setAdded_by(user);
        equipment.setCategory(category);

        return equipment;

    }

    public Equipment convertToEntity(EquipmentUpdateDto equipmentUpdateDto, Long EquipmentId) {

        Category category = categoryService.findById(equipmentUpdateDto.getCategory_Id());

        // Set the data to equipment
        Equipment equipment = equipmentService.findById(EquipmentId);
        equipment.setId(EquipmentId);
        equipment.setName(equipmentUpdateDto.getName());
        equipment.setPrice_per_day(equipmentUpdateDto.getPrice_per_day());
        equipment.setCategory(category);

        return equipment;

    }
}
