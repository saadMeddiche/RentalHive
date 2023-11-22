package com.rentalhive.stockManagement.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull(message = "The equipment can not be null")
    private Equipment equipment;

    @ManyToOne
    @NotNull(message = "The status can not be null")
    private Status status;


    @ManyToOne
    @NotNull(message = "The user that added the equipment in the stock can not be null")
    private User added_by;

    @NotNull(message = "The registrationNumber can not be null")
    @NotBlank(message = "the registration number can't be blank")
    private String registrationNumber;
    @ManyToMany(mappedBy = "stocks")
    @JoinTable(name = "demande_stock",
            joinColumns = @JoinColumn(name = "demande_id"),
            inverseJoinColumns = @JoinColumn(name = "stock_id"))
    private List<Demande> demandes;
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
