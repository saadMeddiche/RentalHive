package com.rentalhive.stockManagement.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import com.rentalhive.stockManagement.entities.Demande;

public interface Testing {

    interface add {

        @BeforeEach
        void setUpRepositoryAndServiceOfDemande();

        @TestFactory
        Stream<DynamicTest> whenAllIsGood_thenShouldNotException();

        @TestFactory
        Stream<DynamicTest> whenAddingADemandeThatIsVerifiedFromTheBegining_thenShouldThrowValidationException();

        @TestFactory
        Stream<DynamicTest> whenSomeThingWrongWithTheDates_thenShouldThrowIllegalArgument();

        @TestFactory
        Stream<DynamicTest> whenNonNullableAttributeAreNull_thenShouldThrowValidationException();

        void resetTheAttributes();

        List<Demande> createTestsDemandsForDatesValidation();

        List<Demande> createTestsDemandsForNullValidation();
    }

    interface update {

        @BeforeEach
        void setUpRepositoryAndServiceOfDemande();

        @TestFactory
        Stream<DynamicTest> whenAllIsGood_thenShouldNotException();

        @TestFactory
        Stream<DynamicTest> whenSomeThingWrongWithTheDates_thenShouldThrowIllegalArgument();

        @TestFactory
        Stream<DynamicTest> whenNonNullableAttributeAreNull_thenShouldThrowValidationException();

        void resetTheAttributes();

        List<Demande> createTestsDemandsForDatesValidation();

        List<Demande> createTestsDemandsForNullValidation();

    }

    interface delete {

    }

    LocalDateTime getDateTime(String date);

    Demande createDemande();
}
