package com.rentalhive.stockManagement.services.helpers;

import com.rentalhive.stockManagement.dto.DemandeDto.StockQuantity;
import com.rentalhive.stockManagement.entities.Demande;
import com.rentalhive.stockManagement.entities.Equipment;
import com.rentalhive.stockManagement.entities.Stock;
import com.rentalhive.stockManagement.exceptions.costums.DoNotExistsException;
import com.rentalhive.stockManagement.exceptions.costums.ValidationException;
import com.rentalhive.stockManagement.helpers.ServiceHelper;
import com.rentalhive.stockManagement.repositories.DemandeRepository;
import com.rentalhive.stockManagement.services.EquipmentService;
import com.rentalhive.stockManagement.services.StockService;
import com.rentalhive.stockManagement.services.UserService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DemandeServiceHelper extends ServiceHelper {

    private final DemandeRepository demandeRepository;

    private final StockService stockService;

    private final EquipmentService equipmentService;

    private final UserService userService;

    public DemandeServiceHelper(DemandeRepository demandeRepository , StockService stockService , EquipmentService equipmentService , UserService userService) {
        this.stockService = stockService;
        this.demandeRepository = demandeRepository;
        this.equipmentService = equipmentService;
        this.userService = userService;
    }

    public  static void main(String[] args) {

        List<StockQuantity> examaple = new ArrayList<>();

        examaple.add(new StockQuantity(10, 1L));
        examaple.add(new StockQuantity(10, 2L));
        examaple.add(new StockQuantity(10, 3L));
        examaple.add(new StockQuantity(10, 4L));
        examaple.add(new StockQuantity(10, 5L));
        examaple.add(new StockQuantity(10, 6L));
        examaple.add(new StockQuantity(10, 7L));

        List<StockQuantity> mergededQuantities = mergeRepeatedIdEquipments(examaple);
        System.out.printf(mergededQuantities.toString());

    }

    public List<Stock> validatePairOfEquipmentsAndTheirQuantities(List<StockQuantity> pairOfEquipmentsAndTheirQuantities , Demande demande){

        // Check If The reserve of our stock is enough to fulfill the demand

            // Check If There is any repeated equipment in the list , then merge the quantities in one equipment
            List<StockQuantity> mergededQuantities = mergeRepeatedIdEquipments(pairOfEquipmentsAndTheirQuantities);

            // Throw Exception If The reserve of our stock is not enough
            List<Stock> availableStocks = throwExceptionIfReserveOfStockIsNotEnough(mergededQuantities , demande); // if everything is ok , Return List of ids of available stock

            return availableStocks;
    }

    public static List<StockQuantity> mergeRepeatedIdEquipments(List<StockQuantity> pairOfEquipmentsAndTheirQuantities){

        // Mapping
        Map<Long , Integer> mergededQuantitiesmap =  pairOfEquipmentsAndTheirQuantities.stream().collect(
                Collectors.groupingBy(StockQuantity::getEquipmentId, Collectors.summingInt(StockQuantity::getQuantity)));

        if(mergededQuantitiesmap.size() == pairOfEquipmentsAndTheirQuantities.size()){
            return pairOfEquipmentsAndTheirQuantities;
        }

        System.out.println("----------------- Passed");

        // With The Created Map , I will create a new list of StockQuantity
        List<StockQuantity> stockQuantities = mergededQuantitiesmap.entrySet().stream().map(entry -> {
            return new StockQuantity(
                   entry.getValue()
                    ,
                    entry.getKey()
                    );
        }).toList();

        return  stockQuantities;
    }

    public List<Stock> throwExceptionIfReserveOfStockIsNotEnough(List<StockQuantity> pairOfEquipmentsAndTheirQuantities , Demande demande){

        List<Stock> availableStock = new ArrayList<>();

        List<String> erros = pairOfEquipmentsAndTheirQuantities.stream().filter(pair ->{

            List<Stock> temp = stockService.getAvailableStocks(pair.getEquipmentId() , demande.getDate_reservation() , demande.getDate_expiration());

            if(temp.size() >= pair.getQuantity()){
                temp.subList(0 , pair.getQuantity());
                availableStock.addAll(temp);
                return false;
            }

            return true;
        }

        ).map(pair -> "The Stock Of equipment with id : " + pair.getEquipmentId() + " is not enough").collect(Collectors.toList());

        if(!erros.isEmpty()){
            throw new ValidationException(erros);
        }

        return availableStock;
    }

    protected void validateDemandeOnAdding(Demande demande, List<StockQuantity> stockQuantities) {

        // Inputs Validation
        validateObject(demande);

        // throwException If The User exist in the database (user table)
        throwExceptionIfUserDoNotExist(demande);

        throwExceptionIfReservationDateHigherThanExpiredDate(demande);

        throwExceptionIfVerificationDateNull(demande);

        throwExceptionIfDemandeAccepted(demande);

        throwExceptionIfDateReservationLowerThanDemandeDate(demande);

    }



    protected void throwExceptionIfTheDemandeExist(Demande demande) {
        // throwException If The Demande exist in the database (demande table)
        if (!demandeRepository.existsById(demande.getId())) {
            throw new DoNotExistsException("The demande does not exist");

        }
    }

    protected void thowExceptionIfDemandeIsEmpty(Optional<Demande> demande) {

        if (demande.isEmpty()) {
            throw new DoNotExistsException("The demande does not exist");
        }
    }

    protected void throwExceptionIfUserDoNotExist(Demande demande) {
        // throwException If The User exist in the database (user table)
        if (!userService.isExists(demande.getRenter())) {
            throw new DoNotExistsException("The user does not exist");
        }
    }

    protected void throwExceptionIfIdOfDemandeIsNull(Demande demande) {
        // throwException If The ID of demande Is Null
        if (demande.getId() == null) {
            throw new ValidationException(List.of("The ID of demande can not be null"));
        }
    }

    protected void throwExceptionIfIdOfEquipmentIdIsNull(Long id) {
        if (id == null) {
            throw new ValidationException(List.of("The ID of equipment can not be null"));
        }
    }

    protected void throwExceptionIfIdOfDemandeIsNull(Long id) {
        // throwException If The ID of demande Is Null
        if (id == null) {
            throw new ValidationException(List.of("The ID of demande can not be null"));
        }

    }

    protected void throwExceptionIfReservationDateHigherThanExpiredDate(Demande demande) {

        if (!demande.getDate_reservation().isBefore(demande.getDate_expiration())) {
            throw new ValidationException(List.of("Reservation Date is Higher Than Expiration Date"));
        }

    }
    protected void throwExceptionIfVerificationDateNull(Demande demande) {

        if (!(demande.getDate_verification()==null && demande.getVerified_by()==null)) {
            throw new ValidationException(List.of("the Verification Date need to be Null"));
        }

    }
    protected void throwExceptionIfDemandeAccepted(Demande demande) {

        if (demande.getAccepted()) {
            throw new ValidationException(List.of("The demande can't be accepted before creating it"));
        }

    }
    protected void throwExceptionIfDateReservationLowerThanDemandeDate(Demande demande) {
        if (!demande.getDate_demande().isBefore(demande.getDate_reservation())) {
            throw new ValidationException(List.of("The Reservation Date Lower Than Demande Date"));
        }
    }

    protected void throwExceptionIfEquipmentQuantityDoesNotExist(Equipment equipment, Integer quantity, Demande demande) {
        if (!(stockService.countAvailableStocksForEquipment(equipment)>=quantity)) {
            if(stockService.countAvailableAndRentedStocksForEquipment(equipment,demande)<quantity) {
                throw new ValidationException(List.of("Our stock can't provide you with the quantity your asking for"));
            }
        }
    }

    protected void throwExceptionIfEquipmentDoesNotExist(Equipment equipment){
        if (!equipmentService.isExist(equipment)) {
            throw new ValidationException(List.of("The equipment you want doesn't exist in our stock"));
        }
    }
    protected void throwExceptionIfDateReservationLowerThanVerificationDate(Demande demande){
        if (!demande.getDate_verification().isBefore(demande.getDate_reservation())) {
            throw new ValidationException(List.of("Reservation Date is Lower Than The Verification Date"));
        }
    }







}
