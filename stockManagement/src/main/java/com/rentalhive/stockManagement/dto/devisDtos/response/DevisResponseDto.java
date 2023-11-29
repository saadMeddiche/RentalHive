package com.rentalhive.stockManagement.dto.devisDtos.response;

import com.rentalhive.stockManagement.entities.Demande;
import com.rentalhive.stockManagement.entities.Devis;

public class DevisResponseDto {

    protected Demande demande;
    protected String pathPDF;
    protected Double priceWithOutDiscount;
    protected Double discount;

    protected Devis.Status status;


//    public DemandeResponseDto getDemande() {
//        return demande;
//    }
//
//    public void setDemande(DemandeResponseDto demande) {
//}
}