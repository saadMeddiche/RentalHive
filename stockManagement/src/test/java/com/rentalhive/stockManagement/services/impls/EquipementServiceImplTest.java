package com.rentalhive.stockManagement.services.impls;

import com.example.test.domain.Equipement;
import com.example.test.repository.EquipementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EquipementServiceImplTest {

    @Mock
    private EquipementRepository equipementRepository;

    @InjectMocks
    private EquipementServiceImpl equipementService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveEquipementWithInvalidData(){
        Equipement equipement=new Equipement();
        User user = new User("john", "John", "Doe", "john@example.com", "password",role);
        User insertedUser = new User("john", "John", "Doe", "john@example.com", "password",role);

        insertedUser.setId(1L);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Stream.empty());
        doNothing().when(userRepository).save(user);
        assertDoesNotThrow(()->userService.register(user));
    }

}