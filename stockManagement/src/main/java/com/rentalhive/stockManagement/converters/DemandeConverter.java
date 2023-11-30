
package com.rentalhive.stockManagement.converters;

import com.rentalhive.stockManagement.dto.DemandeDto.response.DemandeDto;
import com.rentalhive.stockManagement.entities.Demande;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

public class DemandeConverter {
    private final ModelMapper mapper;

   public  DemandeConverter( ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Demande convertDtoToEntity(DemandeDto demandeDto) {
        return mapper.map(demandeDto, Demande.class);
    }


    public DemandeDto convertEntityToDto(Demande demande) {
        return mapper.map(demande, DemandeDto.class);
    }

}

