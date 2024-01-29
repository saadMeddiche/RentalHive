package com.rentalhive.stockManagement.services;

import java.time.LocalDateTime;
import java.util.List;

import com.rentalhive.stockManagement.dto.DemandeDto.StockQuantity;
import com.rentalhive.stockManagement.entities.Demande;
import org.springframework.data.jpa.repository.Query;

public interface DemandeService {

    public List<Demande> getAllDemandes();

    public Demande addDemande(Demande demande, List<StockQuantity> stockQuantities );

    public Demande updateDemand(Demande demande);

    public void deleteDemand(Demande demande);

    public Demande findById(Long id);


}
