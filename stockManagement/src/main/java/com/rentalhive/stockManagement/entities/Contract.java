package com.rentalhive.stockManagement.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@AllArgsConstructor
@Builder
@Entity
@NoArgsConstructor
public class Contract {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Devis devis;

    @NotNull(message = "creation date can't be null")
    private LocalDateTime creation_Date;

    private String signature;

    @ManyToOne
    @NotNull(message = "user can't be null")
    private User created_by;

    @NotNull(message = "the contract file can't be null")
    @NotBlank(message = "the contract file can't be blank")
    private String path;

}
