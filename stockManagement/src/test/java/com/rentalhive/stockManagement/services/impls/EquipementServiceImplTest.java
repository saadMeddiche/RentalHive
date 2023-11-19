package com.rentalhive.stockManagement.services.impls;

import com.rentalhive.stockManagement.embeddables.AddressEmail;
import com.rentalhive.stockManagement.embeddables.FullName;
import com.rentalhive.stockManagement.embeddables.Password;
import com.rentalhive.stockManagement.entities.Category;
import com.rentalhive.stockManagement.entities.Equipment;
import com.rentalhive.stockManagement.entities.User;
import com.rentalhive.stockManagement.repositories.CategoryRepository;
import com.rentalhive.stockManagement.repositories.EquipmentRepository;
import com.rentalhive.stockManagement.repositories.UserRepository;
import com.rentalhive.stockManagement.services.Exceptions.InvalidEquipmentException;
import com.rentalhive.stockManagement.services.Exceptions.UnknownCategoryException;
import com.rentalhive.stockManagement.services.Exceptions.UnknownUserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EquipementServiceImplTest {

    @Mock
    private EquipmentRepository equipmentRepository;
    @Mock
    private CategoryServiceImp categoryService;
    @Mock
    private UserServiceImp userService;

    @InjectMocks
    private EquipmentServiceImp equipmentService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveEquipementWithUnknownCategory(){
        LocalDateTime userRegistrationDate = LocalDateTime.of(2022, 4, 5, 0, 0, 0);
        User user=new User(new FullName("fname","lname"),"username",new AddressEmail("user@gmail.com"),new Password("password"),userRegistrationDate);
        Category category= new Category(1L,"User");
        Equipment equipment=new Equipment("name",50.30,user,category);

        when(categoryService.find(category)).thenThrow(UnknownCategoryException.class);
        when(userService.find(user)).thenReturn(user);
        doNothing().when(equipmentRepository).save(equipment);

       assertThrows(UnknownCategoryException.class, () -> equipmentService.addEquipment(equipment));
    }

    @Test
    public void testSaveEquipementWithUnknownUser(){
        LocalDateTime userRegistrationDate = LocalDateTime.of(2022, 4, 5, 0, 0, 0);
        User user=new User(new FullName("fname","lname"),"username",new AddressEmail("user@gmail.com"),new Password("password"),userRegistrationDate);
        Category category= new Category(1L,"User");
        Equipment equipment=new Equipment("name",50.30,user,category);

        when(categoryService.find(category)).thenReturn(category);
        when(userService.find(user)).thenThrow(UnknownUserException.class);
        doNothing().when(equipmentRepository).save(equipment);

        assertThrows(UnknownUserException.class, () -> equipmentService.addEquipment(equipment));

        /*UnknownUserException exception = assertThrows(UnknownUserException.class,
                () -> equipmentService.addEquipment(equipment));
        assertEquals("this user doesn't exists", exception.getMessage());*/
    }


    @Test
    public void testSaveEquipmentWithBlankOrNullAttribute() {
        LocalDateTime userRegistrationDate = LocalDateTime.of(2022, 4, 5, 0, 0, 0);
        User user = new User(new FullName("fname", "lname"), "username", new AddressEmail("user@gmail.com"), new Password("password"), userRegistrationDate);
        Category category = new Category(1L, "User");

        // Test with blank or null name
        Equipment equipmentWithBlankName = new Equipment("", 50.30, user, category);
        Equipment equipmentWithNullName = new Equipment(null, 50.30, user, category);
        assertThrows(InvalidEquipmentException.class, () -> equipmentService.addEquipment(equipmentWithBlankName));
        assertThrows(InvalidEquipmentException.class, () -> equipmentService.addEquipment(equipmentWithNullName));

        // Test with null price
        Equipment equipmentWithNullPrice = new Equipment("name", null, user, category);
        assertThrows(InvalidEquipmentException.class, () -> equipmentService.addEquipment(equipmentWithNullPrice));

        // Test with null user
        Equipment equipmentWithNullUser = new Equipment("name", 50.30, null, category);
        assertThrows(InvalidEquipmentException.class, () -> equipmentService.addEquipment(equipmentWithNullUser));

        // Test with blank or null category
        Equipment equipmentWithNullCategory = new Equipment("name", 50.30, user, null);
        assertThrows(InvalidEquipmentException.class, () -> equipmentService.addEquipment(equipmentWithNullCategory));
    }


}