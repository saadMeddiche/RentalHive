package com.rentalhive.stockManagement;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;

public abstract class DynamicTestHelper {

    public <O, C extends RuntimeException> Stream<DynamicTest> DynamicTestStreamForAssertThrows(
            List<O> listOfObjects,
            Consumer<O> method, Class<C> exceptionClass) {

        return listOfObjects.stream().map(object -> DynamicTest.dynamicTest(object.toString(), () -> {
            assertThrows(exceptionClass, () -> {
                method.accept(object);
            });
        }));
    }

    public <O> Stream<DynamicTest> DynamicTestForAssertDoesNotThrow(List<O> listOfObjects, Consumer<O> method) {

        return listOfObjects.stream().map(object -> DynamicTest.dynamicTest(object.toString(), () -> {
            assertDoesNotThrow(() -> {
                method.accept(object);
            });
        }));
    }
}
