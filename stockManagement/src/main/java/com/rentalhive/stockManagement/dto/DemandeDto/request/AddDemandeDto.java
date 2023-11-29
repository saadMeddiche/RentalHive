package com.rentalhive.stockManagement.dto.DemandeDto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class AddDemandeDto {


    @NotEmpty(message = "The description can not be empty")
    @NotNull(message = "The description can not be null")
    @NotBlank(message = "The description can not be blank")
    private String description;

    @NotNull(message = "The date of verification can not be null")
    private LocalDateTime date_reservation;

    @NotNull(message = "The date of expiration can not be null")
    private LocalDateTime date_expiration;
}
