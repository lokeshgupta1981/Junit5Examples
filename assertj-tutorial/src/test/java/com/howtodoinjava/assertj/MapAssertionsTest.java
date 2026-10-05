package com.howtodoinjava.assertj;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import java.util.Map;
import org.junit.jupiter.api.Test;

class MapAssertionsTest {

  @Test
  void maps() {
    Map<String, Integer> stock = Map.of("apple", 5, "banana", 3);

    assertThat(stock).hasSize(2).isNotEmpty();                       // passes
    assertThat(stock).containsKey("apple").doesNotContainKey("kiwi");// passes
    assertThat(stock).containsKeys("apple", "banana");               // passes
    assertThat(stock).containsOnlyKeys("banana", "apple");           // passes
    assertThat(stock).containsValue(3).doesNotContainValue(0);       // passes
    assertThat(stock).containsEntry("apple", 5);                     // passes
    assertThat(stock).contains(entry("apple", 5), entry("banana", 3)); // passes
    assertThat(stock).containsExactlyInAnyOrderEntriesOf(Map.of("banana", 3, "apple", 5)); // passes
    assertThat(stock).extractingByKey("apple").isEqualTo(5);         // passes
    assertThat(stock).allSatisfy((fruit, count) -> assertThat(count).isPositive()); // passes
  }
}
