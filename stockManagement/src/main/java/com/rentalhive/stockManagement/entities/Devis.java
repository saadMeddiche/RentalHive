package com.rentalhive.stockManagement.entities;

import com.rentalhive.stockManagement.embeddables.Discount;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class Devis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "The demande can not be null")
    private Demande demande;

    @NotNull(message = "The path of PDF can not be null")
    @NotBlank(message ="The path of PDF can not be blank")
    private String pathPDF;

    @NotNull(message = "The price with discount of PDF can not be null")
    @Positive
    private Double priceWithOutDiscount;

    @NotNull(message="The discount can not be null")
    private Discount discount;

    @NotNull(message = "The status can not be null")
    private  Status status;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public String getPathPDF() {
        return pathPDF;
    }

    public void setPathPDF(String pathPDF) {
        this.pathPDF = pathPDF;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }


    public Double getPriceWithOutDiscount() {
        return priceWithOutDiscount;
    }

    public void setPriceWithOutDiscount(Double priceWithOutDiscount) {
        this.priceWithOutDiscount = priceWithOutDiscount;
    }

    public enum Status {
        PENDING,
        ACCEPTED,
        REJECTED
    }
}
