package com.rentalhive.stockManagement.services.implementes;

import com.rentalhive.stockManagement.entities.Stock;
import com.rentalhive.stockManagement.repositories.StockRepository;
import com.rentalhive.stockManagement.services.StockService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StockServiceImp implements StockService {
    private StockRepository stockRepository;
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
}
