package com.rentalhive.stockManagement.services;

import org.mockito.Mockito;

import com.rentalhive.stockManagement.repositories.DemandeRepository;

public class DemandeServiceTest {

    DemandeRepository demandeRepository;

    public void setUpRepositoriesAndeventServiceImp(){
        demandeRepository = Mockito.mock(DemandeRepository.class);
    }
}
