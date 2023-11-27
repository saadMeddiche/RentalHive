package com.rentalhive.stockManagement.DTOs;

import com.rentalhive.stockManagement.dto.categoryDtos.response.CategoryResponseDto;
import com.rentalhive.stockManagement.entities.Category;
import com.rentalhive.stockManagement.entities.User;

public class EquipmentDTO {
    private String name;

    private Double price_per_day;
    private CategoryResponseDto category;

    public EquipmentDTO(String name, Double price_per_day, CategoryResponseDto category) {
        this.name = name;
        this.price_per_day = price_per_day;
        this.category = category;
    }

    public EquipmentDTO() {
    }

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

    public CategoryResponseDto getCategory() {
        return category;
    }

    public void setCategory(CategoryResponseDto category) {
        this.category = category;
    }
}
