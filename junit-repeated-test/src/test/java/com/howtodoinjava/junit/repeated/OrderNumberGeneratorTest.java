package com.howtodoinjava.junit.repeated;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderNumberGeneratorTest {

    private OrderNumberGenerator generator;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        generator = new OrderNumberGenerator();
        System.out.println("@BeforeEach -> " + testInfo.getDisplayName());
    }

    @RepeatedTest(3)
    void startsAtOne() {
        long first = generator.next();
        System.out.println("  test       -> first number " + first);
        assertEquals(1, first);
    }

    @AfterEach
    void tearDown() {
        System.out.println("@AfterEach");
    }
}
