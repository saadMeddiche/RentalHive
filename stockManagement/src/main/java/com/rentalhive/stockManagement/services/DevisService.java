package com.rentalhive.stockManagement.services;

import com.rentalhive.stockManagement.entities.Devis;

import java.util.List;

public interface DevisService {

    public List<Devis> getAllDevises();

    public Devis addDevis(Devis devis);

    // You need to only update the status or the discount
    public Devis updateDevis(Devis devis);
}
