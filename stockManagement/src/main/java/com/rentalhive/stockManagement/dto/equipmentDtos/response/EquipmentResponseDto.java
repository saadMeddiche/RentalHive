package com.rentalhive.stockManagement.dto.equipmentDtos.response;

import com.rentalhive.stockManagement.dto.categoryDtos.response.CategoryResponseDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class EquipmentResponseDto {

    protected String name;
    protected Double price_per_day;

    protected CategoryResponseDto category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice_per_day() {
        return price_per_day;
    }

    public void setPrice_per_day(Double price_per_day) {
        this.price_per_day = price_per_day;
    }

    public CategoryResponseDto getCategory_response_dto() {
        return category;
    }

    public void setCategory_response_dto(CategoryResponseDto category_response_dto) {
        this.category = category_response_dto;
    }
}
