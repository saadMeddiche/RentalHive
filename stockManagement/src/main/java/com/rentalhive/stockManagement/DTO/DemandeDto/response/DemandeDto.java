package com.rentalhive.stockManagement.dto.DemandeDto.response;
import com.rentalhive.stockManagement.Enum.DemandeStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import com.rentalhive.stockManagement.dto.DemandeDto.StockDto;

@Data
public class DemandeDto {

    private String description;

    private Boolean accepted;

    private DemandeStatus status;

    private LocalDateTime date_reservation;

    private LocalDateTime date_expiration;

    private LocalDateTime date_demande;

    private List<StockDto> stocks;
}
