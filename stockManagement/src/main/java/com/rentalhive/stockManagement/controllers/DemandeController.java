package com.rentalhive.stockManagement.controllers;

import com.rentalhive.stockManagement.DTO.AddDemandeDto;
import com.rentalhive.stockManagement.DTO.DemandeWithOutIdDto;
import com.rentalhive.stockManagement.DTO.UpdateDemandeDto;
import com.rentalhive.stockManagement.embeddables.DemandeStockQuantityRequest;
import com.rentalhive.stockManagement.embeddables.StockQuantity;
import com.rentalhive.stockManagement.entities.Demande;
import com.rentalhive.stockManagement.helpers.ControllerHelper;
import com.rentalhive.stockManagement.services.DemandeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/RentalHive/Api/Demande")
@AllArgsConstructor
public class DemandeController extends ControllerHelper {

    final DemandeService demandeService;
    final ModelMapper modelMapper;

    @GetMapping("/demandes")
    public ResponseEntity<?> getAllDemandes() {

        try {

            List<Demande> demandes = demandeService.getAllDemandes();

            return new ResponseEntity<>(demandes, HttpStatus.OK);

        } catch (Exception e) {
            return getResponseEntityDependingOnException(e);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addDemande(@RequestBody()DemandeStockQuantityRequest request) {

        try {
            Demande demande = modelMapper.map(request.getDemandeDto(), Demande.class);
            Demande addedDemande = demandeService.addDemande(demande,request.getStockQuantities());
            AddDemandeDto addDemandeDto= modelMapper.map(addedDemande, AddDemandeDto.class);
            return new ResponseEntity<>(addDemandeDto, HttpStatus.OK);

        } catch (Exception e) {
            return getResponseEntityDependingOnException(e);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDemande(@PathVariable("id") long id, @RequestBody UpdateDemandeDto demandeDto, @RequestBody List<StockQuantity> stockQuantities) {

        try {

            Demande demande = modelMapper.map(demandeDto, Demande.class);
            Demande addedDemande = demandeService.updateDemand(demande,stockQuantities);
            DemandeWithOutIdDto updatedDemandeDto= modelMapper.map(addedDemande, DemandeWithOutIdDto.class);
            return new ResponseEntity<>(updatedDemandeDto, HttpStatus.OK);

        } catch (Exception e) {
            return getResponseEntityDependingOnException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDemande(@PathVariable("id") long id, @RequestBody Demande demande) {

        try {

            demandeService.deleteDemand(demande);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return getResponseEntityDependingOnException(e);
        }
    }

}
