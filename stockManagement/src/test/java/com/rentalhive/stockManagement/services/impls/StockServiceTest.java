package com.rentalhive.stockManagement.services.impls;

import com.rentalhive.stockManagement.embeddables.AddressEmail;
import com.rentalhive.stockManagement.embeddables.FullName;
import com.rentalhive.stockManagement.embeddables.Password;
import com.rentalhive.stockManagement.entities.*;
import com.rentalhive.stockManagement.exceptions.costums.AlreadyExistsException;
import com.rentalhive.stockManagement.exceptions.costums.DoNotExistsException;
import com.rentalhive.stockManagement.exceptions.costums.ValidationException;
import com.rentalhive.stockManagement.repositories.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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
        stock.setRegistrationNumber("3456GTJK");
        return stock;
    }

    private User createValidUser() {
        User user = new User();
        user.setId(1L);
        user.setUser_name("baouboula");
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
        when(stockRepository.findByRegistrationNumber(anyString())).thenReturn(Optional.empty());
        when(userServiceImp.findById(anyLong())).thenReturn(Optional.of(user));
        when(statusServiceImp.findById(anyLong())).thenReturn(Optional.of(status));
        when(equipmentServiceImp.findById(equipment)).thenReturn(Optional.empty());
        when(stockRepository.save(stock)).thenReturn(stock);

        Stock stockRes = stockServiceImp.addStock(stock);
        assertEquals(stockRes, stock);
    }

    @Test
    void testAddStockWhenUserNotFoundThenThrowNotFoundException(){
        Stock stock = createValidStock();
        when(stockRepository.findByRegistrationNumber(anyString())).thenReturn(Optional.empty());
        when(userServiceImp.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(DoNotExistsException.class, ()->stockServiceImp.addStock(stock),"user does not exist");
    }

    @Test
    void testAddStockWhenStockAlreadyExistThenThrowAlreadyExistException(){
        Stock stock = createValidStock();
        when(stockRepository.findByRegistrationNumber(anyString())).thenReturn(Optional.of(stock));
        assertThrows(DoNotExistsException.class, ()->stockServiceImp.addStock(stock),"stock doesn't exist");
    }

    @Test
    void testAddStockWhenEquipmentNotFoundThenThrowNotFoundException(){
        Stock stock = createValidStock();
        User user=createValidUser();
        Equipment equipment = createValidEquipment();
        when(stockRepository.findByRegistrationNumber(anyString())).thenReturn(Optional.empty());
        when(userServiceImp.findById(anyLong())).thenReturn(Optional.of(user));
        when(equipmentServiceImp.findById(equipment)).thenReturn(Optional.empty());
        assertThrows(DoNotExistsException.class, ()-> stockServiceImp.addStock(stock),"the equipment does not exist");
    }

    @Test
    void testAddStockWhenCategoryNotFoundThenThrowNotFoundException(){
        Stock stock = createValidStock();
        Equipment equipment = createValidEquipment();
        User user=createValidUser();
        when(userServiceImp.findById(1L)).thenReturn(Optional.of(user));
        when(equipmentServiceImp.findById(equipment)).thenReturn(Optional.of(equipment));
        when(statusServiceImp.findById(1L)).thenReturn(Optional.empty());
        assertThrows(DoNotExistsException.class, ()->stockServiceImp.addStock(stock),"category does not exist");
    }

    @ParameterizedTest
    @MethodSource("provideTestData")
    void testStockAttributesForNull(Stock stock, String nullField) throws IllegalAccessException {
        Field[] fields = stock.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
                if (field.getName().equals(nullField)) {
                    field.set(stock, null);
                }
        }
        assertThrows(ValidationException.class, () -> stockServiceImp.addStock(stock),"one of this stock fields is null");

    }
    @ParameterizedTest
    @MethodSource("provideTestData")
    void testStockAttributesAreBlank(Stock stock, String nullField) throws IllegalAccessException {
        Field[] fields = stock.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().equals(nullField)) {
                field.set(stock, "");
            }
        }
        assertThrows(ValidationException.class, () -> stockServiceImp.addStock(stock),"one of this stock fields is blank");
    }

    @Test
    void testUpdateStockWhenValidThenSuccess() {

        Stock stock = createValidStock();
        Status status = createValidStatus();
        Equipment equipment = createValidEquipment();
        User user=createValidUser();
        when(stockRepository.findByRegistrationNumber(anyString())).thenReturn(Optional.of(stock));
        when(userServiceImp.findById(anyLong())).thenReturn(Optional.of(user));
        when(statusServiceImp.findById(anyLong())).thenReturn(Optional.of(status));
        when(equipmentServiceImp.findById(equipment)).thenReturn(Optional.of(equipment));
        when(stockRepository.save(stock)).thenReturn(stock);

        Stock stockRes = stockServiceImp.addStock(stock);
        assertEquals(stockRes, stock);
    }

    @Test
    void testUpdateStockWhenUserNotFoundThenThrowNotFoundException(){
        Stock stock = createValidStock();
        when(stockRepository.findByRegistrationNumber(anyString())).thenReturn(Optional.of(stock));
        when(userServiceImp.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(DoNotExistsException.class, ()->stockServiceImp.updateStock(stock),"user does not exist");
    }

    @Test
    void testUpdateStockWhenStockNotFoundThenThrowAlreadyExistException(){
        Stock stock = createValidStock();
        when(stockRepository.findByRegistrationNumber(anyString())).thenReturn(Optional.empty());
        assertThrows(AlreadyExistsException.class, ()->stockServiceImp.updateStock(stock),"stock with the same registration name already exist");
    }

    @Test
    void testUpdateStockWhenEquipmentNotFoundThenThrowNotFoundException(){
        Stock stock = createValidStock();
        User user=createValidUser();
        Equipment equipment=createValidEquipment();
        when(stockRepository.findByRegistrationNumber(anyString())).thenReturn(Optional.of(stock));
        when(userServiceImp.findById(anyLong())).thenReturn(Optional.of(user));
        when(equipmentServiceImp.findById(equipment)).thenReturn(Optional.empty());
        assertThrows(DoNotExistsException.class, ()-> stockServiceImp.updateStock(stock),"the equipment does not exist");
    }

    @Test
    void testUpdateStockWhenCategoryNotFoundThenThrowNotFoundException(){
        Stock stock = createValidStock();
        Equipment equipment = createValidEquipment();
        User user=createValidUser();
        when(stockRepository.findByRegistrationNumber(anyString())).thenReturn(Optional.of(stock));
        when(userServiceImp.findById(1L)).thenReturn(Optional.of(user));
        when(equipmentServiceImp.findById(equipment)).thenReturn(Optional.of(equipment));
        when(statusServiceImp.findById(1L)).thenReturn(Optional.empty());
        assertThrows(DoNotExistsException.class, ()->stockServiceImp.updateStock(stock),"category does not exist");
    }

    @ParameterizedTest
    @MethodSource("provideTestData")
    void testUpdateStockAttributesForNull(Stock stock, String nullField) throws IllegalAccessException {
        Field[] fields = stock.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().equals(nullField)) {
                field.set(stock, null);
            }
        }
        assertThrows(ValidationException.class, () -> stockServiceImp.updateStock(stock),"one of this stock fields is null");

    }


    private  Stream<Arguments> provideTestData() {
        Stock stock = createValidStock();
        return Stream.of(
                Arguments.of(stock, "name"),
                Arguments.of(stock, "price"),
                Arguments.of(stock, "user"),
                Arguments.of(stock, "equipment")
        );
    }



}
