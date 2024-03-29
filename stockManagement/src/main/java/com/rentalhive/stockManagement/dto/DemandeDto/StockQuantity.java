package com.rentalhive.stockManagement.dto.DemandeDto;

import lombok.Data;

@Data
public class StockQuantity {

    private Integer quantity;

    private Long equipmentId;

    public StockQuantity(Integer quantity, Long equipmentId){
        this.quantity = quantity;
        this.equipmentId = equipmentId;
    }

}
