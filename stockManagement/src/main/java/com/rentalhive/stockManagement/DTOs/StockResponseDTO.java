package com.rentalhive.stockManagement.DTOs;


public class StockResponseDTO {
    private String registrationNumber;

    private StatusDTO status;

    private UserDTO added_by;
    private EquipmentDTO equipment;

    public StockResponseDTO(String registrationNumber, StatusDTO status, UserDTO added_by, EquipmentDTO equipment) {
        this.registrationNumber = registrationNumber;
        this.status = status;
        this.added_by = added_by;
        this.equipment = equipment;
    }

    public StockResponseDTO() {
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public StatusDTO getStatus() {
        return status;
    }

    public void setStatus(StatusDTO status) {
        this.status = status;
    }

    public UserDTO getAdded_by() {
        return added_by;
    }

    public void setAdded_by(UserDTO added_by) {
        this.added_by = added_by;
    }

    public EquipmentDTO getEquipment() {
        return equipment;
    }

    public void setEquipment(EquipmentDTO equipment) {
        this.equipment = equipment;
    }
}
