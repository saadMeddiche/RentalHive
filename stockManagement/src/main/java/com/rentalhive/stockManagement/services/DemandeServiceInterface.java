package com.rentalhive.stockManagement.services;

import java.util.List;

import com.rentalhive.stockManagement.entities.Demande;

public interface DemandeServiceInterface {

    public List<Demande> getAllDemandes();

    public Demande addDemande(Demande demande);

    public Demande updateDemand(Demande demande);

    public void deleteDemand(Demande demande);
}
