package com.rentalhive.stockManagement.services.impls;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotNull;

import com.rentalhive.stockManagement.entities.Demande;
import com.rentalhive.stockManagement.entities.User;
import com.rentalhive.stockManagement.repositories.DemandeRepository;

import liquibase.repackaged.org.apache.commons.lang3.ObjectUtils.Null;

public class DemandeServiceImpTest {

    // @Mock
    // DemandeRepository demandeRepository;

    // @InjectMocks
    // DemandeServiceImp demandeServiceImp;

    // @BeforeEach
    // public void setUpRepositoriesAndeventServiceImp() {
    // MockitoAnnotations.openMocks(this);
    // }

    public DemandeRepository demandeRepository;

    public DemandeServiceImp demandeServiceImp;

    @BeforeEach
    public void setUpRepositoryAndServiceOfDemande() {
        demandeRepository = mock(DemandeRepository.class);
        demandeServiceImp = new DemandeServiceImp(demandeRepository);
    }

    @Nested
    class addDemande {

        @Test
        public void whenDateVerificationIsNull_thenShouldNotThrowIllegalArgument() {

            User renter = new User();

            User verified_by = mock(User.class);

            String description = "Hello World !!";

            Boolean accepted = false;

            LocalDateTime date_verification = getDateTime(null);

            LocalDateTime date_reservation = getDateTime("2004-01-20T12:00:00");

            LocalDateTime date_expiration = getDateTime("2005-01-20T12:00:00");

            LocalDateTime date_demande = getDateTime("2004-01-01T12:00:00");

            Demande demande = new Demande(renter, description, accepted, verified_by, date_verification,
                    date_reservation,
                    date_expiration, date_demande);

            assertDoesNotThrow(() -> {
                demandeServiceImp.addDemande(demande);
            });
        }

        public void whenDateDescriptionIsNull_thenShouldThrowIllegalArgument() {

            User renter = new User();

            User verified_by = mock(User.class);

            String description = null;

            Boolean accepted = false;

            LocalDateTime date_verification = getDateTime(null);

            LocalDateTime date_reservation = getDateTime("2004-01-20T12:00:00");

            LocalDateTime date_expiration = getDateTime("2005-01-20T12:00:00");

            LocalDateTime date_demande = getDateTime("2004-01-01T12:00:00");

            Demande demande = new Demande(renter, description, accepted, verified_by, date_verification,
                    date_reservation,
                    date_expiration, date_demande);

           
        }
    }

    public LocalDateTime getDateTime(String date) {

        return (date != null) ? LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")) : null;
    }

}
