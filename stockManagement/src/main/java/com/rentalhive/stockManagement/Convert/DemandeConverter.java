/*
package com.rentalhive.stockManagement.Convert;

import com.rentalhive.stockManagement.DTO.DemandeDto.AddDemandeDto;
import com.rentalhive.stockManagement.entities.Demande;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

public class DemandeConverter extends AbstractConverter<Demande, AddDemandeDto>{
    private final ModelMapper mapper;

    public DemandeConverter(ModelMapper mapper) {
        mapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        this.mapper = mapper;
    }

    @Override
    public Demande convertDtoToEntity(AddDemandeDto studentDto) {
        return mapper.map(studentDto, Demande.class);
    }

    @Override
    public AddDemandeDto convertEntityToDto(Demande student) {
        return mapper.map(student, AddDemandeDto.class);
    }
}
*/
