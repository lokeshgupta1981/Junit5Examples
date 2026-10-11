package com.howtodoinjava.junit.tags;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

// Not matched by Surefire's default includes; run with: mvn test -Dtest=BadTagCheck
class BadTagCheck {

    @Test
    @Tag("smoke test")
    void badTag() {
        assertTrue(true);
    }
}
