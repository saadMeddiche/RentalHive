package com.rentalhive.stockManagement.dto.equipmentDtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class EquipmentUpdateDto {

    @NotBlank(message = "The name of equipment can not be empty")
    @NotNull(message = "The name of equipment can not be null")
    private String name;

    @Positive(message = "The rent can not be negative or zero")
    private Double price_per_day;

    @NotNull(message = "The id of category of the equipment can not be null")
    private Long category_Id;

    public EquipmentUpdateDto() {
    }

    public EquipmentUpdateDto(String name, Double price_per_day, Long category_Id) {
        this.name = name;
        this.price_per_day = price_per_day;
        this.category_Id = category_Id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice_per_day() {
        return this.price_per_day;
    }

    public void setPrice_per_day(Double price_per_day) {
        this.price_per_day = price_per_day;
    }

    public Long getCategory_Id() {
        return this.category_Id;
    }

    public void setCategory_Id(Long category_Id) {
        this.category_Id = category_Id;
    }

    @Override
    public String toString() {
        return "{" +
                ", name='" + getName() + "'" +
                ", price_per_day='" + getPrice_per_day() + "'" +
                ", category_Id='" + getCategory_Id() + "'" +
                "}";
    }
}
