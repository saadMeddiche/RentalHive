package com.rentalhive.stockManagement.entities;

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
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The name of status can not be empty")
    @NotNull(message = "The name of status can not be null")
    @NotBlank(message = "The name of status can not be blank")
    private String name;

    public Status(){}

    public Status(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
