package com.howtodoinjava.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CaptorTest {

  @Mock
  BookRepository repository;

  @Mock
  Notifier notifier;

  @InjectMocks
  LoanService service;

  @Captor
  ArgumentCaptor<Book> bookCaptor;

  @Test
  void captureSavedBook() {
    when(repository.findByTitle("Dune")).thenReturn(Optional.of(new Book("Dune", true)));

    service.borrow("Lokesh", "Dune");

    verify(repository).save(bookCaptor.capture());
    Book saved = bookCaptor.getValue();                           // Book[title=Dune, available=false]

    assertEquals("Dune", saved.title());
    assertFalse(saved.available());
  }

  @Test
  void captureWithoutAnnotation() {
    when(repository.findByTitle("Dune")).thenReturn(Optional.of(new Book("Dune", true)));
    ArgumentCaptor<String> message = ArgumentCaptor.forClass(String.class);

    service.borrow("Lokesh", "Dune");

    verify(notifier).send(eq("Lokesh"), message.capture());
    assertEquals("You borrowed Dune", message.getValue());
  }
}
