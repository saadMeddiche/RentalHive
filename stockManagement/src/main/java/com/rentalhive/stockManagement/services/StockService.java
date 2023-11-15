package com.rentalhive.stockManagement.services;

import com.rentalhive.stockManagement.entities.Stock;

import java.util.List;

public interface StockService {

    public abstract List<Stock> getAllStocks();

    public abstract Stock addStock(Stock stock);

    public abstract Stock updateStock(Stock stock);

    public abstract void deleteStock(Stock stock);
}