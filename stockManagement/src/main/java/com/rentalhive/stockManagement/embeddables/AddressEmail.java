package com.rentalhive.stockManagement.embeddables;

import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class AddressEmail {

    @NotEmpty(message = "The address email can not be empty")
    @NotNull(message = "The address email can not be null")
    @NotBlank(message = "The address email can not be blank")
    @Email(message = "Invalid email format")
    private String addressEmail;

    public AddressEmail() {
    }

    public AddressEmail(String addressEmail) {
        this.addressEmail = addressEmail;
    }

}
