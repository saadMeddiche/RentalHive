package com.rentalhive.stockManagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentalhive.stockManagement.entities.Equipment;
import com.rentalhive.stockManagement.repositories.EquipmentRepository;

@CrossOrigin(origins = "http://localhost:3306")
@RestController
@RequestMapping("/api")
public class EquipmentController {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @GetMapping("/equipments")
    public ResponseEntity<List<Equipment>> getAllEquipments() {

        List<Equipment> equipments = equipmentRepository.findAll();

        return (!equipments.isEmpty()) 
        ?
        new ResponseEntity<>(equipments, HttpStatus.OK)
        : 
        new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
