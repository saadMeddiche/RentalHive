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

@Entity
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

    public Equipment() {
    }

    public Equipment(Long id, String name, Double price_per_day, User added_by, Category category) {
        this.id = id;
        this.name = name;
        this.price_per_day = price_per_day;
        this.added_by = added_by;
        this.category = category;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice_per_day() {
        return this.price_per_day;
    }

    public void setPrice_per_day(Double price_per_day) {
        this.price_per_day = price_per_day;
    }

    public User getAdded_by() {
        return this.added_by;
    }

    public void setAdded_by(User added_by) {
        this.added_by = added_by;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", price_per_day='" + getPrice_per_day() + "'" +
                ", added_by='" + getAdded_by() + "'" +
                ", category='" + getCategory() + "'" +
                "}";
    }
}
