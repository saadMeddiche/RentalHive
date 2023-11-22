package com.rentalhive.stockManagement.embeddables;

import com.rentalhive.stockManagement.DTO.AddDemandeDto;
import lombok.Data;

import java.util.List;

@Data
public class DemandeStockQuantityRequest {
    private AddDemandeDto demandeDto;
    private List<StockQuantity> stockQuantities;
}
