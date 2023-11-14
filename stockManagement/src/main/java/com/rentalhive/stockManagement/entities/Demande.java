package com.rentalhive.stockManagement.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Demande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "The renter can not be null")
    private User renter;

    @NotEmpty(message = "The description can not be empty")
    @NotNull(message = "The description can not be null")
    @NotBlank(message = "The description can not be blank")
    private String description;

    private Boolean accepted;

    @NotNull(message = "The user that verified can not be null")
    private User verified_by;

    @NotNull(message = "The date of verification can not be null")
    private LocalDateTime date_verification;

    @NotNull(message = "The date of verification can not be null")
    private LocalDateTime date_reservation;

    @NotNull(message = "The date of expiration can not be null")
    private LocalDateTime date_expiration;

    @NotNull(message = "The date of demande can not be null")
    private LocalDateTime date_demande;

}