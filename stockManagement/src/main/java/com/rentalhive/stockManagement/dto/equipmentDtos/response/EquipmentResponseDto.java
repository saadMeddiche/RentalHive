package com.rentalhive.stockManagement.dto.equipmentDtos.response;

import com.rentalhive.stockManagement.dto.categoryDtos.response.CategoryResponseDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class EquipmentResponseDto {

    protected String name;
    protected Double price_per_day;

    protected CategoryResponseDto category;

}
