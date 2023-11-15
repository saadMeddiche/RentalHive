package com.rentalhive.stockManagement.services.implementes;

import com.example.test.domain.Equipement;
import com.example.test.repository.EquipementRepository;
import com.example.test.service.EquipementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public interface EquipmentServiceImpl implements EquipmentService{
    @Autowired
    private EquipementRepository equipementRepository;

    @Override
    public void save(Equipement equipement) {
        return equipementRepository.save(equipement) ;
    }
    @Override
    public void save(Equipement equipement) {
        return equipementRepository.save(equipement) ;
    }

    @Override
    public void save(Equipement equipement) {
        return equipementRepository.save(equipement) ;
    }
}