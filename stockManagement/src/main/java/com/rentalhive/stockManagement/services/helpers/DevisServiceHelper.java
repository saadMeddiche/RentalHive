package com.rentalhive.stockManagement.services.helpers;

import com.rentalhive.stockManagement.dto.DemandeDto.StockQuantity;
import com.rentalhive.stockManagement.entities.Equipment;
import com.rentalhive.stockManagement.services.EquipmentService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class DevisServiceHelper {

    EquipmentService equipmentService;

    public DevisServiceHelper(){
    }

    public DevisServiceHelper (EquipmentService equipmentService){
        this.equipmentService = equipmentService;
    }

    BiFunction<Long , Integer, Double> calculatePriceOfQuantityOfSpecificEquipment = (equipmentId, quantity) -> {

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
