package com.rentalhive.stockManagement.dto.DemandeDto;

import com.rentalhive.stockManagement.dto.equipmentDtos.response.EquipmentResponseDto;
import com.rentalhive.stockManagement.entities.Equipment;
import lombok.Data;


@Data
public class StockDto {
    private EquipmentResponseDto equipment;

    private String registrationNumber;
}
