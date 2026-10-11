package com.howtodoinjava.junit.repeated;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DisplayNameTest {

    private final OrderNumberGenerator generator = new OrderNumberGenerator();

    @RepeatedTest(2)
    void defaultName(TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName());
        assertTrue(generator.next() > 0);
    }

    @RepeatedTest(value = 2, name = RepeatedTest.LONG_DISPLAY_NAME)
    @DisplayName("Generate order number")
    void longName(TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName());
        assertTrue(generator.next() > 0);
    }

    @RepeatedTest(value = 2, name = "{displayName} - run {currentRepetition}/{totalRepetitions}")
    @DisplayName("Generate order number")
    void customName(TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName());
        assertTrue(generator.next() > 0);
    }
}
