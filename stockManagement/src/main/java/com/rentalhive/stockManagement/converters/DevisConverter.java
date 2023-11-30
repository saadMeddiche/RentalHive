package com.rentalhive.stockManagement.converters;

import com.rentalhive.stockManagement.dto.devisDtos.request.DevisRequestAddDto;
import com.rentalhive.stockManagement.dto.devisDtos.response.DevisResponseDto;
import com.rentalhive.stockManagement.embeddables.Discount;
import com.rentalhive.stockManagement.entities.Demande;
import com.rentalhive.stockManagement.entities.Devis;
import com.rentalhive.stockManagement.services.DemandeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;

@Component
public class DevisConverter {

    private final DemandeService demandeService;


    @Autowired
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

    public static DevisResponseDto convertToDto(Devis devis) throws Exception{

       DevisResponseDto devisResponseDto = new DevisResponseDto();

        devisResponseDto.setDemande(devis.getDemande());

        devisResponseDto.setPdf(encodeFileToBase64(devis.getPathPDF()));

        devisResponseDto.setPriceWithOutDiscount(devis.getPriceWithOutDiscount());

        devisResponseDto.setStatus(devis.getStatus());

        return  devisResponseDto;
    }

    // Thanks to Khalid Fifel
    public static String encodeFileToBase64(String filepath) throws Exception {
        File file = new File(filepath);

        byte[] encoded = Files.readAllBytes(file.toPath());

        return Base64.getEncoder().encodeToString(encoded);
    }
}
