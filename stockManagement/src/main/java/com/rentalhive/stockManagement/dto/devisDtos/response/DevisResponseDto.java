package com.rentalhive.stockManagement.dto.devisDtos.response;

import com.rentalhive.stockManagement.dto.DemandeDto.response.DemandeDto;
import com.rentalhive.stockManagement.entities.Demande;
import com.rentalhive.stockManagement.entities.Devis;

public class DevisResponseDto {

    protected DemandeDto demande;
    protected String pathPDF;
    protected Double priceWithOutDiscount;
    protected Double discount;

    protected Devis.Status status;

    public DemandeDto getDemande() {
        return demande;
    }

    public void setDemande(DemandeDto demande) {
        this.demande = demande;
    }

    public String getPathPDF() {
        return pathPDF;
    }

    public void setPathPDF(String pathPDF) {
        this.pathPDF = pathPDF;
    }

    public Double getPriceWithOutDiscount() {
        return priceWithOutDiscount;
    }

    public void setPriceWithOutDiscount(Double priceWithOutDiscount) {
        this.priceWithOutDiscount = priceWithOutDiscount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Devis.Status getStatus() {
        return status;
    }

    public void setStatus(Devis.Status status) {
        this.status = status;
    }
}