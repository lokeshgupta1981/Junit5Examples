package com.howtodoinjava.junit5.examples;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestNonStaticBeforeAllAndAfterAll {

  private List<String> cart = new ArrayList<>();

  @BeforeAll
  void setupBeforeAll() {
    System.out.println("Setting up the shopping cart for all tests");
    cart.add("Item 1");
    cart.add("Item 2");
  }

  @AfterAll
  void cleanupAfterAll() {
    System.out.println("Cleaning up the shopping cart after all tests");
    cart.clear();
  }

  @RepeatedTest(10)
  @Execution(ExecutionMode.CONCURRENT)
  void testAddItemToCart() {
    cart.add("Item 3");
    // Test logic to add an item and assert its presence in the cart.
  }

  @RepeatedTest(10)
  @Execution(ExecutionMode.CONCURRENT)
  void testRemoveItemFromCart() {
    cart.remove("Item 1");
    cart.remove("Item 2");
    cart.remove("Item 3");
    // Test logic to remove an item and assert its absence in the cart.
  }
}