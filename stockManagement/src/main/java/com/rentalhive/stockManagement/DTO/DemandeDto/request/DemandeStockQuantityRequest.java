package com.rentalhive.stockManagement.dto.DemandeDto.request;
import com.rentalhive.stockManagement.dto.DemandeDto.StockQuantity;
import com.rentalhive.stockManagement.dto.DemandeDto.request.AddDemandeDto;
import lombok.Data;

import java.util.List;


@Data
public class DemandeStockQuantityRequest {
    private AddDemandeDto demandeDto;
    private List<StockQuantity> stockQuantities;
}
