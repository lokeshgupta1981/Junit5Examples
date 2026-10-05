package com.howtodoinjava.mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VerifyTest {

  @Mock
  BookRepository repository;

  @Mock
  Notifier notifier;

  @InjectMocks
  LoanService service;

  @Test
  void verifyCalls() {
    when(repository.findByTitle("Dune")).thenReturn(Optional.of(new Book("Dune", true)));

    service.borrow("Lokesh", "Dune");

    verify(repository).findByTitle("Dune");                       // called once
    verify(repository, times(1)).save(new Book("Dune", false));   // same as verify(repository)
    verify(notifier).send("Lokesh", "You borrowed Dune");
    verifyNoMoreInteractions(repository, notifier);               // no other calls
  }

  @Test
  void verifyNever() {
    when(repository.findByTitle("Dune")).thenReturn(Optional.of(new Book("Dune", false)));

    service.borrow("Lokesh", "Dune");

    verify(repository, never()).save(any());                      // book was out, nothing saved
    verifyNoInteractions(notifier);                               // no message sent
  }
}
