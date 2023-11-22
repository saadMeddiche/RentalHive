package com.rentalhive.stockManagement.entities;

import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.rentalhive.stockManagement.embeddables.AddressEmail;
import com.rentalhive.stockManagement.embeddables.FullName;
import com.rentalhive.stockManagement.embeddables.Password;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @Valid
    private FullName full_name;

    @NotEmpty(message = "The user name can not be empty")
    @NotNull(message = "The user name can not be null")
    @NotBlank(message = "The user name can not be blank")
    @Pattern(regexp = "^[^\\s]*$", message = "No space allowed in user name")
    private String user_name;

    @Embedded
    @Valid
    private AddressEmail email;

    @Embedded
    @Valid
    private Password password;

    @NotNull(message = "The date of creation of account can npt be null")
    private LocalDateTime date_creation_account;


    public User(){};
    public User(FullName full_name, String user_name, AddressEmail email, Password password, LocalDateTime date_creation_account) {
        this.full_name = full_name;
        this.user_name = user_name;
        this.email = email;
        this.password = password;
        this.date_creation_account = date_creation_account;
    }

    public User(String firstName, String middleName, String lastName, String user_name, String email,
            String password,
            LocalDateTime date_creation_account) {

        this.full_name = new FullName(firstName, middleName, lastName);
        this.user_name = user_name;
        this.email = new AddressEmail(email);
        this.password = new Password(password);
        this.date_creation_account = date_creation_account;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FullName getFull_name() {
        return this.full_name;
    }

    public void setFull_name(FullName full_name) {
        this.full_name = full_name;
    }

    public String getUser_name() {
        return this.user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public AddressEmail getEmail() {
        return this.email;
    }

    public void setEmail(AddressEmail email) {
        this.email = email;
    }

    public Password getPassword() {
        return this.password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public LocalDateTime getDate_creation_account() {
        return this.date_creation_account;
    }

    public void setDate_creation_account(LocalDateTime date_creation_account) {
        this.date_creation_account = date_creation_account;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", full_name='" + getFull_name() + "'" +
                ", user_name='" + getUser_name() + "'" +
                ", email='" + getEmail() + "'" +
                ", password='" + getPassword() + "'" +
                ", date_creation_account='" + getDate_creation_account() + "'" +
                "}";
    }

}
