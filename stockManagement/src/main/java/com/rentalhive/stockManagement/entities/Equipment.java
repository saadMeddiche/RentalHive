package com.rentalhive.stockManagement.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The name of equipment can not be empty")
    @NotNull(message = "The name of equipment can not be null")
    @NotBlank(message = "The name of equipment can not be blank")
    private String name;

    @Positive(message = "The rent can not be negative or zero")
    private Double price_per_day;

    @NotNull(message = "The user that added the equipment can not be null")
    @Valid
    private User added_by;

    @NotNull(message = "The category of the equipment can not be null")
    @Valid
    private Category category;
}
