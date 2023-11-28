package com.rentalhive.stockManagement.dto.devisDtos.request;

public class DevisRequestAddDto {

    private Long demandeId;
    // should be between 0-100
    private Double discount;

    public DevisRequestAddDto() {
    }

    public DevisRequestAddDto(Long demandeId, Double discount) {
        this.demandeId = demandeId;
        this.discount = discount;
    }

    public Long getDemandeId() {
        return demandeId;
    }

    public void setDemandeId(Long demandeId) {
        this.demandeId = demandeId;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
