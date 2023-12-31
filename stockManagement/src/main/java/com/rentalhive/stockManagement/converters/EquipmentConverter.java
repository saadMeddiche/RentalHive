package com.rentalhive.stockManagement.converters;

import com.rentalhive.stockManagement.dto.equipmentDtos.response.EquipmentResponseDto;
import com.rentalhive.stockManagement.entities.Equipment;

import com.rentalhive.stockManagement.entities.User;
import com.rentalhive.stockManagement.services.CategoryService;
import com.rentalhive.stockManagement.services.UserService;

import com.rentalhive.stockManagement.services.EquipmentService;

import com.rentalhive.stockManagement.dto.equipmentDtos.request.EquipmentRequestAddDto;
import com.rentalhive.stockManagement.dto.equipmentDtos.request.EquipmentRequestUpdateDto;

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


    public Equipment convertToEntity(EquipmentRequestAddDto equipmentAddDto) {

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

    public Equipment convertToEntity(EquipmentRequestUpdateDto equipmentUpdateDto, Long EquipmentId) {

        Category category = categoryService.findById(equipmentUpdateDto.getCategory_Id());

        // Set the data to equipment
        Equipment equipment = equipmentService.findById(EquipmentId);
        equipment.setName(equipmentUpdateDto.getName());
        equipment.setPrice_per_day(equipmentUpdateDto.getPrice_per_day());
        equipment.setCategory(category);

        return equipment;

    }

    public static EquipmentResponseDto convertToDto(Equipment equipment){

        EquipmentResponseDto equipmentResponseDto = new EquipmentResponseDto();

        equipmentResponseDto.setId(equipment.getId());

        equipmentResponseDto.setName(equipment.getName());

        equipmentResponseDto.setPrice_per_day(equipment.getPrice_per_day());

        equipmentResponseDto.setCategory(CategoryConverter.convertToDto(equipment.getCategory()));

        return equipmentResponseDto;

    }

}
