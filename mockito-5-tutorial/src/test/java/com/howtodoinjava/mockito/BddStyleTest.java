package com.howtodoinjava.mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BddStyleTest {

  @Mock
  BookRepository repository;

  @Mock
  Notifier notifier;

  @InjectMocks
  LoanService service;

  @Test
  void lendsBook() {
    // given
    given(repository.findByTitle("Dune")).willReturn(Optional.of(new Book("Dune", true)));

    // when
    boolean borrowed = service.borrow("Lokesh", "Dune");

    // then
    assertTrue(borrowed);
    then(notifier).should().send("Lokesh", "You borrowed Dune");
  }
}
