package com.rentalhive.stockManagement.DTO.DemandeDto;

import com.rentalhive.stockManagement.DTO.DemandeDto.AddDemandeDto;
import com.rentalhive.stockManagement.embeddables.StockQuantity;
import lombok.Data;

import java.util.List;

@Data
public class DemandeStockQuantityRequest {
    private AddDemandeDto demandeDto;
    private List<StockQuantity> stockQuantities;
}
