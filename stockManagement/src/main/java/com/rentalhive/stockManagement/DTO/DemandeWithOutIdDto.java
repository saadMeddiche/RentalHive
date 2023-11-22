package com.rentalhive.stockManagement.DTO;

import com.rentalhive.stockManagement.entities.Stock;
import com.rentalhive.stockManagement.entities.User;
import lombok.Data;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;


@Data
public class DemandeWithOutIdDto {

    @NotNull(message = "The renter can not be null")
    private User renter;

    @NotEmpty(message = "The description can not be empty")
    @NotNull(message = "The description can not be null")
    @NotBlank(message = "The description can not be blank")
    private String description;

    private Boolean accepted;

    // Can Be Null
    private User verified_by;

    // Can Be Null
    private LocalDateTime date_verification;

    @NotNull(message = "The date of verification can not be null")
    private LocalDateTime date_reservation;

    @NotNull(message = "The date of expiration can not be null")
    private LocalDateTime date_expiration;

    @NotNull(message = "The date of demande can not be null")
    private LocalDateTime date_demande;

    private List<Stock> stocks;
}
