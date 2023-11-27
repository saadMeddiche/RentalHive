package com.rentalhive.stockManagement.DTOs;

public class StatusDTO {
    private String name;
    public StatusDTO() {
    }

    public StatusDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}