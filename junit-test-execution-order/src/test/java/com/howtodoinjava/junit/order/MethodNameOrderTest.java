package com.howtodoinjava.junit.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.MethodName.class)
class MethodNameOrderTest {

    @BeforeEach
    void printName(TestInfo info) {
        System.out.println("MethodNameOrderTest > " + info.getDisplayName());
    }

    @Test
    void step2LendsBook() {
    }

    @Test
    void step10ClosesAccount() {
    }

    @Test
    void step1RegistersBook() {
    }

    @Test
    void Step3ReturnsBook() {
    }
}
