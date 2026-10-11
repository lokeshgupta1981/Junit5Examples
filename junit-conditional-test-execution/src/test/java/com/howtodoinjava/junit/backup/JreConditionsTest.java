package com.howtodoinjava.junit.backup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Gatherers;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledForJreRange;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.JRE;

class JreConditionsTest {

  @Test
  @EnabledOnJre(JRE.JAVA_25)
  void onlyOnJava25() {
    assertEquals(25, Runtime.version().feature());
  }

  @Test
  @EnabledOnJre(versions = 26)
  void onlyOnJava26() {
    assertEquals(26, Runtime.version().feature());
  }

  @Test
  @EnabledForJreRange(min = JRE.JAVA_21)
  void virtualThreadsAvailable() throws InterruptedException {
    Thread thread = Thread.ofVirtual().start(() -> { });
    thread.join();
    assertTrue(thread.isVirtual());
  }

  @Test
  @EnabledForJreRange(minVersion = 25, maxVersion = 27)
  void fromJava25To27() {
    assertTrue(Runtime.version().feature() >= 25);
  }

  @Test
  @DisabledForJreRange(max = JRE.JAVA_23, disabledReason = "Stream gatherers are final since Java 24")
  void groupsFilesIntoBatches() {
    List<List<String>> batches = Stream.of("a.txt", "b.txt", "c.txt").gather(Gatherers.windowFixed(2)).toList();
    assertEquals(List.of(List.of("a.txt", "b.txt"), List.of("c.txt")), batches);
  }
}
