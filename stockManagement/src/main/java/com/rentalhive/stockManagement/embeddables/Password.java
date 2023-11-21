package com.rentalhive.stockManagement.embeddables;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Embeddable;

@Embeddable
public class Password {

    @NotEmpty(message = "The password can not be empty")
    @NotNull(message = "The password can not be null")
    @NotBlank(message = "The password can not be blank")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(regexp = "^[^\\s]*$", message = "No Space Allowed")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, and one special character (@#$%^&+=)")
    private String hashed_password;

    public Password() {
    }

    public Password(String non_hashed_password) {
        this.hashed_password = hashPassword(non_hashed_password);
    }

    public String getHashed_password() {
        return this.hashed_password;
    }

    public void setHashed_password(String hashed_password) {
        this.hashed_password = hashed_password;
    }

    public boolean isHashedPasswordEqualsNonHashedPassword(String non_hashed_password) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        return bCryptPasswordEncoder.matches(hashed_password, non_hashed_password);
    }

    public String hashPassword(String non_hashed_password) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        String hashed_password = bCryptPasswordEncoder.encode(non_hashed_password);

        return hashed_password;
    }

    @Override
    public String toString() {
        return "{" +
                " hashed_password='" + getHashed_password() + "'" +
                "}";
    }
}
