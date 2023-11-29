package com.rentalhive.stockManagement.converters;

import com.rentalhive.stockManagement.dto.devisDtos.request.DevisRequestAddDto;
import com.rentalhive.stockManagement.dto.devisDtos.response.DevisResponseDto;
import com.rentalhive.stockManagement.embeddables.Discount;
import com.rentalhive.stockManagement.entities.Demande;
import com.rentalhive.stockManagement.entities.Devis;
import com.rentalhive.stockManagement.services.DemandeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DevisConverter {

    private final DemandeService demandeService;



    public DevisConverter(DemandeService demandeService) {
        this.demandeService = demandeService;

    }

    public Devis convertToEntity(DevisRequestAddDto devisRequestAddDto){

        Devis devis = new Devis();

        Demande demande = demandeService.findById(devisRequestAddDto.getDemandeId());

        devis.setDemande(demande);

        devis.setDiscount(new Discount(devisRequestAddDto.getDiscount()));

        return devis;
    }

    public static DevisResponseDto convertToDto(Devis devis){

        ModelMapper modelMapper = new ModelMapper();

        DevisResponseDto devisResponseDto = modelMapper.map(devis, DevisResponseDto.class);

        return  devisResponseDto;
    }
}
