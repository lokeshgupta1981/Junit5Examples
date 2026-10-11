package com.howtodoinjava.junit.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.Random.class)
class RandomOrderTest {

    @BeforeEach
    void printName(TestInfo info) {
        System.out.println("RandomOrderTest > " + info.getDisplayName());
    }

    @Test
    void searchesByAuthor() {
    }

    @Test
    void searchesByTitle() {
    }

    @Test
    void searchesByYear() {
    }

    @Test
    void searchesByGenre() {
    }
}
