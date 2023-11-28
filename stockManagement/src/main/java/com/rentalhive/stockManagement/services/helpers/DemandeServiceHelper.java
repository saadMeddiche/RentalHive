/*
package com.rentalhive.stockManagement.services.helpers;

import com.rentalhive.stockManagement.DTO.DemandeDto.StockQuantity;
import com.rentalhive.stockManagement.entities.Demande;
import com.rentalhive.stockManagement.entities.Equipment;
import com.rentalhive.stockManagement.entities.Stock;
import com.rentalhive.stockManagement.exceptions.costums.DoNotExistsException;
import com.rentalhive.stockManagement.exceptions.costums.ValidationException;
import com.rentalhive.stockManagement.helpers.ServiceHelper;
import com.rentalhive.stockManagement.repositories.DemandeRepository;
import com.rentalhive.stockManagement.repositories.EquipmentRepository;
import com.rentalhive.stockManagement.repositories.UserRepository;
import com.rentalhive.stockManagement.services.EquipmentService;
import com.rentalhive.stockManagement.services.UserService;
import com.rentalhive.stockManagement.services.impls.EquipmentServiceImp;
import com.rentalhive.stockManagement.services.impls.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class DemandeServiceHelper extends ServiceHelper {

    private final DemandeRepository demandeRepository;
    private final EquipmentService equipmentService;
    private final UserService userService;

    Predicate<Demande> isIdOfDemandeNull = demande -> demande.getId() == null;

    Predicate<Demande> isUserDoNotExist = demande ->!userService.isExists(demande.getRenter());

    Predicate<Demande> isReservationDateHigherThanExpiredDate=demande->
            demande.getDate_reservation().isBefore(demande.getDate_expiration());

    Predicate<Demande> isVerificationDateNull=demande ->
            demande.getDate_verification()==null && demande.getVerified_by()==null;

    Predicate<Demande> isDemandeAccepted= demande->!demande.getAccepted();

    Predicate<Demande> isDateReservationHigherThanDemandeDate=demande -> demande.getDate_demande().isBefore(demande.getDate_reservation());

    Predicate<Demande> isDateReservationHigherThanVerificationDate=demande -> demande.getDate_verification().isBefore(demande.getDate_reservation());

    Predicate<Equipment> isEquipmentExist= equipmentService::isExist;


    // Check If The Demande Exists
    Predicate<Demande> isDemandeExists = demande -> demandeRepository.existsById(demande.getId());

    protected Demande validateDemandeOnAdding(Demande demande, List<StockQuantity> stockQuantities) {

        // Inputs Validation
        validateObject(demande);

        // throwException If The ID Is Null
        throwExceptionIfIdOfDemandeIsNull(demande);

        // throwException If The User exist in the database (user table)
        throwExceptionIfUserDoNotExist(demande);

        throwExceptionIfReservationDateHigherThanExpiredDate(demande);

        throwExceptionIfVerificationDateNull(demande);

        throwExceptionIfDemandeAccepted(demande);

        throwExceptionIfDateReservationLowerThanDemandeDate(demande);

        demande.setStocks(CreateStockList(stockQuantities,demande));

        return demande;
    }



    protected Demande validateDemandeOnUpdating(Demande demande,List<StockQuantity> stockQuantities) {

        // Inputs Validation
        validateObject(demande);

        // throwException If The ID Is Null
        throwExceptionIfIdOfDemandeIsNull(demande);

        // throwException If The Demande exist in the database (demande table)
        throwExceptionIfTheDemandeExist(demande);

        // throwException If The User exist in the database (user table)
        throwExceptionIfUserDoNotExist(demande);

        throwExceptionIfReservationDateHigherThanExpiredDate(demande);

        throwExceptionIfDateReservationLowerThanDemandeDate(demande);

        throwExceptionIfDateReservationLowerThanVerificationDate(demande);

        demande.setStocks(CreateStockList(stockQuantities,demande));

        return demande;
    }

    protected void validateDemandeOnDeleting(Demande demande) {

        // throwException If The ID Is Null
        throwExceptionIfIdOfDemandeIsNull(demande);

        // throwException If The Demande exist in the database (demande table)
        throwExceptionIfTheDemandeExist(demande);

    }

    protected List<Stock> CreateStockList(List<StockQuantity> stockQuantities,Demande demande){
        List<Stock> stocks=new ArrayList<>();
        stockQuantities.forEach(eS->{
            Equipment equipment=new Equipment();
            equipment.setId(eS.getId());
            throwExceptionIfEquipmentDoesNotExist(equipment);
            throwExceptionIfEquipmentQuantityDoesNotExist(equipment,eS.getQuantity(),demande);
            stocks.addAll(stockService.getStocksByEquipemntQuantity(equipment, eS.getQuantity(),demande));
        });
        return stocks;
    }

    protected void throwExceptionIfTheDemandeExist(Demande demande) {
        // throwException If The Demande exist in the database (demande table)
        if (isDemandeExists.test(demande)) {
            throw new DoNotExistsException("The demande does not exist");
        }
    }

    protected void throwExceptionIfUserDoNotExist(Demande demande) {
        // throwException If The User exist in the database (user table)
        if (isUserDoNotExist.test(demande)) {
            throw new DoNotExistsException("The user does not exist");
        }
    }

    protected void throwExceptionIfIdOfDemandeIsNull(Demande demande) {
        // throwException If The ID of demande Is Null
        if (isIdOfDemandeNull.test(demande)) {
            throw new ValidationException(List.of("The ID of demande can not be null"));
        }

    }

    protected void throwExceptionIfReservationDateHigherThanExpiredDate(Demande demande) {

        if (!isReservationDateHigherThanExpiredDate.test(demande)) {
            throw new ValidationException(List.of("Reservation Date is Higher Than Expiration Date"));
        }

    }
    protected void throwExceptionIfVerificationDateNull(Demande demande) {

        if (!isVerificationDateNull.test(demande)) {
            throw new ValidationException(List.of("the Verification Date need to be Null"));
        }

    }
    protected void throwExceptionIfDemandeAccepted(Demande demande) {

        if (!isDemandeAccepted.test(demande)) {
            throw new ValidationException(List.of("The demande can't be accepted before creating it"));
        }

    }
    protected void throwExceptionIfDateReservationLowerThanDemandeDate(Demande demande) {
        if (!isDateReservationHigherThanDemandeDate.test(demande)) {
            throw new ValidationException(List.of("The Reservation Date Lower Than Demande Date"));
        }
    }

    protected void throwExceptionIfEquipmentQuantityDoesNotExist(Equipment equipment,Integer quantity,Demande demande) {
        if (equipmentService.countAvailableStocksForEquipment(equipment,demande)>=quantity) {
            throw new ValidationException(List.of("Our stock can't provide you with the quantity your asking for"));
        }
    }

    protected void throwExceptionIfEquipmentDoesNotExist(Equipment equipment){
        if (!isEquipmentExist.test(equipment)) {
            throw new ValidationException(List.of("The equipment you want doesn't exist in our stock"));
        }
    }
    protected void throwExceptionIfDateReservationLowerThanVerificationDate(Demande demande){
        if (!isDateReservationHigherThanVerificationDate.test(demande)) {
            throw new ValidationException(List.of("Reservation Date is Lower Than The Verification Date"));
        }
    }
}*/
