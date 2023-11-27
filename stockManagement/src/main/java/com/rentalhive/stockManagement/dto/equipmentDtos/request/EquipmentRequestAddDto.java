package com.rentalhive.stockManagement.dto.equipmentDtos.request;

import javax.validation.constraints.NotNull;

public class EquipmentRequestAddDto extends EquipmentRequestUpdateDto {

    private Long added_by_id;

    public EquipmentRequestAddDto() {
    }

    public EquipmentRequestAddDto(String name, Double price_per_day, Long added_by_id, Long category_Id) {
        this.name = name;
        this.price_per_day = price_per_day;
        this.added_by_id = added_by_id;
        this.category_Id = category_Id;
    }

    public Long getAdded_by_id() {
        return this.added_by_id;
    }

    public void setAdded_by_id(Long added_by_id) {
        this.added_by_id = added_by_id;
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
