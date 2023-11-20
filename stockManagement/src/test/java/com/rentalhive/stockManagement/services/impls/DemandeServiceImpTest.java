package com.rentalhive.stockManagement.services.impls;

import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Nested;

import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.rentalhive.stockManagement.DynamicTestHelper;
import com.rentalhive.stockManagement.entities.Demande;
import com.rentalhive.stockManagement.entities.User;
import com.rentalhive.stockManagement.exceptions.costums.DateValidationException;
import com.rentalhive.stockManagement.exceptions.costums.ValidationException;
import com.rentalhive.stockManagement.repositories.DemandeRepository;
import com.rentalhive.stockManagement.services.Testing;

public class DemandeServiceImpTest extends DynamicTestHelper implements Testing {

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

    User renter;

    User verified_by;

    String description;

    Boolean accepted;

    LocalDateTime date_verification;

    LocalDateTime date_reservation;

    LocalDateTime date_expiration;

    LocalDateTime date_demande;

    @Nested
    class addDemande implements Testing.add {

        @BeforeEach
        public void setUpRepositoryAndServiceOfDemande() {
            demandeRepository = mock(DemandeRepository.class);
            demandeServiceImp = new DemandeServiceImp(demandeRepository);
            resetTheAttributes();
        }

        /**
         * Test case to verify that no Exception is thrown when all is good.
         *
         * @param None
         * @return None
         */
        @TestFactory
        public Stream<DynamicTest> whenAllIsGood_thenShouldNotException() {
            return DynamicTestForAssertDoesNotThrow(List.of(createDemande()), demandeServiceImp::addDemande);
        }

        /**
         * Generates a stream of dynamic tests for the scenario when adding a demande
         * that is verified from the beginning.
         * If the demande is verified by a user, has a verification date and is
         * accepted, then adding the demande should throw a ValidationException.
         *
         * @return A stream of dynamic tests
         */
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

            return DynamicTestStreamForAssertThrows(demands,
                    demandeServiceImp::addDemande,
                    ValidationException.class);

        }

        /**
         * Generate a stream of dynamic tests for when something is wrong with the
         * dates,
         * then throw an IllegalArgumentException.
         *
         * @return a stream of dynamic tests
         */
        @TestFactory
        public Stream<DynamicTest> whenSomeThingWrongWithTheDates_thenShouldThrowDateValidationException() {
            return DynamicTestStreamForAssertThrows(createTestsDemandsForDatesValidation(),
                    demandeServiceImp::addDemande,
                    DateValidationException.class);
        }

        /**
         * This function creates a stream of dynamic tests for the scenario when
         * non-nullable attributes are null,
         * and it expects a ValidationException to be thrown. It adds various Demande
         * objects to a list with null
         * attribute values, and then resets the attributes after each addition.
         * Finally, it maps each Demande to a
         * dynamic test and asserts that adding the Demande to the DemandeServiceImp
         * object throws a ValidationException.
         *
         * @return a stream of dynamic tests
         */
        @TestFactory
        public Stream<DynamicTest> whenNonNullableAttributeAreNull_thenShouldThrowValidationException() {

            return DynamicTestStreamForAssertThrows(createTestsDemandsForNullValidation(),
                    demandeServiceImp::addDemande,
                    ValidationException.class);

        }

        /**
         * Reset all the attributes of the nested class addDemande.
         *
         * @param None
         * @return None
         */
        public void resetTheAttributes() {
            renter = new User();
            verified_by = null;
            description = "Hello World !!";
            accepted = false;
            date_verification = getDateTime(null);
            date_reservation = getDateTime("2004-01-20T12:00:00");
            date_expiration = getDateTime("2005-01-20T12:00:00");
            date_demande = getDateTime("2004-01-01T12:00:00");
        }

        /**
         * Generates a list of Demande objects for dates validation testing.
         *
         * @return A list of Demande objects
         */
        public List<Demande> createTestsDemandsForDatesValidation() {

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

            return demands;
        }

        /**
         * Generates a list of test Demande objects for null validation.
         *
         * @return a list of Demande objects for null validation
         */
        public List<Demande> createTestsDemandsForNullValidation() {

            List<Demande> demands = new ArrayList<>();

            demands.add(null);

            // Demande With Null Description
            description = null;
            demands.add(createDemande());
            resetTheAttributes();

            // Demande With Null Renter
            renter = null;
            demands.add(createDemande());
            resetTheAttributes();

            // Demande With Null Date Expiration
            date_expiration = null;
            demands.add(createDemande());
            resetTheAttributes();

            // Demande With Null Date Reservation
            date_reservation = null;
            demands.add(createDemande());
            resetTheAttributes();

            // Demande With Null Date Demande
            date_demande = null;
            demands.add(createDemande());
            resetTheAttributes();

            return demands;
        }

    }

    @Nested
    class updateDemande implements Testing.update {

        @BeforeEach
        public void setUpRepositoryAndServiceOfDemande() {
            demandeRepository = mock(DemandeRepository.class);
            demandeServiceImp = new DemandeServiceImp(demandeRepository);
            resetTheAttributes();
        }

        /**
         * Test case to verify that no Exception is thrown when all is good.
         *
         * @param None
         * @return None
         */
        @TestFactory
        public Stream<DynamicTest> whenAllIsGood_thenShouldNotException() {
            return DynamicTestForAssertDoesNotThrow(List.of(createDemande()), demandeServiceImp::updateDemand);
        }

        /**
         * Generate a stream of dynamic tests for when something is wrong with the
         * dates,
         * then throw an IllegalArgumentException.
         *
         * @return a stream of dynamic tests
         */
        @TestFactory
        public Stream<DynamicTest> whenSomeThingWrongWithTheDates_thenShouldThrowDateValidationException() {
            return DynamicTestStreamForAssertThrows(createTestsDemandsForDatesValidation(),
                    demandeServiceImp::updateDemand,
                    DateValidationException.class);
        }

        /**
         * This function creates a stream of dynamic tests for the scenario when
         * non-nullable attributes are null,
         * and it expects a ValidationException to be thrown. It adds various Demande
         * objects to a list with null
         * attribute values, and then resets the attributes after each addition.
         * Finally, it maps each Demande to a
         * dynamic test and asserts that adding the Demande to the DemandeServiceImp
         * object throws a ValidationException.
         *
         * @return a stream of dynamic tests
         */
        @TestFactory
        public Stream<DynamicTest> whenNonNullableAttributeAreNull_thenShouldThrowValidationException() {

            return DynamicTestStreamForAssertThrows(createTestsDemandsForNullValidation(),
                    demandeServiceImp::addDemande,
                    ValidationException.class);

        }

        /**
         * Reset all the attributes of the nested class updateDemand.
         *
         * @param None
         * @return None
         */
        public void resetTheAttributes() {
            renter = new User();
            verified_by = new User();
            description = "Hello World !!";
            accepted = true;
            date_verification = getDateTime("2003-01-20T12:00:00");
            date_reservation = getDateTime("2004-01-20T12:00:00");
            date_expiration = getDateTime("2005-01-20T12:00:00");
            date_demande = getDateTime("2004-01-01T12:00:00");
        }

        /**
         * Generates a list of test demands for dates validation.
         *
         * @return a list of Demande objects
         */
        public List<Demande> createTestsDemandsForDatesValidation() {

            List<Demande> demands = new addDemande().createTestsDemandsForDatesValidation();

            // Date verification is higher than date reservation
            date_reservation = getDateTime("2004-01-20T12:00:00");
            date_expiration = getDateTime("2005-01-20T12:00:00");
            date_demande = getDateTime("2004-01-01T12:00:00");
            date_verification = getDateTime("2006-01-20T12:00:00");
            demands.add(createDemande());

            return demands;
        }

        /**
         * Generates a list of test demands for null validation.
         *
         * @return A list of Demande objects.
         */
        public List<Demande> createTestsDemandsForNullValidation() {

            List<Demande> demands = new addDemande().createTestsDemandsForNullValidation();

            demands.add(null);

            // Date Of Verification Is Null
            date_verification = null;
            demands.add(createDemande());
            resetTheAttributes();

            // The Admin Who verified the demande Is Null
            verified_by = null;
            demands.add(createDemande());
            resetTheAttributes();

            return demands;
        }

    }

    /**
     * Parses the given date string and returns a LocalDateTime object.
     *
     * @param date the date string to be parsed in the format
     *             "yyyy-MM-dd'T'HH:mm:ss"
     * @return a LocalDateTime object representing the parsed date, or null if the
     *         input string is null
     */
    public LocalDateTime getDateTime(String date) {

        return (date != null) ? LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")) : null;
    }

    /**
     * Create a Demande object with the attribute of nested class addDemande.
     *
     * @return The created Demande object.
     */
    public Demande createDemande() {
        return new Demande(renter, description, accepted, verified_by, date_verification, date_reservation,
                date_expiration, date_demande);
    }

}
