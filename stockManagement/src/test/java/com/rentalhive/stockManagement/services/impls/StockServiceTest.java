package com.rentalhive.stockManagement.services.impls;

import com.rentalhive.stockManagement.embeddables.AddressEmail;
import com.rentalhive.stockManagement.embeddables.FullName;
import com.rentalhive.stockManagement.embeddables.Password;
import com.rentalhive.stockManagement.entities.*;
import com.rentalhive.stockManagement.services.implementes.CategoryServiceImp;
import com.rentalhive.stockManagement.repositories.StockRepository;
import com.rentalhive.stockManagement.services.implementes.StatusServiceImp;
import com.rentalhive.stockManagement.services.implementes.StockServiceImp;
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
    private StockRepository stockRepository;

    @BeforeEach
    public void setUp() {
        stockRepository = mock(StockRepository.class);
        statusServiceImp = mock(StatusServiceImp.class);
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
        when(statusServiceImp.findById(1L)).thenReturn(Optional.of(status));
        when(stockRepository.save(stock)).thenReturn(stock);

        Stock stockRes = stockServiceImp.addStock(stock);
        assertEquals(stockRes, stock);
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