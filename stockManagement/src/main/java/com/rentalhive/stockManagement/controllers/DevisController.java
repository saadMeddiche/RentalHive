package com.rentalhive.stockManagement.controllers;

import com.rentalhive.stockManagement.converters.DevisConverter;
import com.rentalhive.stockManagement.converters.EquipmentConverter;
import com.rentalhive.stockManagement.dto.devisDtos.request.DevisRequestAddDto;
import com.rentalhive.stockManagement.dto.devisDtos.response.DevisResponseDto;
import com.rentalhive.stockManagement.dto.equipmentDtos.request.EquipmentRequestAddDto;
import com.rentalhive.stockManagement.dto.equipmentDtos.response.EquipmentResponseDto;
import com.rentalhive.stockManagement.entities.Devis;
import com.rentalhive.stockManagement.entities.Equipment;
import com.rentalhive.stockManagement.helpers.ControllerHelper;
import com.rentalhive.stockManagement.services.DevisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DevisController extends ControllerHelper {

    private final DevisService devisService;

    private final DevisConverter devisConverter;

    public DevisController(DevisService devisService, DevisConverter devisConverter) {
        this.devisService = devisService;
        this.devisConverter = devisConverter;
    }


    @PostMapping("/devies")
    public ResponseEntity<?> addDevis(@RequestBody DevisRequestAddDto devisRequestAddDto) {

        try {

            Devis devis = devisConverter.convertToEntity(devisRequestAddDto);

            Devis addedDevis = devisService.addDevis(devis);

            DevisResponseDto devisResponseDto = DevisConverter.convertToDto(addedDevis);

            return new ResponseEntity<>(devisResponseDto, HttpStatus.OK);

        } catch (Exception e) {
            return getResponseEntityDependingOnException(e);
        }
    }
}
