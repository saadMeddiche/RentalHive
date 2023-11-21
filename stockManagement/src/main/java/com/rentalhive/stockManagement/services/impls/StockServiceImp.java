package com.rentalhive.stockManagement.services.impls;

import com.rentalhive.stockManagement.entities.Stock;
import com.rentalhive.stockManagement.exceptions.costums.DoNotExistsException;
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
public class StockServiceImp implements StockService {
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private UserService userService;
    @Autowired
    private StatusService statusService;

    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    public Stock addStock(Stock stock) {
        Long equipmentId = stock.getEquipment().getId();
        Long addedById = stock.getAdded_by().getId();
        Long statusId = stock.getStatus().getId();

        if (!equipmentService.findById(equipmentId).isEmpty()) {
            throw new DoNotExistsException("The equipment does not exist");
        }

        if (!userService.findById(addedById).isEmpty()) {
            throw new DoNotExistsException("The user does not exist");
        }

        if (!statusService.findById(statusId).isEmpty()) {
            throw new DoNotExistsException("The status does not exist");
        }
        return stockRepository.save(stock);
    }

    public Stock updateStock(Stock stock) {
        Long stockId=stock.getId();
        Long equipmentId = stock.getEquipment().getId();
        Long addedById = stock.getAdded_by().getId();
        Long statusId = stock.getStatus().getId();

        if (!stockRepository.findById(stockId).isEmpty()) {
            throw new DoNotExistsException("The stock does not exist");
        }

        if (!equipmentService.findById(equipmentId).isEmpty()) {
            throw new DoNotExistsException("The equipment does not exist");
        }

        if (!userService.findById(addedById).isEmpty()) {
            throw new DoNotExistsException("The user does not exist");
        }

        if (!statusService.findById(statusId).isEmpty()) {
            throw new DoNotExistsException("The status does not exist");
        }
        return stockRepository.save(stock);
    }

    public void deleteStock(Stock stock) {
        Long stockId = stock.getId();
        Optional<Stock> existingStock = stockRepository.findById(stockId);
        if (existingStock.isEmpty()) {
            throw new DoNotExistsException("The stock does not exist");
        }

        stockRepository.delete(stock);
    }

}
