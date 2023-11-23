package com.rentalhive.stockManagement.converters;

import com.rentalhive.stockManagement.entities.Equipment;

import com.rentalhive.stockManagement.entities.User;
import com.rentalhive.stockManagement.services.CategoryService;
import com.rentalhive.stockManagement.services.UserService;

import com.rentalhive.stockManagement.services.EquipmentService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.rentalhive.stockManagement.dto.equipmentDtos.EquipmentAddDto;
import com.rentalhive.stockManagement.dto.equipmentDtos.EquipmentUpdateDto;

import com.rentalhive.stockManagement.services.impls.UserServiceImp;

import com.rentalhive.stockManagement.entities.Category;
import org.springframework.stereotype.Component;

@Component
public class EquipmentConverter {



    public final UserService userservice;

    public final CategoryService categoryService;

    public final EquipmentService equipmentService;

    public EquipmentConverter(UserService userService, CategoryService categoryService, EquipmentService equipmentService) {
        this.userservice = userService;
        this.categoryService = categoryService;
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
        equipment.setName(equipmentUpdateDto.getName());
        equipment.setPrice_per_day(equipmentUpdateDto.getPrice_per_day());
        equipment.setCategory(category);

        return equipment;

    }
}
