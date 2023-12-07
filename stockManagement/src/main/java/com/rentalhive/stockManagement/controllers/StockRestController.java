package com.rentalhive.stockManagement.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.rentalhive.stockManagement.DTOs.*;
import com.rentalhive.stockManagement.converters.CategoryConverter;
import com.rentalhive.stockManagement.converters.EquipmentConverter;
import com.rentalhive.stockManagement.dto.categoryDtos.response.CategoryResponseDto;
import com.rentalhive.stockManagement.dto.equipmentDtos.response.EquipmentResponseDto;
import com.rentalhive.stockManagement.entities.Equipment;
import com.rentalhive.stockManagement.entities.User;
import com.rentalhive.stockManagement.helpers.ControllerHelper;

import com.rentalhive.stockManagement.entities.Status;
import com.rentalhive.stockManagement.entities.Stock;
import com.rentalhive.stockManagement.services.EquipmentService;
import com.rentalhive.stockManagement.services.StockService;
import com.rentalhive.stockManagement.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class StockRestController extends ControllerHelper {
    private final StockService stockService;
    private final UserService userService;
    private final EquipmentService equipmentService;


    public StockRestController(StockService stockService, UserService userService, EquipmentService equipmentService) {
        this.stockService = stockService;
        this.userService = userService;
        this.equipmentService = equipmentService;
    }

    @PostMapping("/stocks")
    public ResponseEntity<?> save(@RequestBody @Valid StockDTO stockDTO){
        try{
            Stock stock=convertToEntity(stockDTO);
            Stock addedStock= stockService.addStock(stock);
            StockResponseDTO addedStockDTO=convertToDTO(addedStock);
            return new ResponseEntity<>(addedStockDTO, HttpStatus.OK);
        }catch(Exception e){
            return getResponseEntityDependingOnException(e);
        }
    }

    @GetMapping("/stocks")
    public ResponseEntity<?> findAll() {
        try{
            List<Stock> stockList= stockService.getAllStocks();
            List<StockResponseDTO> stockDTOList=convertToDTOList(stockList);
            return new ResponseEntity<>(stockDTOList, HttpStatus.OK);
        }catch(Exception e){
            System.out.println("not stocks");
            return getResponseEntityDependingOnException(e);
        }
    }

    @GetMapping("/stocks/{id}")
    public ResponseEntity<?> getStockById(@PathVariable("id") long id) {
        try {

            Stock stock = stockService.findById(id);

            StockResponseDTO stockDTO = convertToDTO(stock);

            return new ResponseEntity<>(stock, HttpStatus.OK);
        }catch (Exception e){
            return getResponseEntityDependingOnException(e);
        }
    }

    @PutMapping("/stocks/{id}")
    public ResponseEntity<?> updateStock(@PathVariable("id") long id, @RequestBody StockDTO stockDTO) {
        try{
            Stock stock = convertToEntity(stockDTO);
            stock.setId(id);
            Stock updatedStock= stockService.updateStock(stock);
            StockResponseDTO updatedStockDTO=convertToDTO(updatedStock);
            return new ResponseEntity<>(updatedStockDTO, HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getClass());
            return getResponseEntityDependingOnException(e);
        }
    }


    @DeleteMapping("/stocks/{id}")
    public ResponseEntity<?> deleteStock(@PathVariable("id") long id) {
        try{
            stockService.deleteStock(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return getResponseEntityDependingOnException(e);
        }
    }

    private Stock convertToEntity(StockDTO stockDTO) {
        Stock stock = new Stock();
        stock.setRegistrationNumber(stockDTO.getRegistrationNumber());
        stock.setAdded_by(userService.findById(stockDTO.getAdded_by_id()));
        stock.setEquipment(equipmentService.findById(stockDTO.getEquipment_id()) );
        stock.setStatus(new Status(1L,"available"));
        return stock;
    }

    private StockResponseDTO convertToDTO(Stock stock) {
        StockResponseDTO stockResponseDTO = new StockResponseDTO();
        stockResponseDTO.setId(stock.getId());
        CategoryConverter categoryConverter=new CategoryConverter();
        stockResponseDTO.setRegistrationNumber(stock.getRegistrationNumber());
        User added_by=stock.getAdded_by();
        UserDTO added_by_DTO=new UserDTO(added_by.getFull_name(),added_by.getUser_name(),added_by.getEmail(),added_by.getPassword(),added_by.getDate_creation_account());
        stockResponseDTO.setAdded_by(added_by_DTO);
        Equipment equipment=stock.getEquipment();
        CategoryResponseDto categoryDTO=categoryConverter.convertToDto(equipment.getCategory());
        EquipmentDTO equipmentDTO=new EquipmentDTO(equipment.getName(),equipment.getPrice_per_day(),categoryDTO);
        stockResponseDTO.setEquipment(equipmentDTO);
        StatusDTO statusSTO=new StatusDTO(stock.getStatus().getName());
        stockResponseDTO.setStatus(statusSTO);
        return stockResponseDTO;
    }

    private List<StockResponseDTO> convertToDTOList(List<Stock> stocks) {
        return stocks.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

}
