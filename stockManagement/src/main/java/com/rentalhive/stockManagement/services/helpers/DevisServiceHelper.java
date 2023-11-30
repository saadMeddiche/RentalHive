package com.rentalhive.stockManagement.services.helpers;

import com.rentalhive.stockManagement.dto.DemandeDto.StockQuantity;
import com.rentalhive.stockManagement.entities.Devis;
import com.rentalhive.stockManagement.entities.Equipment;
import com.rentalhive.stockManagement.generatePDF.TableRow;
import com.rentalhive.stockManagement.helpers.ServiceHelper;
import com.rentalhive.stockManagement.processors.DevisProcessor;
import com.rentalhive.stockManagement.services.EquipmentService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class DevisServiceHelper extends ServiceHelper {

    EquipmentService equipmentService;

    public DevisServiceHelper(){
    }

    public DevisServiceHelper (EquipmentService equipmentService){
        this.equipmentService = equipmentService;
    }


    public double createPdfForDevis(Devis devis , String outPutPath){
        DevisProcessor devisProcessor = new DevisProcessor();
        return devisProcessor.processDevis(devis , outPutPath);
    }

    public String createOutPutPathForDevis(Devis devis){

        // Get Current Time In Milliseconds
        long currentTimeMillis = System.currentTimeMillis();

        StringBuilder outPutPath = new StringBuilder();
        outPutPath.append("src/main/resources/devises/devis-");
        outPutPath.append(devis.getDemande().getId());
        outPutPath.append("-");
        outPutPath.append(currentTimeMillis);
        outPutPath.append(".pdf");

        return outPutPath.toString();

    }

    public BiFunction<Long , Integer, Double> calculatePriceOfQuantityOfSpecificEquipment = (equipmentId, quantity) -> {

        Equipment equipment = equipmentService.findById(equipmentId);

        Double pricePerDay = equipment.getPrice_per_day();

        return pricePerDay * quantity;
    };


    public Long getNumberOfDaysBetweenTwoDateTimes(LocalDateTime startDate , LocalDateTime endDate){
        return ChronoUnit.DAYS.between(startDate, endDate);
    }


    public List<Double> getTheListPriceOfQuantityOfEachEquipmentInOneDay(List<StockQuantity> listOfEquipmentAndQuantity) {
        return listOfEquipmentAndQuantity.stream().map(
                stockQuantity ->
                        calculatePriceOfQuantityOfSpecificEquipment.apply(
                                stockQuantity.getEquipmentId() ,
                                stockQuantity.getQuantity()
                        )
        ).collect(Collectors.toList());
    }

    public Double getTotalPriceOfEquipmentsInOneDay(List<Double> listPriceOfQuantityOfEachEquipmentInOneDay){
        return  listPriceOfQuantityOfEachEquipmentInOneDay.stream().mapToDouble(Double::doubleValue).sum();
    }





}
