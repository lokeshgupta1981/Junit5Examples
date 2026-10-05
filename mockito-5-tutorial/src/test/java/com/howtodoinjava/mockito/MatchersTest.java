package com.howtodoinjava.mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.misusing.InvalidUseOfMatchersException;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MatchersTest {

  @Mock
  BookRepository repository;

  @Mock
  Notifier notifier;

  @InjectMocks
  LoanService service;

  @Test
  void matchers() {
    when(repository.findByTitle(anyString())).thenReturn(Optional.of(new Book("Any", true)));

    boolean dune = service.borrow("Lokesh", "Dune");              // true
    boolean emma = service.borrow("Lokesh", "Emma");              // true

    assertTrue(dune && emma);
    verify(notifier).send(eq("Lokesh"), contains("Dune"));        // eq() needed next to contains()
    verify(repository).save(argThat(book -> book.title().equals("Emma") && !book.available()));
  }

  @Test
  void mixingMatchersAndValues() {
    when(repository.findByTitle("Dune")).thenReturn(Optional.of(new Book("Dune", true)));
    service.borrow("Lokesh", "Dune");

    InvalidUseOfMatchersException ex = assertThrows(InvalidUseOfMatchersException.class,
        () -> verify(notifier).send("Lokesh", contains("Dune")));   // plain value next to a matcher
    System.out.println("----- Mixed matchers -----");
    System.out.println(ex);
  }
}
