package com.rentalhive.stockManagement.controllers;

import com.rentalhive.stockManagement.DTO.DemandeDto.AddDemandeDto;
import com.rentalhive.stockManagement.DTO.DemandeDto.DemandeWithOutIdDto;
import com.rentalhive.stockManagement.embeddables.DemandeStockQuantityRequest;
import com.rentalhive.stockManagement.entities.Demande;
import com.rentalhive.stockManagement.entities.User;
import com.rentalhive.stockManagement.helpers.ControllerHelper;
import com.rentalhive.stockManagement.services.DemandeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @PostMapping("/demandes")
    public ResponseEntity<?> addDemande(@RequestBody() DemandeStockQuantityRequest request) {

        try {
            Demande demande = modelMapper.map(request.getDemandeDto(), Demande.class);
            User user=new User();
            user.setId(1L);
            demande.setRenter(user);
            demande.setDate_demande(LocalDateTime.now());
            Demande addedDemande = demandeService.addDemande(demande,request.getStockQuantities());
            AddDemandeDto addDemandeDto= modelMapper.map(addedDemande, AddDemandeDto.class);
            return new ResponseEntity<>(addDemandeDto, HttpStatus.OK);

        } catch (Exception e) {
            return getResponseEntityDependingOnException(e);
        }
    }

    @PutMapping("/demandes/{id}")
    public ResponseEntity<?> updateDemande(@PathVariable("id") long id, @RequestBody() DemandeStockQuantityRequest request) {

        try {
            Demande demande = modelMapper.map(request.getDemandeDto(), Demande.class);
            demande.setId(id);
            User user=new User();
            user.setId(1L);
            demande.setVerified_by(user);
            demande.setDate_verification(LocalDateTime.now());
            Demande addedDemande = demandeService.updateDemand(demande,request.getStockQuantities());
            DemandeWithOutIdDto updatedDemandeDto= modelMapper.map(addedDemande, DemandeWithOutIdDto.class);
            return new ResponseEntity<>(updatedDemandeDto, HttpStatus.OK);

        } catch (Exception e) {
            return getResponseEntityDependingOnException(e);
        }
    }

    @DeleteMapping("/demandes/{id}")
    public ResponseEntity<?> deleteDemande(@PathVariable("id") long id, @RequestBody Demande demande) {

        try {
            demande.setId(id);
            demandeService.deleteDemand(demande);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return getResponseEntityDependingOnException(e);
        }
    }

}