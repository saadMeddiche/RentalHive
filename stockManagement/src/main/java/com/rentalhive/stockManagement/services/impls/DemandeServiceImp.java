package com.rentalhive.stockManagement.services.impls;

import java.util.List;

import com.rentalhive.stockManagement.entities.Demande;
import com.rentalhive.stockManagement.exceptions.costums.ValidationException;
import com.rentalhive.stockManagement.repositories.DemandeRepository;
import com.rentalhive.stockManagement.repositories.UserRepository;
import com.rentalhive.stockManagement.services.DemandeService;

public class DemandeServiceImp implements DemandeService {

    private DemandeRepository demandeRepository;

    public DemandeServiceImp(DemandeRepository demandeRepository) {

        this.demandeRepository = demandeRepository;
    }
    @Override
    public List<Demande> getAllDemandes() {
        return null;
    }

    @Override
    public Demande addDemande(Demande demande) {
            return demande;
    }

    @Override
    public Demande updateDemand(Demande demande) {
        return null;
    }

    @Override
    public void deleteDemand(Demande demande) {

    }

}
