package com.rentalhive.stockManagement.DTO;

import com.rentalhive.stockManagement.DTO.DemandeDto.DemandeDto;
import com.rentalhive.stockManagement.embeddables.Discount;
import lombok.Data;


@Data
public class DevisDto {
    private DemandeDto demandeDto;
    private String pathPDF;
    private Discount discount;
}
