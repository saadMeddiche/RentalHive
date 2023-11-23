package com.rentalhive.stockManagement.services.impls;

import com.rentalhive.stockManagement.entities.Demande;
import com.rentalhive.stockManagement.entities.Equipment;
import com.rentalhive.stockManagement.entities.Stock;
import com.rentalhive.stockManagement.repositories.StockRepository;
import com.rentalhive.stockManagement.services.StockService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StockServiceImp implements StockService {

    final StockRepository stockRepository;
    public List<Stock> getAllStocks() {
        return null;
    }

    public Stock addStock(Stock stock) {
        return null;
    }

    public Stock updateStock(Stock stock) {
        return null;
    }

    public void deleteStock(Stock stock) {

    }

    public Integer countAvailableAndRentedStocksForEquipment(Equipment equipment, Demande demande){
       Integer total=countAvailableStocksForEquipment(equipment);
        final Integer[] newCount = {0};
        List<Stock> stocks= stockRepository.findByEquipmentAndStatusName(equipment,"Rented");
        stocks.forEach(s->{
                    long count=s.getDemandes().stream().filter(d->(d.getDate_reservation().isBefore(demande.getDate_expiration()) || d.getDate_expiration().isAfter(demande.getDate_reservation()))).count();
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
}
