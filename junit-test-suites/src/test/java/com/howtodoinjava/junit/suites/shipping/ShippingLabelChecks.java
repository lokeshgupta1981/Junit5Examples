package com.howtodoinjava.junit.suites.shipping;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShippingLabelChecks {

    @Test
    void printsCityInCapitals() {
        assertEquals("SHIP TO PUNE", new ShippingCost().label("Pune"));
    }
}
