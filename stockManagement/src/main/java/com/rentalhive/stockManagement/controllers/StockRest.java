package com.rentalhive.stockManagement.controllers;

import com.rentalhive.stockManagement.DTO.StockDTO;
import com.rentalhive.stockManagement.entities.Equipment;
import com.rentalhive.stockManagement.entities.Status;
import com.rentalhive.stockManagement.entities.Stock;
import com.rentalhive.stockManagement.services.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stock")
public class StockRest {
    private final StockService stockService;



    public StockRest( StockService stockService) {
        this.stockService = stockService;
    }


    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Valid StockDTO stockDTO){
        try{
            Stock stock=convertToEntity(stockDTO);
            Stock addedStock= stockService.addStock(stock);
            StockDTO addedStockDTO=convertToDTO(addedStock);
            return new ResponseEntity<>(addedStockDTO, HttpStatus.OK);
        }catch(Exception e){
            throw e;
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> findAll() {
        try{
            List<Stock> stockList= stockService.getAllStocks();
            List<StockDTO> stockDTOList=convertToDTOList(stockList);
            return new ResponseEntity<>(stockDTOList, HttpStatus.OK);
        }catch(Exception e){
            throw e;
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStock(@PathVariable("id") long id, @RequestBody StockDTO stockDTO) {
        try{
            Stock stock = convertToEntity(stockDTO);
            Stock updatedStock= stockService.updateStock(stock);
            StockDTO updatedStockDTO=convertToDTO(updatedStock);
            return new ResponseEntity<>(updatedStockDTO, HttpStatus.OK);
        }catch(Exception e){
            throw e;
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStock(@PathVariable("id") long id, @RequestBody StockDTO stockDTO) {
        try{
            Stock stock=convertToEntity(stockDTO);
            stockService.deleteStock(stock);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            throw e;
        }
    }

    private Stock convertToEntity(StockDTO stockDTO) {
        Stock stock = new Stock();
        stock.setRegistrationNumber(stockDTO.getRegistrationNumber());
        stock.setAdded_by(stockDTO.getAdded_by());
        stock.setEquipment(stockDTO.getEquipment());
        stock.setStatus(new Status(1L,"available"));
        return stock;
    }

    private StockDTO convertToDTO(Stock stock) {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setRegistrationNumber(stock.getRegistrationNumber());
        stockDTO.setAdded_by(stock.getAdded_by());
        stockDTO.setEquipment(stock.getEquipment());
        stockDTO.setStatus(stock.getStatus());
        return stockDTO;
    }

    private List<StockDTO> convertToDTOList(List<Stock> stocks) {
        return stocks.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

}
