package com.rentalhive.stockManagement.dto.equipmentDtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import com.rentalhive.stockManagement.entities.Equipment;
import com.rentalhive.stockManagement.entities.User;

public class EquipmentAddDto {

    @NotBlank(message = "The name of equipment can not be empty")
    @NotNull(message = "The name of equipment can not be null")
    private String name;

    @Positive(message = "The rent can not be negative or zero")
    private Double price_per_day;

    @NotNull(message = "The id of user that added the equipment can not be null")
    private Long added_by_id;

    @NotNull(message = "The id of category of the equipment can not be null")
    private Long category_Id;

    public EquipmentAddDto() {
    }

    public EquipmentAddDto(String name, Double price_per_day, Long added_by_id, Long category_Id) {
        this.name = name;
        this.price_per_day = price_per_day;
        this.added_by_id = added_by_id;
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

    public Long getAdded_by_id() {
        return this.added_by_id;
    }

    public void setAdded_by_id(Long added_by_id) {
        this.added_by_id = added_by_id;
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
                ", added_by_id='" + getAdded_by_id() + "'" +
                ", category_Id='" + getCategory_Id() + "'" +
                "}";
    }
}
