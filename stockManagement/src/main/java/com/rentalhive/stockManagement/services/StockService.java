package com.rentalhive.stockManagement.services;


import com.rentalhive.stockManagement.entities.Demande;

import com.rentalhive.stockManagement.entities.Equipment;
import com.rentalhive.stockManagement.entities.Stock;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface StockService {

    public abstract List<Stock> getAllStocks();

    public abstract Stock addStock(Stock stock);

    public abstract Stock updateStock(Stock stock);
    public Stock findById(Long id);
    public abstract void deleteStock(Long id);
    public abstract void deleteStock(Stock stock);
    public Integer countAvailableStocksForEquipment(Equipment equipment);
    public Integer countAvailableAndRentedStocksForEquipment(Equipment equipment, Demande demande);
    public List<Stock> getStocksByEquipemntQuantity(Equipment equipment, Integer quantity, Demande demande);
    public List<Stock> getStocksByEquipmentQuantityRentedAndAvailable(Equipment equipment, Integer quantity, Demande demande);

    public List<Stock> getAvailableStocks(Long equipmentId , LocalDateTime dateReservation, LocalDateTime dateExpiration);
    }

