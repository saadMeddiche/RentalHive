package com.rentalhive.stockManagement.dto.devisDtos.response;

import com.rentalhive.stockManagement.entities.Devis;

public class DevisResponseDto {

    //    protected DemandeResponseDto demande;
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