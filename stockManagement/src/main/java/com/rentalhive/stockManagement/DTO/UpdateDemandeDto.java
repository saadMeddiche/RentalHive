package com.rentalhive.stockManagement.DTO;

import com.rentalhive.stockManagement.entities.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class UpdateDemandeDto {

    private Boolean accepted;
    // Can Be Null
    private User verified_by;

    // Can Be Null
    private LocalDateTime date_verification;

    @NotEmpty(message = "The description can not be empty")
    @NotNull(message = "The description can not be null")
    @NotBlank(message = "The description can not be blank")
    private String description;
}
