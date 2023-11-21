package com.rentalhive.stockManagement.controllers;

import com.rentalhive.stockManagement.entities.Equipment;
import com.rentalhive.stockManagement.entities.Stock;
import com.rentalhive.stockManagement.services.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/stock")
public class StockRest {
    private final StockService stockService;


    public StockRest( StockService stockService) {
        this.stockService = stockService;
    }


    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Valid Stock stock){
        try{
            Stock addedStock= stockService.addStock(stock);
            return new ResponseEntity<>(addedStock, HttpStatus.OK);
        }catch(Exception e){
            throw e;
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> findAll() {
        try{
            List<Stock> stockList= stockService.getAllStocks();
            return new ResponseEntity<>(stockList, HttpStatus.OK);
        }catch(Exception e){
            throw e;
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStock(@PathVariable("id") long id, @RequestBody Stock stock) {
        try{
            Stock updatedStock= stockService.updateStock(stock);
            return new ResponseEntity<>(updatedStock, HttpStatus.OK);
        }catch(Exception e){
            throw e;
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStock(@PathVariable("id") long id, @RequestBody Stock stock) {
        try{
            stockService.deleteStock(stock);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            throw e;
        }
    }
}
