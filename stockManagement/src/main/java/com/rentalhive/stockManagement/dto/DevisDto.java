package com.rentalhive.stockManagement.dto;

import com.rentalhive.stockManagement.dto.DemandeDto.response.DemandeDto;
import com.rentalhive.stockManagement.embeddables.Discount;
import lombok.Data;


@Data
public class DevisDto {
    private DemandeDto demandeDto;
    private String pathPDF;
    private Discount discount;
}
