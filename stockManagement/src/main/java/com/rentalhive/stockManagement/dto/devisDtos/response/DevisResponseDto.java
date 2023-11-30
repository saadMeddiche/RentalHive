package com.rentalhive.stockManagement.dto.devisDtos.response;

import com.rentalhive.stockManagement.dto.DemandeDto.response.DemandeDto;
import com.rentalhive.stockManagement.entities.Demande;
import com.rentalhive.stockManagement.entities.Devis;

public class DevisResponseDto {

    protected Demande demande;
    protected String pdf;
    protected Double priceWithOutDiscount;
    protected Double discount;

    protected Devis.Status status;

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pathPDF) {
        this.pdf = pathPDF;
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