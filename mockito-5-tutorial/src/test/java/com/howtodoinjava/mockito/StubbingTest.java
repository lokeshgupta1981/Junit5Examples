package com.howtodoinjava.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StubbingTest {

  @Mock
  BookRepository repository;

  @Mock
  Notifier notifier;

  @InjectMocks
  LoanService service;

  @Test
  void defaultValuesAndThenReturn() {
    Optional<Book> before = repository.findByTitle("Dune");           // Optional.empty, not stubbed yet

    when(repository.findByTitle("Dune")).thenReturn(Optional.of(new Book("Dune", true)));

    Optional<Book> dune = repository.findByTitle("Dune");             // Optional[Book[title=Dune, available=true]]
    Optional<Book> emma = repository.findByTitle("Emma");             // Optional.empty, other argument

    assertTrue(before.isEmpty());
    assertEquals("Dune", dune.orElseThrow().title());
    assertTrue(emma.isEmpty());
  }

  @Test
  void consecutiveCalls() {
    when(repository.findByTitle("Dune"))
        .thenReturn(Optional.of(new Book("Dune", true)))
        .thenReturn(Optional.of(new Book("Dune", false)));

    boolean first = service.borrow("Lokesh", "Dune");             // true
    boolean second = service.borrow("Alex", "Dune");              // false, book is out

    assertTrue(first);
    assertFalse(second);
  }

  @Test
  void thenThrow() {
    when(repository.findByTitle("Dune")).thenThrow(new IllegalStateException("DB down"));

    IllegalStateException ex = assertThrows(IllegalStateException.class,
        () -> service.borrow("Lokesh", "Dune"));                  // DB down

    assertEquals("DB down", ex.getMessage());
  }

  @Test
  void doThrowForVoidMethod() {
    when(repository.findByTitle("Dune")).thenReturn(Optional.of(new Book("Dune", true)));
    doThrow(new IllegalStateException("Mail server down"))
        .when(notifier).send("Lokesh", "You borrowed Dune");

    IllegalStateException ex = assertThrows(IllegalStateException.class,
        () -> service.borrow("Lokesh", "Dune"));                  // Mail server down

    assertEquals("Mail server down", ex.getMessage());
  }
}
