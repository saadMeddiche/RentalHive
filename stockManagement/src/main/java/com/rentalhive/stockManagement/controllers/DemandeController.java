package com.rentalhive.stockManagement.controllers;

import com.rentalhive.stockManagement.embeddables.StockQuantity;
import com.rentalhive.stockManagement.entities.Demande;
import com.rentalhive.stockManagement.helpers.ControllerHelper;
import com.rentalhive.stockManagement.services.DemandeService;
import com.rentalhive.stockManagement.services.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/RentalHive/Api/Demande")
public class DemandeController extends ControllerHelper {
    @Autowired
    private DemandeService demandeService;

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
    public ResponseEntity<?> addDemande(@RequestBody Demande demande,@RequestBody List<StockQuantity> stockQuantities) {

        try {

            Demande addedDemande = demandeService.addDemande(demande,stockQuantities);

            return new ResponseEntity<>(addedDemande, HttpStatus.OK);

        } catch (Exception e) {
            return getResponseEntityDependingOnException(e);
        }
    }

    @PutMapping("/demandes/{id}")
    public ResponseEntity<?> updateDemande(@PathVariable("id") long id, @RequestBody Demande demande, @RequestBody List<StockQuantity> stockQuantities) {

        try {

            Demande addedDemande = demandeService.updateDemand(demande,stockQuantities);

            return new ResponseEntity<>(addedDemande, HttpStatus.OK);

        } catch (Exception e) {
            return getResponseEntityDependingOnException(e);
        }
    }

    @DeleteMapping("/demandes/{id}")
    public ResponseEntity<?> deleteDemande(@PathVariable("id") long id, @RequestBody Demande demande) {

        try {

            demandeService.deleteDemand(demande);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return getResponseEntityDependingOnException(e);
        }
    }

}
