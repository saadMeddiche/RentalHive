package com.rentalhive.stockManagement.DTOs;

import com.rentalhive.stockManagement.entities.Equipment;
import com.rentalhive.stockManagement.entities.Status;
import com.rentalhive.stockManagement.entities.User;

public class StockDTO {
    private String registrationNumber;
    private Long added_by_id;
    private Long equipment_id;


    public StockDTO() {
    }

    public StockDTO(String registrationNumber, Long added_by_id, Long equipment_id) {
        this.registrationNumber = registrationNumber;
        this.added_by_id = added_by_id;
        this.equipment_id = equipment_id;
    }

    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Long getAdded_by_id() {
        return added_by_id;
    }

    public void setAdded_by_id(Long added_by_id) {
        this.added_by_id = added_by_id;
    }

    public Long getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(Long equipment_id) {
        this.equipment_id = equipment_id;
    }

}
