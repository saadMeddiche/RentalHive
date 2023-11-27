package com.rentalhive.stockManagement.services.impls;

import com.rentalhive.stockManagement.DTOs.StockDTO;
import com.rentalhive.stockManagement.entities.Equipment;
import com.rentalhive.stockManagement.entities.Status;
import com.rentalhive.stockManagement.entities.Stock;
import com.rentalhive.stockManagement.exceptions.costums.AlreadyExistsException;
import com.rentalhive.stockManagement.exceptions.costums.DoNotExistsException;
import com.rentalhive.stockManagement.exceptions.costums.EmptyListException;
import com.rentalhive.stockManagement.helpers.ServiceHelper;
import com.rentalhive.stockManagement.repositories.StockRepository;
import com.rentalhive.stockManagement.services.EquipmentService;
import com.rentalhive.stockManagement.services.StatusService;
import com.rentalhive.stockManagement.services.StockService;
import com.rentalhive.stockManagement.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
