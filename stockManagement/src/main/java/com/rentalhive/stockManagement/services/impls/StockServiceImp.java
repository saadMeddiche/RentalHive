package com.rentalhive.stockManagement.services.impls;

import com.rentalhive.stockManagement.DTOs.StockDTO;
import com.rentalhive.stockManagement.entities.Equipment;
import com.rentalhive.stockManagement.entities.Status;

import com.rentalhive.stockManagement.entities.Demande;
import com.rentalhive.stockManagement.entities.Equipment;

import com.rentalhive.stockManagement.entities.Stock;
import com.rentalhive.stockManagement.exceptions.costums.AlreadyExistsException;
import com.rentalhive.stockManagement.exceptions.costums.DoNotExistsException;
import com.rentalhive.stockManagement.exceptions.costums.EmptyListException;
import com.rentalhive.stockManagement.exceptions.costums.ValidationException;
import com.rentalhive.stockManagement.helpers.ServiceHelper;
import com.rentalhive.stockManagement.repositories.StockRepository;
import com.rentalhive.stockManagement.services.EquipmentService;
import com.rentalhive.stockManagement.services.StatusService;
import com.rentalhive.stockManagement.services.StockService;
import com.rentalhive.stockManagement.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class StockServiceImp extends ServiceHelper implements StockService {
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private UserService userService;
    @Autowired
    private StatusService statusService;

    public List<Stock> getAllStocks() {
        List<Stock> stocksList=stockRepository.findAll();
        if(stocksList.isEmpty()){
            throw new EmptyListException("there is no stocks");
        }
        return stocksList;
    }

    public Stock findById(Long id){
        if(id == null){
            throw new ValidationException(List.of("Id Of The Stock can not be null"));
        }

        Optional<Stock> stock = stockRepository.findById(id);

        if(stock.isEmpty()){
            throw new DoNotExistsException("The stock With Id :" + id+ "Do not Exist");
        }

        return stock.get();
    }

    public Stock addStock(Stock stock) {
        validateObject(stock);
        Long statusId = stock.getStatus().getId();
        Long equipmentId = stock.getEquipment().getId();
        Long addedById = stock.getAdded_by().getId();


        if (!stockRepository.findByRegistrationNumber(stock.getRegistrationNumber()).isEmpty()) {
            throw new AlreadyExistsException("Registration number already exists try another");
        }

        if (equipmentService.findEquipmentById(equipmentId).isEmpty()) {
            System.out.println("equipment");
            throw new DoNotExistsException("The equipment does not exist");
        }

        if (userService.findUserById(addedById).isEmpty()) {
            throw new DoNotExistsException("The user does not exist");
        }

        if (statusService.findStatusById(statusId).isEmpty()) {
            throw new DoNotExistsException("The status does not exist");
        }
        return stockRepository.save(stock);
    }

    public Stock updateStock(Stock stock) {
        validateObject(stock);
        Long stockId=stock.getId();
        Long equipmentId = stock.getEquipment().getId();
        Long addedById = stock.getAdded_by().getId();
        Long statusId = stock.getStatus().getId();

        Optional<Stock> stockOptionalById=stockRepository.findById(stockId);
        String newRegistrationNumber=stock.getRegistrationNumber();

        if (stockOptionalById.isEmpty()) {
            System.out.println("1");
            throw new DoNotExistsException("The stock does not exist");
        }else{
            Optional<Stock> stockOptionalByRegistrationNumber=stockRepository.findByRegistrationNumber(newRegistrationNumber);
            if(stockOptionalByRegistrationNumber.isPresent()) {
                Long foundStockByRegistrationNumberId=stockOptionalByRegistrationNumber.get().getId();
                System.out.println(2);
                if(!foundStockByRegistrationNumberId.equals(stockId)){
                    System.out.println("3");
                    throw new AlreadyExistsException("Registration number already exists try another");
                }
            }
        }

        if (equipmentService.findEquipmentById(equipmentId).isEmpty()) {
            throw new DoNotExistsException("The equipment does not exist");
        }

        if (userService.findUserById(addedById).isEmpty()) {
            throw new DoNotExistsException("The user does not exist");
        }

        if (statusService.findStatusById(statusId).isEmpty()) {
            throw new DoNotExistsException("The status does not exist");
        }
        return stockRepository.save(stock);
    }

    public void deleteStock(Long id) {

        throwExceptionIfObjectIsNull(id , "The id cannot be null");

        Optional<Stock> existingStock = stockRepository.findById(id);
        if (existingStock.isEmpty()) {
            throw new DoNotExistsException("The stock does not exist");
        }

        stockRepository.deleteById(id);
    }

    public void deleteStock(Stock stock){
        throw  new ValidationException(List.of("deleteStock(Stock stock) has been used"));
    }


    public Integer countAvailableAndRentedStocksForEquipment(Equipment equipment, Demande demande){
       Integer total=countAvailableStocksForEquipment(equipment);
        final Integer[] newCount = {0};
        List<Stock> stocks= stockRepository.findByEquipmentAndStatusName(equipment,"Rented");
        stocks.forEach(s->{
            long count=s.getDemandes().stream().filter(d->(d.getDate_reservation().isAfter(demande.getDate_reservation()) && d.getDate_reservation().isBefore(demande.getDate_expiration())) ||
                    (d.getDate_reservation().isAfter(d.getDate_reservation()) && (d.getDate_expiration().isAfter(demande.getDate_reservation()) && d.getDate_reservation().isBefore(demande.getDate_expiration()))) ||
                    (d.getDate_reservation().isBefore(demande.getDate_reservation()) && d.getDate_expiration().isAfter(demande.getDate_expiration()))).count();
            if(count==0){
                newCount[0]++;
            }
                }
        );
        return total + newCount[0];
    }
    public Integer countAvailableStocksForEquipment(Equipment equipment){
        return stockRepository.countByEquipmentAndStatusName(equipment, "Available");
    }
    public List<Stock> getStocksByEquipemntQuantity(Equipment equipment, Integer quantity, Demande demande){
        Pageable pageable = PageRequest.of(0, quantity);
        return stockRepository.findByEquipmentAndStatusName(equipment,"Available",pageable);
    }
    public List<Stock> getStocksByEquipmentQuantityRentedAndAvailable(Equipment equipment, Integer quantity, Demande demande){
        List<Stock> stocks=stockRepository.findByEquipmentAndStatusName(equipment,"Available");
        Pageable pageable = PageRequest.of(0, stocks.size());
        stocks.addAll(stockRepository.findByEquipmentAndStatusName(equipment,"Available",pageable));
        return stocks;
    }


    public List<Stock> getAvailableStocks(Long equipmentId , LocalDateTime dateReservation, LocalDateTime dateExpiration){
        return stockRepository.getAvailableStocks(equipmentId,dateReservation,dateExpiration);
    };

}
