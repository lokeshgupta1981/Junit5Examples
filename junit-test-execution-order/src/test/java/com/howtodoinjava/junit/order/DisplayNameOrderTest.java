package com.howtodoinjava.junit.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.DisplayName.class)
class DisplayNameOrderTest {

    @BeforeEach
    void printName(TestInfo info) {
        System.out.println("DisplayNameOrderTest > " + info.getDisplayName());
    }

    @Test
    @DisplayName("C - a member returns the book")
    void returnsBook() {
    }

    @Test
    @DisplayName("A - a librarian registers a book")
    void registersBook() {
    }

    @Test
    @DisplayName("B - a member borrows the book")
    void lendsBook() {
    }
}
