package com.rentalhive.stockManagement.services.impls;

import com.rentalhive.stockManagement.entities.Devis;
import com.rentalhive.stockManagement.repositories.DevisRepository;
import com.rentalhive.stockManagement.services.DevisService;

import java.util.List;

public class DevisServiceImp implements DevisService {

    DevisRepository devisRepository;

    public DevisServiceImp(DevisRepository devisRepository) {
        this.devisRepository = devisRepository;
    }

    @Override
    public List<Devis> getAllDevises() {
        return null;
    }

    @Override
    public Devis addDevis(Devis devis) {
        return null;
    }

    @Override
    public Devis updateDevis(Devis devis) {
        return null;
    }
}
