package com.howtodoinjava.mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AnnotationsTest {

  @Mock
  BookRepository repository;

  @Mock
  Notifier notifier;

  @InjectMocks
  LoanService service;   // created with new LoanService(repository, notifier)

  @Test
  void lendsAvailableBook() {
    when(repository.findByTitle("Dune")).thenReturn(Optional.of(new Book("Dune", true)));

    boolean borrowed = service.borrow("Lokesh", "Dune");          // true

    assertTrue(borrowed);
    verify(repository).save(new Book("Dune", false));
  }

  @Test
  void refusesBookThatIsOut() {
    when(repository.findByTitle("Dune")).thenReturn(Optional.of(new Book("Dune", false)));

    boolean borrowed = service.borrow("Lokesh", "Dune");          // false

    assertFalse(borrowed);
  }
}
