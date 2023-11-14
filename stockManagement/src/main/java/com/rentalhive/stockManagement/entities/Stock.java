package com.rentalhive.stockManagement.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Entity
@Data
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "The user name can not be null")
    private Equipment equipment;

    @NotNull(message = "The user name can not be null")
    private Category category;

    @NotNull(message = "The user name can not be null")
    private User added_by;

}
