package com.howtodoinjava.junit.params;

import java.util.stream.Stream;

import org.junit.jupiter.params.ArgumentCountValidationMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

// Fails on purpose; run with: mvn test -Dtest=ParameterizedMistakesDemo
class ParameterizedMistakesDemo {

    @ParameterizedTest(argumentCountValidation = ArgumentCountValidationMode.STRICT)
    @CsvSource({"10001, 94105"})
    void tooManyColumns(String zip) {
        assertTrue(new ZipCodeValidator().isValid(zip));
    }

    static Stream<String> zipsFromEmptyImport() {
        return Stream.empty();
    }

    @ParameterizedTest
    @MethodSource("zipsFromEmptyImport")
    void noArguments(String zip) {
        assertTrue(new ZipCodeValidator().isValid(zip));
    }
}
