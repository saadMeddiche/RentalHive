package com.rentalhive.stockManagement.services.impls;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Nested;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.validation.constraints.NotNull;

import com.rentalhive.stockManagement.entities.Demande;
import com.rentalhive.stockManagement.entities.User;
import com.rentalhive.stockManagement.exceptions.costums.ValidationException;
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

    @Nested
    class addDemande {

        User renter = new User();

        User verified_by = null;

        String description = "Hello World !!";

        Boolean accepted = false;

        LocalDateTime date_verification = getDateTime(null);

        LocalDateTime date_reservation = getDateTime("2004-01-20T12:00:00");

        LocalDateTime date_expiration = getDateTime("2005-01-20T12:00:00");

        LocalDateTime date_demande = getDateTime("2004-01-01T12:00:00");

        @BeforeEach
        public void setUpRepositoryAndServiceOfDemande() {
            demandeRepository = mock(DemandeRepository.class);
            demandeServiceImp = new DemandeServiceImp(demandeRepository);
            resetTheAttributes();
        }

        @Test
        public void whenAllIsGood_thenShouldNotThrowIllegalArgument() {
            assertDoesNotThrow(() -> {
                demandeServiceImp.addDemande(createDemande());
            });
        }

        @TestFactory
        public Stream<DynamicTest> whenAddingADemandeThatIsVerifiedFromTheBegining_thenShouldThrowValidationException() {

            List<Demande> demands = new ArrayList<>();

            verified_by = new User();
            date_verification = getDateTime("2004-01-01T12:00:00");
            accepted = true;

            demands.add(createDemande());

            verified_by = null;
            date_verification = getDateTime("2004-01-01T12:00:00");
            accepted = false;

            demands.add(createDemande());

            verified_by = new User();
            date_verification = null;
            accepted = true;

            demands.add(createDemande());

            return demands.stream().map(demand -> DynamicTest.dynamicTest(demand.toString(), () -> {
                assertThrows(ValidationException.class, () -> {
                    demandeServiceImp.addDemande(demand);
                });
            }));

        }

        @TestFactory
        public Stream<DynamicTest> whenSomeThingWrongWithTheDates_thenShouldThrowIllegalArgument() {

            List<Demande> demands = new ArrayList<>();

            // Reservation Is Higher Than Date Expiration
            date_expiration = getDateTime("2004-01-20T12:00:00");
            date_reservation = getDateTime("2005-01-20T12:00:00");
            demands.add(createDemande());

            // Date Demande Is Higher Than Date Reservation
            date_reservation = getDateTime("2004-01-20T12:00:00");
            date_expiration = getDateTime("2005-01-20T12:00:00");
            date_demande = getDateTime("2006-01-20T12:00:00");
            demands.add(createDemande());

            // Date Demande Is Between Date Reservation And Date Expiration
            date_reservation = getDateTime("2004-01-20T12:00:00");
            date_demande = getDateTime("2005-01-20T12:00:00");
            date_expiration = getDateTime("2006-01-20T12:00:00");
            demands.add(createDemande());

            // Date verification is higher than date reservation
            this.date_reservation = getDateTime("2004-01-20T12:00:00");
            this.date_expiration = getDateTime("2005-01-20T12:00:00");
            this.date_demande = getDateTime("2004-01-01T12:00:00");
            this.date_verification = getDateTime("2006-01-20T12:00:00");
            demands.add(createDemande());

            return demands.stream().map(demand -> DynamicTest.dynamicTest(demand.toString(), () -> {
                assertThrows(ValidationException.class, () -> {
                    demandeServiceImp.addDemande(demand);
                });
            }));
        }

        @TestFactory
        public Stream<DynamicTest> whenNonNullableAttributeAreNull_thenShouldThrowValidationException() {

            List<Demande> demands = new ArrayList<>();

            demands.add(null);

            description = null;
            demands.add(createDemande());
            resetTheAttributes();

            renter = null;
            demands.add(createDemande());
            resetTheAttributes();

            date_expiration = null;
            demands.add(createDemande());
            resetTheAttributes();

            date_reservation = null;
            demands.add(createDemande());
            resetTheAttributes();

            date_demande = null;
            demands.add(createDemande());
            resetTheAttributes();

            return demands.stream().map(demand -> DynamicTest.dynamicTest(demand.toString(), () -> {
                assertThrows(ValidationException.class, () -> {
                    demandeServiceImp.addDemande(demand);
                });
            }));

        }

        public void resetTheAttributes() {
            this.renter = new User();
            this.verified_by = null;
            this.description = "Hello World !!";
            this.accepted = false;
            this.date_verification = getDateTime(null);
            this.date_reservation = getDateTime("2004-01-20T12:00:00");
            this.date_expiration = getDateTime("2005-01-20T12:00:00");
            this.date_demande = getDateTime("2004-01-01T12:00:00");
        }

        public Demande createDemande() {
            return new Demande(renter, description, accepted, verified_by, date_verification, date_reservation,
                    date_expiration, date_demande);
        }
    }

    public LocalDateTime getDateTime(String date) {

        return (date != null) ? LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")) : null;
    }

}
