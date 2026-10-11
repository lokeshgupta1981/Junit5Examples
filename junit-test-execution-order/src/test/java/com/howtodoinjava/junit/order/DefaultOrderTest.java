package com.howtodoinjava.junit.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

class DefaultOrderTest {

    @BeforeEach
    void printName(TestInfo info) {
        System.out.println("DefaultOrderTest > " + info.getDisplayName());
    }

    @Test
    void registersBook() {
    }

    @Test
    void lendsBook() {
    }

    @Test
    void returnsBook() {
    }

    @Test
    void checksUnknownBook() {
    }
}
