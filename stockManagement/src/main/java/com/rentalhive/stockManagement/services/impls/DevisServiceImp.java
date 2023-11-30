package com.rentalhive.stockManagement.services.impls;

import com.rentalhive.stockManagement.dto.DemandeDto.StockQuantity;
import com.rentalhive.stockManagement.entities.*;
import com.rentalhive.stockManagement.exceptions.costums.DoNotExistsException;
import com.rentalhive.stockManagement.generatePDF.GenerateDevis;
import com.rentalhive.stockManagement.generatePDF.TableRow;
import com.rentalhive.stockManagement.processors.DevisProcessor;
import com.rentalhive.stockManagement.repositories.DevisRepository;
import com.rentalhive.stockManagement.services.DevisService;
import com.rentalhive.stockManagement.services.EquipmentService;
import com.rentalhive.stockManagement.services.helpers.DevisServiceHelper;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DevisServiceImp extends DevisServiceHelper implements DevisService {

    DevisRepository devisRepository;

    EquipmentService equipmentService;

    public DevisServiceImp(DevisRepository devisRepository ,EquipmentService equipmentService) {
        super(equipmentService);
        this.devisRepository = devisRepository;
        this.equipmentService = equipmentService;
    }


    public Devis findById(Long id) {
        Optional<Devis> devis=devisRepository.findById(id);
        if(devis.isPresent()){
            return devis.get();
        }
        throw new DoNotExistsException("this devis doesn't exist");
    }
    @Override
    public List<Devis> getAllDevises() {
        return null;
    }

    @Override
    public Devis addDevis(Devis devis) {

        validateObject(devis);

        String outPutPath = createOutPutPathForDevis(devis);

        double totalPrice = createPdfForDevis(devis , outPutPath);

        devis.setPathPDF(outPutPath);

        devis.setPriceWithOutDiscount(totalPrice);

        devis.setStatus(Devis.Status.PENDING);

        return devisRepository.save(devis);
    }



    @Override
    public Devis updateDevis(Devis devis) {
        return null;
    }

    @Override
    public boolean isExist(Devis devis){
        return devisRepository.existsById(devis.getId());
    }
    public boolean isAccepted(Devis devis){
        return devisRepository.existsByStatus(devis.getStatus());
    }

    public Double calculateTotalPrice(List<StockQuantity> listOfEquipmentAndQuantity , Demande demande) {

        List<Double> listPriceOfQuantityOfEachEquipmentInOneDay = getTheListPriceOfQuantityOfEachEquipmentInOneDay(listOfEquipmentAndQuantity);

        Double totalPriceOfEquipmentsInOneDay = getTotalPriceOfEquipmentsInOneDay(listPriceOfQuantityOfEachEquipmentInOneDay);

        Long totalDays = getNumberOfDaysBetweenTwoDateTimes(demande.getDate_reservation() , demande.getDate_expiration());

        Double totalPrice  = totalPriceOfEquipmentsInOneDay * totalDays;

        return totalPrice;

    }



}
