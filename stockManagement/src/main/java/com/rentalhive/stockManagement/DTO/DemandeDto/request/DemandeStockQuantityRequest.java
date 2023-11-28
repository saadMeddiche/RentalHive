package com.rentalhive.stockManagement.DTO.DemandeDto.request;
import com.rentalhive.stockManagement.dto.DemandeDto.StockQuantity;
import lombok.Data;

import java.util.List;


@Data
public class DemandeStockQuantityRequest {
    private AddDemandeDto demandeDto;
    private List<StockQuantity> stockQuantities;
}
