package com.rentalhive.stockManagement.dto.equipmentDtos.request;

import com.rentalhive.stockManagement.entities.Equipment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class EquipmentRequestUpdateDto {

    protected String name;

    protected Double price_per_day;

    protected Long category_Id;

    public EquipmentRequestUpdateDto() {
    }

    public EquipmentRequestUpdateDto(String name, Double price_per_day, Long category_Id) {
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
