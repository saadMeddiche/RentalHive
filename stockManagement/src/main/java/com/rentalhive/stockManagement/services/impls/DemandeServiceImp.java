package com.rentalhive.stockManagement.services.impls;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.rentalhive.stockManagement.embeddables.StockQuantity;
import com.rentalhive.stockManagement.entities.Demande;
import com.rentalhive.stockManagement.entities.Equipment;
import com.rentalhive.stockManagement.entities.Stock;
import com.rentalhive.stockManagement.exceptions.costums.DoNotExistsException;
import com.rentalhive.stockManagement.exceptions.costums.ValidationException;
import com.rentalhive.stockManagement.helpers.ServiceHelper;
import com.rentalhive.stockManagement.repositories.DemandeRepository;
import com.rentalhive.stockManagement.services.DemandeService;
import com.rentalhive.stockManagement.services.EquipmentService;
import com.rentalhive.stockManagement.services.StockService;
import com.rentalhive.stockManagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemandeServiceImp extends ServiceHelper implements DemandeService {

@Autowired
    final DemandeRepository demandeRepository;

@Autowired
    final EquipmentService equipmentService ;

@Autowired
    final StockService stockService;

@Autowired
    final UserService userService;

    public DemandeServiceImp(DemandeRepository demandeRepository, EquipmentService equipmentService, UserService userService,StockService stockService) {
        this.demandeRepository = demandeRepository;
        this.equipmentService = equipmentService;
        this.userService = userService;
        this.stockService=stockService;
    }

    @Override
    public List<Demande> getAllDemandes() {
        return demandeRepository.findAll();
    }

    @Override
    public Demande addDemande(Demande demande, List<StockQuantity> stockQuantities) {
        Demande Ndemande=validateDemandeOnAdding(demande,stockQuantities);

        return demandeRepository.save(Ndemande);
    }

    @Override
    public Demande updateDemand(Demande demande, List<StockQuantity> stockQuantities) {
        Demande Ndemande=validateDemandeOnUpdating(demande,stockQuantities);
        return demandeRepository.save(Ndemande);
    }

    @Override
    public void deleteDemand(Demande demande) {
           validateDemandeOnDeleting(demande);

        demandeRepository.delete(demande);
    }

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

    protected Demande validateDemandeOnUpdating(Demande demandeN,List<StockQuantity> stockQuantities) {

        // Inputs Validation
        validateObject(demandeN);

        // throwException If The ID Is Null
        throwExceptionIfIdOfDemandeIsNull(demandeN);

        // throwException If The Demande exist in the database (demande table)
        throwExceptionIfTheDemandeExist(demandeN);

        Optional<Demande> demandeOp=demandeRepository.findById(demandeN.getId());
        if(demandeOp.isPresent()){
            Demande demande=demandeOp.get();
            demande.setAccepted(demandeN.getAccepted());
            demande.setDate_verification(LocalDateTime.now());
            demande.setVerified_by(demandeN.getVerified_by());

        // throwException If The User exist in the database (user table)
        throwExceptionIfUserDoNotExist(demande);

        throwExceptionIfReservationDateHigherThanExpiredDate(demande);

        throwExceptionIfDateReservationLowerThanDemandeDate(demande);

        throwExceptionIfDateReservationLowerThanVerificationDate(demande);

        demande.setStocks(CreateStockList(stockQuantities,demande));

        return demande;
        }
        throw new DoNotExistsException("the demande you want to update doesn't exist");
    }

    protected void validateDemandeOnDeleting(Demande demande) {

        // throwException If The ID Is Null
        throwExceptionIfIdOfDemandeIsNull(demande);

        // throwException If The Demande exist in the database (demande table)
        throwExceptionIfTheDemandeExist(demande);

    }

    protected List<Stock> CreateStockList(List<StockQuantity> stockQuantities, Demande demande){
        List<Stock> stocks=new ArrayList<>();
        stockQuantities.forEach(eS->{
            Equipment equipment=new Equipment();
            equipment.setId(eS.getId());
            throwExceptionIfEquipmentDoesNotExist(equipment);
            if (stockService.countAvailableStocksForEquipment(equipment)>= eS.getQuantity()) {
                stocks.addAll(stockService.getStocksByEquipemntQuantity(equipment, eS.getQuantity(),demande));
            }else if(stockService.countAvailableAndRentedStocksForEquipment(equipment,demande)>= eS.getQuantity()) {
                stocks.addAll(stockService.getStocksByEquipmentQuantityRentedAndAvailable(equipment, eS.getQuantity(),demande));
            }else{
                throw new ValidationException(List.of("Our stock can't provide you with the quantity your asking for"));
            }
        });
        return stocks;
    }

    protected void throwExceptionIfTheDemandeExist(Demande demande) {
        // throwException If The Demande exist in the database (demande table)
        if (!demandeRepository.existsById(demande.getId())) {
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

    protected void throwExceptionIfEquipmentQuantityDoesNotExist(Equipment equipment,Integer quantity,Demande demande) {
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
