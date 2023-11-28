package com.rentalhive.stockManagement.dto.DemandeDto;

import com.rentalhive.stockManagement.entities.Equipment;
import lombok.Data;


@Data
public class StockDto {
    private Equipment equipment;

    private String registrationNumber;
}
