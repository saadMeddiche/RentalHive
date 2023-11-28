package com.rentalhive.stockManagement.DTO.DemandeDto;

import com.rentalhive.stockManagement.entities.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class UpdateDemandeDto {

    private Boolean accepted;

    private String description;
}
