package com.rentalhive.stockManagement.DTO;

import com.rentalhive.stockManagement.entities.Equipment;
import com.rentalhive.stockManagement.entities.Status;
import com.rentalhive.stockManagement.entities.User;

public class StockDTO {
    private String registrationNumber;

    private Status status; //I don't take the status but I give it to the user

    private User added_by;
    private Equipment equipment;


    public StockDTO() {
    }

    public StockDTO(String registrationNumber, User added_by, Equipment equipment) {
        this.registrationNumber = registrationNumber;
        this.added_by = added_by;
        this.equipment = equipment;
    }

    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public User getAdded_by() {
        return added_by;
    }

    public void setAdded_by(User added_by) {
        this.added_by = added_by;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
