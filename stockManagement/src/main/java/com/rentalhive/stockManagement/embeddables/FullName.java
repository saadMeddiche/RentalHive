package com.rentalhive.stockManagement.embeddables;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Embeddable
public class FullName {

    @NotEmpty(message = "The first name can not be empty")
    @NotNull(message = "The first name can not be null")
    @NotBlank(message = "The first name can not be blank")
    @Pattern(regexp = "^[^\\s]*$", message = "No space allowed in first name")
    private String firstName;

    @Pattern(regexp = "^[^\\s]*$", message = "No Space Allowed in middle name")
    private String middleName;

    @NotEmpty(message = "The last name can not be empty")
    @NotNull(message = "The last name can not be null")
    @NotBlank(message = "The last name can not be blank")
    @Pattern(regexp = "^[^\\s]*$", message = "No Space Allowed in last name")
    private String lastName;

    public FullName() {
    }

    public FullName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public FullName(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "{" +
                " firstName='" + getFirstName() + "'" +
                ", middleName='" + getMiddleName() + "'" +
                ", lastName='" + getLastName() + "'" +
                "}";
    }
}
