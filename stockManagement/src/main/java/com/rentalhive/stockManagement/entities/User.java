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

import lombok.Getter;
import lombok.Setter;
import lombok.Data;
@Entity
@Data
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

}
