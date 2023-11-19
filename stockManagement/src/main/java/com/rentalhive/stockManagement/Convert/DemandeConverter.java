package com.rentalhive.stockManagement.Convert;

import com.rentalhive.stockManagement.DTO.DemandeDto;
import com.rentalhive.stockManagement.entities.Demande;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

public class DemandeConverter extends AbstractConverter<Demande, DemandeDto>{
    private final ModelMapper mapper;

    public DemandeConverter(ModelMapper mapper) {
        mapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        this.mapper = mapper;
    }

    @Override
    public Demande convertDtoToEntity(DemandeDto studentDto) {
        return mapper.map(studentDto, Demande.class);
    }

    @Override
    public DemandeDto convertEntityToDto(Demande student) {
        return mapper.map(student, DemandeDto.class);
    }
}
