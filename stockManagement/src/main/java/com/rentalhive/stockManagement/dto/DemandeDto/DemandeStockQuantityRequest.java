package com.rentalhive.stockManagement.dto.DemandeDto;

import lombok.Data;

import java.util.List;

@Data
public class DemandeStockQuantityRequest {
    private AddDemandeDto demandeDto;
    private List<StockQuantity> stockQuantities;
}
