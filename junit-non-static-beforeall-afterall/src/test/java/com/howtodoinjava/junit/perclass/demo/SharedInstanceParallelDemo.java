package com.howtodoinjava.junit.perclass.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

/**
 * With PER_CLASS and concurrent execution, all tests run on the same instance in different threads,
 * so shared fields need thread-safe types.
 * Run with: mvn test -Dtest=SharedInstanceParallelDemo -Djunit.jupiter.execution.parallel.enabled=true
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Execution(ExecutionMode.CONCURRENT)
class SharedInstanceParallelDemo {

  final Queue<String> checkouts = new ConcurrentLinkedQueue<>();     // not ArrayList

  @Test
  void checksOutEmma() {
    checkouts.add("Emma");
    System.out.println("Emma on " + Thread.currentThread().getName() + ", instance " + this.hashCode());
  }

  @Test
  void checksOutDune() {
    checkouts.add("Dune");
    System.out.println("Dune on " + Thread.currentThread().getName() + ", instance " + this.hashCode());
  }

  @AfterAll
  void verifyCheckouts() {
    System.out.println("@AfterAll: " + checkouts.size() + " checkouts in one shared instance");
    assertEquals(2, checkouts.size());
  }
}
