package com.rentalhive.stockManagement.dto.devisDtos.request;

import com.rentalhive.stockManagement.entities.Devis;

public class DevisRequestUpdateDto {

    private Double discount;

    private Devis.Status status;

    public DevisRequestUpdateDto(Double discount, Devis.Status status) {
        this.discount = discount;
        this.status = status;
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
