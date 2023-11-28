package com.rentalhive.stockManagement.DTO;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.rentalhive.stockManagement.dto.devisDtos.response.DevisResponseDto;

@Data
public class ContractDto {

    @NotNull(message="devis can't be null")
    private DevisResponseDto devisDto;

    @NotNull(message = "the contract file can't be null")
    @NotBlank(message = "the contract file can't be blank")
    private String path;
}
