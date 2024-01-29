package com.rentalhive.stockManagement.services.impls;

import com.rentalhive.stockManagement.dto.DemandeDto.StockQuantity;
import com.rentalhive.stockManagement.entities.Demande;
import com.rentalhive.stockManagement.entities.Stock;
import com.rentalhive.stockManagement.repositories.DemandeRepository;
import com.rentalhive.stockManagement.services.DemandeService;
import com.rentalhive.stockManagement.services.EquipmentService;
import com.rentalhive.stockManagement.services.StockService;
import com.rentalhive.stockManagement.services.UserService;
import com.rentalhive.stockManagement.services.helpers.DemandeServiceHelper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary

public class DemandeServiceImpMiseEnSituation extends DemandeServiceHelper implements DemandeService {

    private final DemandeRepository demandeRepository;

    private final StockService stockService;

    private final EquipmentService equipmentService;

    private final UserService userService;

    public DemandeServiceImpMiseEnSituation(DemandeRepository demandeRepository , StockService stockService , EquipmentService equipmentService , UserService userService) {
        super(demandeRepository ,stockService , equipmentService , userService);
        this.demandeRepository = demandeRepository;
        this.stockService = stockService;
        this.equipmentService = equipmentService;
        this.userService = userService;
    }

    @Override
    public List<Demande> getAllDemandes() {
        return null;
    }

    @Override
    public Demande addDemande(Demande demande, List<StockQuantity> pairOfEquipmentsAndTheirQuantities) {

        // Validation Demand
        validateDemandeOnAdding(demande, pairOfEquipmentsAndTheirQuantities);

        // Validation pairOfEquipmentsAndTheirQuantities
        List<Stock> availableStocks = validatePairOfEquipmentsAndTheirQuantities(pairOfEquipmentsAndTheirQuantities , demande); // If Something Wrong Throw Validation Exception , Else Return List of ids

        // Set The List Of stock to the demand
        demande.setStocks(availableStocks);

        return demandeRepository.save(demande);
    }



    @Override
    public Demande updateDemand(Demande demande) {
        return null;
    }

    @Override
    public void deleteDemand(Demande demande) {

    }

    @Override
    public Demande findById(Long id){

        throwExceptionIfIdOfDemandeIsNull(id);

        Optional<Demande> demande = demandeRepository.findById(id);

        thowExceptionIfDemandeIsEmpty(demande);

        return demande.get();

    }


}
