
package com.rentalhive.stockManagement.converters;

import com.rentalhive.stockManagement.dto.DemandeDto.request.AddDemandeDto;
import com.rentalhive.stockManagement.dto.DemandeDto.response.DemandeDto;
import com.rentalhive.stockManagement.entities.Demande;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

@RequiredArgsConstructor
public class DemandeConverter {
    private final ModelMapper mapper;


    public Demande convertDtoToEntity(DemandeDto demandeDto) {
        return mapper.map(demandeDto, Demande.class);
    }

    public DemandeDto convertEntityToDto(Demande student) {
        return mapper.map(student, DemandeDto.class);
    }
}

