package com.rentalhive.stockManagement.controllers;

import java.util.List;
import java.util.stream.Collectors;


import com.rentalhive.stockManagement.dto.equipmentDtos.request.EquipmentRequestUpdateDto;
import com.rentalhive.stockManagement.dto.equipmentDtos.response.EquipmentResponseDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rentalhive.stockManagement.entities.Equipment;
import com.rentalhive.stockManagement.helpers.ControllerHelper;
import com.rentalhive.stockManagement.services.EquipmentService;
import com.rentalhive.stockManagement.converters.EquipmentConverter;

import com.rentalhive.stockManagement.dto.equipmentDtos.request.EquipmentRequestAddDto;

@RestController
@RequestMapping("/api")
public class EquipmentController extends ControllerHelper {

    private final EquipmentService equipmentService;
    private final EquipmentConverter equipmentConverter;

    public EquipmentController(EquipmentService equipmentService , EquipmentConverter equipmentConverter){
        this.equipmentService = equipmentService;
        this.equipmentConverter = equipmentConverter;
    }

    @GetMapping("/equipments")
    public ResponseEntity<?> getAllEquipments() {

        try {
            List<Equipment> equipments = equipmentService.getAllEquipments();

            List<EquipmentResponseDto> listOfEquipmentResponseDtos = equipments.stream().map(EquipmentConverter::convertToDto).collect(Collectors.toList());

            return new ResponseEntity<>(listOfEquipmentResponseDtos, HttpStatus.OK);

        } catch (Exception e) {
            return getResponseEntityDependingOnException(e);
        }

    }

    @PostMapping("/equipments")
    public ResponseEntity<?> addEquipment(@RequestBody EquipmentRequestAddDto equipmentAddDto) {

        try {

            Equipment equipment = equipmentConverter.convertToEntity(equipmentAddDto);

            Equipment addedEquipment = equipmentService.addEquipment(equipment);

            EquipmentResponseDto equipmentResponseDto = EquipmentConverter.convertToDto(addedEquipment);

            return new ResponseEntity<>(equipmentResponseDto, HttpStatus.OK);

        } catch (Exception e) {
            return getResponseEntityDependingOnException(e);
        }
    }

    @PutMapping("/equipments/{id}")
    public ResponseEntity<?> updateEquipment(@PathVariable("id") long id, @RequestBody EquipmentRequestUpdateDto equipmentUpdateDto) {

        try {

            Equipment equipment = equipmentConverter.convertToEntity(equipmentUpdateDto , id);

            Equipment updatedEquipment = equipmentService.updateEquipment(equipment);

            EquipmentResponseDto equipmentResponseDto = EquipmentConverter.convertToDto(updatedEquipment);

            return new ResponseEntity<>(equipmentResponseDto, HttpStatus.OK);

        } catch (Exception e) {
            return getResponseEntityDependingOnException(e);
        }
    }

    @DeleteMapping("/equipments/{id}")
    public ResponseEntity<?> deleteEquipment(@PathVariable("id") long id) {

        try {

            Equipment equipment = equipmentService.findById(id);

            equipmentService.deleteEquipment(equipment);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return getResponseEntityDependingOnException(e);
        }
    }

}
