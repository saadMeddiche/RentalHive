package com.rentalhive.stockManagement.services.impls;

import java.util.List;

import com.rentalhive.stockManagement.embeddables.StockQuantity;
import com.rentalhive.stockManagement.entities.Demande;
import com.rentalhive.stockManagement.repositories.DemandeRepository;
import com.rentalhive.stockManagement.services.DemandeService;
import com.rentalhive.stockManagement.services.helpers.DemandeServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemandeServiceImp extends DemandeServiceHelper implements DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;

    public DemandeServiceImp(DemandeRepository demandeRepository) {
        super(demandeRepository);
    }
    @Override
    public List<Demande> getAllDemandes() {
        return demandeRepository.findAll();
    }

    @Override
    public Demande addDemande(Demande demande, List<StockQuantity> stockQuantities) {
        Demande Ndemande=validateDemandeOnAdding(demande,stockQuantities);

        return demandeRepository.save(Ndemande);
    }

    @Override
    public Demande updateDemand(Demande demande, List<StockQuantity> stockQuantities) {
        Demande Ndemande=validateDemandeOnAdding(demande,stockQuantities);
        return demandeRepository.save(Ndemande);
    }

    @Override
    public void deleteDemand(Demande demande) {
           validateDemandeOnDeleting(demande);

        demandeRepository.delete(demande);
    }

}
