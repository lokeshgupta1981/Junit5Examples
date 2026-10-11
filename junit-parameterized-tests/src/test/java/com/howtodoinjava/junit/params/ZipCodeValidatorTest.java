package com.howtodoinjava.junit.params;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ZipCodeValidatorTest {

    static final List<String> SAMPLE_ZIPS = List.of("02134", "73301", "99501");

    private final ZipCodeValidator validator = new ZipCodeValidator();

    @BeforeEach
    void printName(TestInfo info) {
        System.out.println(info.getDisplayName());
    }

    @ParameterizedTest
    @ValueSource(strings = {"10001", "94105", "60614"})
    void acceptsFiveDigits(String zip) {
        assertTrue(validator.isValid(zip));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "1234", "123456", "abcde"})
    void rejectsInvalidInput(String zip) {
        assertFalse(validator.isValid(zip));
    }

    @ParameterizedTest
    @FieldSource("SAMPLE_ZIPS")
    void acceptsSampleZips(String zip) {
        assertTrue(validator.isValid(zip));
    }
}
