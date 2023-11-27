package com.rentalhive.stockManagement.DTOs;

import com.rentalhive.stockManagement.embeddables.AddressEmail;
import com.rentalhive.stockManagement.embeddables.FullName;
import com.rentalhive.stockManagement.embeddables.Password;

import java.time.LocalDateTime;

public class UserDTO {
    private FullName full_name;

    private String user_name;

    private AddressEmail email;

    private Password password;

    private LocalDateTime date_creation_account;

    public UserDTO(FullName full_name, String user_name, AddressEmail email, Password password, LocalDateTime date_creation_account) {
        this.full_name = full_name;
        this.user_name = user_name;
        this.email = email;
        this.password = password;
        this.date_creation_account = date_creation_account;
    }

    public UserDTO() {
    }

    public FullName getFull_name() {
        return full_name;
    }

    public void setFull_name(FullName full_name) {
        this.full_name = full_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public AddressEmail getEmail() {
        return email;
    }

    public void setEmail(AddressEmail email) {
        this.email = email;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public LocalDateTime getDate_creation_account() {
        return date_creation_account;
    }

    public void setDate_creation_account(LocalDateTime date_creation_account) {
        this.date_creation_account = date_creation_account;
    }
}
