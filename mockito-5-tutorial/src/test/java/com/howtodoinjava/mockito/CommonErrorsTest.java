package com.howtodoinjava.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.testkit.engine.EngineTestKit;
import org.junit.platform.testkit.engine.Events;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockMakers;
import org.mockito.MockedStatic;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.exceptions.misusing.PotentialStubbingProblem;
import org.mockito.exceptions.misusing.UnnecessaryStubbingException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

// Runs small example tests that fail on purpose with the JUnit testkit and prints
// the Mockito messages, so the build itself stays green.
class CommonErrorsTest {

  // Example tests that fail on purpose. Surefire skips static nested classes.

  @ExtendWith(MockitoExtension.class)
  static class UnusedStubExample {
    @Mock BookRepository repository;
    @Mock Notifier notifier;
    @InjectMocks LoanService service;

    @Test
    void stubNeverUsed() {
      when(repository.findByTitle("Dune")).thenReturn(Optional.of(new Book("Dune", true)));
      when(repository.findByTitle("Emma")).thenReturn(Optional.empty());    // never used

      service.borrow("Lokesh", "Dune");
    }
  }

  @ExtendWith(MockitoExtension.class)
  static class ArgumentMismatchExample {
    @Mock BookRepository repository;
    @Mock Notifier notifier;
    @InjectMocks LoanService service;

    @Test
    void wrongArgument() {
      when(repository.findByTitle("Dune")).thenReturn(Optional.of(new Book("Dune", true)));

      service.borrow("Lokesh", "dune");                                     // lowercase title
    }
  }

  @ExtendWith(MockitoExtension.class)
  @MockitoSettings(strictness = Strictness.LENIENT)
  static class ArgumentMismatchLenientExample {
    @Mock BookRepository repository;
    @Mock Notifier notifier;
    @InjectMocks LoanService service;

    @Test
    void wrongArgumentWithoutStrictStubs() {
      when(repository.findByTitle("Dune")).thenReturn(Optional.of(new Book("Dune", true)));

      service.borrow("Lokesh", "dune");                                     // mock returns Optional.empty
    }
  }

  // Fixed versions

  @ExtendWith(MockitoExtension.class)
  static class LenientStubExample {
    @Mock BookRepository repository;
    @Mock Notifier notifier;
    @InjectMocks LoanService service;

    @Test
    void lenientStub() {
      when(repository.findByTitle("Dune")).thenReturn(Optional.of(new Book("Dune", true)));
      lenient().when(repository.findByTitle("Emma")).thenReturn(Optional.empty());   // may stay unused

      service.borrow("Lokesh", "Dune");
    }
  }

  @ExtendWith(MockitoExtension.class)
  @MockitoSettings(strictness = Strictness.LENIENT)
  static class LenientClassExample {
    @Mock BookRepository repository;

    @Test
    void stubsMayStayUnused() {
      when(repository.findByTitle(anyString())).thenReturn(Optional.of(new Book("Dune", true)));
      when(repository.findByTitle("Emma")).thenReturn(Optional.empty());
    }
  }

  private static Events run(Class<?> testClass) {
    return EngineTestKit.engine("junit-jupiter")
        .selectors(selectClass(testClass))
        .execute()
        .testEvents();
  }

  private static Throwable failureOf(Class<?> testClass) {
    return run(testClass).failed().stream()
        .findFirst()
        .flatMap(e -> e.getPayload(TestExecutionResult.class))
        .flatMap(TestExecutionResult::getThrowable)
        .orElse(null);
  }

  @Test
  void unnecessaryStubbing() {
    Throwable error = failureOf(UnusedStubExample.class);
    System.out.println("----- UnusedStubExample -----");
    System.out.println(error);

    assertInstanceOf(UnnecessaryStubbingException.class, error);
  }

  @Test
  void potentialStubbingProblem() {
    Throwable error = failureOf(ArgumentMismatchExample.class);
    System.out.println("----- ArgumentMismatchExample -----");
    System.out.println(error);

    assertInstanceOf(PotentialStubbingProblem.class, error);
  }

  @Test
  void mismatchWithoutStrictStubs() {
    Throwable error = failureOf(ArgumentMismatchLenientExample.class);
    System.out.println("----- ArgumentMismatchLenientExample -----");
    System.out.println(error);

    assertInstanceOf(IllegalArgumentException.class, error);
  }

  @Test
  void staticMockNotClosed() {
    MockedStatic<LoanIds> first = mockStatic(LoanIds.class);       // never closed
    try {
      MockitoException error = assertThrows(MockitoException.class, () -> mockStatic(LoanIds.class));
      System.out.println("----- Static mock not closed -----");
      System.out.println(error);
    } finally {
      first.close();
    }
  }

  @Test
  void lenientFixesBoth() {
    assertEquals(1, run(LenientStubExample.class).succeeded().count());
    assertEquals(1, run(LenientClassExample.class).succeeded().count());
  }

  @Test
  void finalClassWithSubclassMockMaker() {
    MockitoException error = assertThrows(MockitoException.class,
        () -> mock(FeeCalculator.class, withSettings().mockMaker(MockMakers.SUBCLASS)));
    System.out.println("----- Subclass mock maker -----");
    System.out.println(error);
  }

  @Test
  void finalClassWithDefaultMockMaker() {
    FeeCalculator calculator = mock(FeeCalculator.class);         // inline mock maker is the default
    when(calculator.lateFee(3)).thenReturn(9);

    int fee = calculator.lateFee(3);                              // 9

    assertEquals(9, fee);
  }
}
