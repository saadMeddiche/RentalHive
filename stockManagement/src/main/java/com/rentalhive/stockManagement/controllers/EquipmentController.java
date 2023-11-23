package com.rentalhive.stockManagement.controllers;

import java.util.List;

import com.rentalhive.stockManagement.dto.equipmentDtos.EquipmentUpdateDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentalhive.stockManagement.entities.Equipment;
import com.rentalhive.stockManagement.helpers.ControllerHelper;
import com.rentalhive.stockManagement.services.EquipmentService;
import com.rentalhive.stockManagement.converters.EquipmentConverter;

import com.rentalhive.stockManagement.dto.equipmentDtos.EquipmentAddDto;

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

            return new ResponseEntity<>(equipments, HttpStatus.OK);

        } catch (Exception e) {
            return getResponseEntityDependingOnException(e);
        }

    }

    @PostMapping("/equipments")
    public ResponseEntity<?> addEquipment(@ModelAttribute EquipmentAddDto equipmentAddDto) {

        try {

            Equipment equipment = equipmentConverter.convertToEntity(equipmentAddDto);

            Equipment addedEquipment = equipmentService.addEquipment(equipment);

            return new ResponseEntity<>(addedEquipment, HttpStatus.OK);

        } catch (Exception e) {

            return getResponseEntityDependingOnException(e);
        }
    }

    @PutMapping("/equipments/{id}")
    public ResponseEntity<?> updateEquipment(@PathVariable("id") long id, @ModelAttribute EquipmentUpdateDto equipmentUpdateDto) {

        try {

            Equipment equipment = equipmentConverter.convertToEntity(equipmentUpdateDto , id);

            Equipment addedEquipment = equipmentService.updateEquipment(equipment);

            return new ResponseEntity<>(addedEquipment, HttpStatus.OK);

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
