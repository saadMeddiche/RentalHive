package com.rentalhive.stockManagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentalhive.stockManagement.entities.Equipment;
import com.rentalhive.stockManagement.helpers.ControllerHelper;
import com.rentalhive.stockManagement.services.EquipmentService;

@CrossOrigin(origins = "http://localhost:3306")
@RestController
@RequestMapping("/api")
public class EquipmentController extends ControllerHelper {

    @Autowired
    private EquipmentService equipmentService;

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
    public ResponseEntity<?> addEquipment(@RequestBody Equipment equipment) {

        try {

            Equipment addedEquipment = equipmentService.addEquipment(equipment);

            return new ResponseEntity<>(addedEquipment, HttpStatus.OK);

        } catch (Exception e) {
            return getResponseEntityDependingOnException(e);
        }
    }

    @PutMapping("/equipments/{id}")
    public ResponseEntity<?> updateEquipment(@PathVariable("id") long id, @RequestBody Equipment equipment) {

        try {

            Equipment addedEquipment = equipmentService.updateEquipment(equipment);

            return new ResponseEntity<>(addedEquipment, HttpStatus.OK);

        } catch (Exception e) {
            return getResponseEntityDependingOnException(e);
        }
    }

    @DeleteMapping("/equipments/{id}")
    public ResponseEntity<?> deleteEquipment(@PathVariable("id") long id, @RequestBody Equipment equipment) {

        try {

            equipmentService.deleteEquipment(equipment);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return getResponseEntityDependingOnException(e);
        }
    }

}
