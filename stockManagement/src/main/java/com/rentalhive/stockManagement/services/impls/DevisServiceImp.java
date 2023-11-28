package com.rentalhive.stockManagement.services.impls;

import com.rentalhive.stockManagement.dto.DemandeDto.StockQuantity;
import com.rentalhive.stockManagement.entities.Contract;
import com.rentalhive.stockManagement.entities.Demande;
import com.rentalhive.stockManagement.entities.Devis;
import com.rentalhive.stockManagement.repositories.DevisRepository;
import com.rentalhive.stockManagement.services.DevisService;
import com.rentalhive.stockManagement.services.EquipmentService;
import com.rentalhive.stockManagement.services.helpers.DevisServiceHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DevisServiceImp extends DevisServiceHelper implements DevisService {

    DevisRepository devisRepository;

    EquipmentService equipmentService;

    public DevisServiceImp(DevisRepository devisRepository ,EquipmentService equipmentService) {
        super(equipmentService);
        this.devisRepository = devisRepository;
        this.equipmentService = equipmentService;
    }

    @Override
    public List<Devis> getAllDevises() {
        return null;
    }

    @Override
    public Devis addDevis(Devis devis) {
        return null;
    }

    @Override
    public Devis updateDevis(Devis devis) {
        return null;
    }

    @Override
    public boolean isExist(Devis devis){
        return devisRepository.existsById(devis.getId());
    }

    public Double calculateTotalPrice(List<StockQuantity> listOfEquipmentAndQuantity , Demande demande) {

        List<Double> listPriceOfQuantityOfEachEquipmentInOneDay = getTheListPriceOfQuantityOfEachEquipmentInOneDay(listOfEquipmentAndQuantity);

        Double totalPriceOfEquipmentsInOneDay = getTotalPriceOfEquipmentsInOneDay(listPriceOfQuantityOfEachEquipmentInOneDay);

        Long totalDays = getNumberOfDaysBetweenTwoDateTimes(demande.getDate_reservation() , demande.getDate_expiration());

        Double totalPrice  = totalPriceOfEquipmentsInOneDay * totalDays;

        return totalPrice;

    }


}
