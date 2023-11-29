package com.rentalhive.stockManagement.entities;

import com.rentalhive.stockManagement.embeddables.Discount;
import  com.rentalhive.stockManagement.entities.Demande;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Devis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
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


    @OneToMany
    private List<Pictures> pictures;

    public enum Status {
        PENDING,
        ACCEPTED,
        REJECTED
    }
}
