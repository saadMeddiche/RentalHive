package com.rentalhive.stockManagement.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@Builder
public class Contract {


    @ManyToOne
    private Devis devis;

    @NotNull(message = "creation date can't be null")
    private LocalDateTime creation_Date;

    private String signature;

    @NotNull(message = "the contract file can't be null")
    @NotBlank(message = "the contract file can't be blank")
    private String path;
}
