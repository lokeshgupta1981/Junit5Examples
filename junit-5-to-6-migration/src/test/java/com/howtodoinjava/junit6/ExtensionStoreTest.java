package com.howtodoinjava.junit6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(CallCounterExtension.class)
class ExtensionStoreTest {

  @Test
  void first() {
  }

  @Test
  void second() {
  }
}
