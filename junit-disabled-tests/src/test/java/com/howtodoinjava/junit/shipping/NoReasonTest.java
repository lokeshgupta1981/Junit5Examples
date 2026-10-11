package com.howtodoinjava.junit.shipping;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NoReasonTest {

  private final ShippingCalculator calculator = new ShippingCalculator();

  @Test
  @Disabled
  void heavyParcel() {
    assertEquals(1999, calculator.domesticRate(6000));
  }

  @ParameterizedTest
  @ValueSource(ints = {200, 500, 900})
  @Disabled("Rates for small parcels change on 1 November")
  void smallParcels(int weightInGrams) {
    assertEquals(499, calculator.domesticRate(weightInGrams));
  }

  @Nested
  @Disabled("Pallet shipping is not offered yet")
  class Pallets {

    @Test
    void palletRate() {
      assertEquals(9900, calculator.domesticRate(80000));
    }
  }
}
