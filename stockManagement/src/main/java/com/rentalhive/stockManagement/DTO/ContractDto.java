package com.rentalhive.stockManagement.DTO;

import com.rentalhive.stockManagement.entities.Devis;
import com.rentalhive.stockManagement.entities.User;
import lombok.Data;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class ContractDto {

    @NotNull(message="devis can't be null")
    private DevisDto devisDto;

    @NotNull(message = "the contract file can't be null")
    @NotBlank(message = "the contract file can't be blank")
    private String path;
}
