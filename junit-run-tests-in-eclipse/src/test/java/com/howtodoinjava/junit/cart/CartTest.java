package com.howtodoinjava.junit.cart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Shopping cart")
class CartTest {

  private Cart cart;

  @BeforeEach
  void createCart() {
    cart = new Cart();
  }

  @Test
  @DisplayName("is empty when created")
  void newCartIsEmpty() {
    assertTrue(cart.isEmpty());
  }

  @Nested
  @DisplayName("when adding items")
  class AddingItems {

    @Test
    @DisplayName("sums the quantities of the same item")
    void sameItemTwice() {
      cart.add("apple", 2);
      cart.add("apple", 3);
      assertEquals(5, cart.quantityOf("apple"));
    }

    @ParameterizedTest(name = "rejects quantity {0}")
    @ValueSource(ints = {0, -1})
    void rejectsInvalidQuantity(int quantity) {
      assertThrows(IllegalArgumentException.class, () -> cart.add("banana", quantity));
    }
  }

  @Test
  @Tag("slow")
  @DisplayName("counts all items")
  void countsAllItems() {
    cart.add("apple", 2);
    cart.add("banana", 1);
    assertEquals(3, cart.itemCount());
  }

  @Test
  @Disabled("Coupons are not implemented yet")
  void appliesCoupon() {
  }
}
