package com.rentalhive.stockManagement.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Demande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull(message = "The renter can not be null")
    private User renter;

    @NotEmpty(message = "The description can not be empty")
    @NotNull(message = "The description can not be null")
    @NotBlank(message = "The description can not be blank")
    private String description;

    private Boolean accepted;

    @ManyToOne
    // Can Be Null
    private User verified_by;

    // Can Be Null
    private LocalDateTime date_verification;

    @NotNull(message = "The date of verification can not be null")
    private LocalDateTime date_reservation;

    @NotNull(message = "The date of expiration can not be null")
    private LocalDateTime date_expiration;

    @NotNull(message = "The date of demande can not be null")
    private LocalDateTime date_demande;

    @OneToMany
    private List<Stock> stocks;

    public Demande() {

    }

    public Demande(User renter, String description, Boolean accepted, User verified_by,
            LocalDateTime date_verification, LocalDateTime date_reservation, LocalDateTime date_expiration,
            LocalDateTime date_demande) {
        this.renter = renter;
        this.description = description;
        this.accepted = accepted;
        this.verified_by = verified_by;
        this.date_verification = date_verification;
        this.date_reservation = date_reservation;
        this.date_expiration = date_expiration;
        this.date_demande = date_demande;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getRenter() {
        return this.renter;
    }

    public void setRenter(User renter) {
        this.renter = renter;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isAccepted() {
        return this.accepted;
    }

    public Boolean getAccepted() {
        return this.accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public User getVerified_by() {
        return this.verified_by;
    }

    public void setVerified_by(User verified_by) {
        this.verified_by = verified_by;
    }

    public LocalDateTime getDate_verification() {
        return this.date_verification;
    }

    public void setDate_verification(LocalDateTime date_verification) {
        this.date_verification = date_verification;
    }

    public LocalDateTime getDate_reservation() {
        return this.date_reservation;
    }

    public void setDate_reservation(LocalDateTime date_reservation) {
        this.date_reservation = date_reservation;
    }

    public LocalDateTime getDate_expiration() {
        return this.date_expiration;
    }

    public void setDate_expiration(LocalDateTime date_expiration) {
        this.date_expiration = date_expiration;
    }

    public LocalDateTime getDate_demande() {
        return this.date_demande;
    }

    public void setDate_demande(LocalDateTime date_demande) {
        this.date_demande = date_demande;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", renter='" + getRenter() + "'" +
                ", description='" + getDescription() + "'" +
                ", accepted='" + isAccepted() + "'" +
                ", verified_by='" + getVerified_by() + "'" +
                ", date_verification='" + getDate_verification() + "'" +
                ", date_reservation='" + getDate_reservation() + "'" +
                ", date_expiration='" + getDate_expiration() + "'" +
                ", date_demande='" + getDate_demande() + "'" +
                "}";
    }

}
