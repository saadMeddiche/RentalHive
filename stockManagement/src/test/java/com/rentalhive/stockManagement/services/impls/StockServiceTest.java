package com.rentalhive.stockManagement.services.impls;

import com.rentalhive.stockManagement.embeddables.AddressEmail;
import com.rentalhive.stockManagement.embeddables.FullName;
import com.rentalhive.stockManagement.embeddables.Password;
import com.rentalhive.stockManagement.entities.*;
import com.rentalhive.stockManagement.repositories.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StockServiceTest {
    private StockServiceImp stockServiceImp;
    private StatusServiceImp statusServiceImp;
    private UserServiceImp userServiceImp;
    private EquipmentServiceImp equipmentServiceImp;
    private StockRepository stockRepository;

    @BeforeEach
    public void setUp() {
        stockRepository = mock(StockRepository.class);
        statusServiceImp = mock(StatusServiceImp.class);
        userServiceImp = mock(UserServiceImp.class);
        equipmentServiceImp = mock(EquipmentServiceImp.class);
        stockServiceImp = new StockServiceImp(stockRepository);
    }
    private Stock createValidStock() {
        Stock stock = new Stock();
        stock.setId(1L);
        stock.setAdded_by(createValidUser());
        stock.setStatus(createValidStatus());
        stock.setEquipment(createValidEquipment());
        return stock;
    }
    private User createValidUser() {
        User user = new User();
        AddressEmail addressEmail=new AddressEmail("nouhaila@gmail.com");
        user.setEmail(addressEmail);
        Password password=new Password("nouha");
        user.setPassword(password);
        FullName fullName=new FullName("nouhaila","khaouti");
        user.setFull_name(fullName);
        return user;
    }

    private Status createValidStatus() {
        Status status = new Status();
        status.setId(1L);
        status.setName("Test Category");
        return status;
    }
    private Equipment createValidEquipment() {
        Equipment equipment = new Equipment();
        equipment.setId(1L);
        equipment.setName("Test equipement");
        return equipment;
    }

    @Test
    void testAddStockWhenValidThenSuccess() {

        Stock stock = createValidStock();
        Status status = createValidStatus();
        Equipment equipment = createValidEquipment();
        User user=createValidUser();
        when(userServiceImp.findById(1L)).thenReturn(Optional.of(user));
        when(statusServiceImp.findById(1L)).thenReturn(Optional.of(status));
        when(equipmentServiceImp.findById(1L)).thenReturn(Optional.of(equipment));
        when(stockRepository.save(stock)).thenReturn(stock);

        Stock stockRes = stockServiceImp.addStock(stock);
        assertEquals(stockRes, stock);
    }

    @Test
    void testAddStockWhenUserNotFoundThenThrowNotFoundException(){
        Stock stock = createValidStock();
        when(userServiceImp.findById(1L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,stockServiceImp.addStock(stock));
    }

    @Test
    void testAddStockWhenEquipmentNotFoundThenThrowNotFoundException(){
        Stock stock = createValidStock();
        User user=createValidUser();
        when(userServiceImp.findById(1L)).thenReturn(Optional.of(user));
        when(equipmentServiceImp.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EquipmentNotFoundException.class,stockServiceImp.addStock(stock));
    }
    @Test
    void testAddStockWhenCategoryNotFoundThenThrowNotFoundException(){
        Stock stock = createValidStock();
        Equipment equipment = createValidEquipment();
        User user=createValidUser();
        when(userServiceImp.findById(1L)).thenReturn(Optional.of(user));
        when(equipmentServiceImp.findById(1L)).thenReturn(Optional.of(equipment));
        when(statusServiceImp.findById(1L)).thenReturn(Optional.empty());
        assertThrows(StatusNotFoundException.class,stockServiceImp.addStock(stock));
    }



    @Test
    public void testSaveStockWhenNullStockThenThrowException() {
        assertThrows(NullPointerException.class, (Executable) stockServiceImp.addStock(null));
    }

    @Test
    void updateStock() {
    }

    @Test
    void deleteStock() {
    }
}