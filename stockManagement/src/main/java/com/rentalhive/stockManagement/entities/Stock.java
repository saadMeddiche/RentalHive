package com.rentalhive.stockManagement.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "The equipment can not be null")
    @ManyToOne
    private Equipment equipment;

    @NotNull(message = "The status can not be null")
    @ManyToOne
    private Status status;

    @NotNull(message = "The user that added the equipment in the stock can not be null")
    @ManyToOne
    private User added_by;

    @NotNull(message = "The registrationNumber can not be null")
    @NotBlank(message = "the registration number can't be blank")
    private String registrationNumber;

    public Stock() {
    }

    public Stock(Long id, Equipment equipment, Status status, User added_by, String registrationNumber) {
        this.id = id;
        this.equipment = equipment;
        this.status = status;
        this.added_by = added_by;
        this.registrationNumber = registrationNumber;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Equipment getEquipment() {
        return this.equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getAdded_by() {
        return this.added_by;
    }

    public void setAdded_by(User added_by) {
        this.added_by = added_by;
    }

    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", equipment='" + getEquipment() + "'" +
                ", status='" + getStatus() + "'" +
                ", added_by='" + getAdded_by() + "'" +
                ", registrationNumber='" + getRegistrationNumber() + "'" +
                "}";
    }

}
