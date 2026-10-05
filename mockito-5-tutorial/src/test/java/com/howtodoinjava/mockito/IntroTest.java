package com.howtodoinjava.mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;

class IntroTest {

  @Test
  void quickReference() {
    // 1. Create mocks for the dependencies
    BookRepository repository = mock(BookRepository.class);
    Notifier notifier = mock(Notifier.class);

    // 2. Stub: tell the mock what to return
    when(repository.findByTitle("Dune")).thenReturn(Optional.of(new Book("Dune", true)));

    // 3. Call the real class under test
    LoanService service = new LoanService(repository, notifier);
    boolean borrowed = service.borrow("Lokesh", "Dune");          // true

    // 4. Check the result and the calls made on the mocks
    assertTrue(borrowed);
    verify(notifier).send("Lokesh", "You borrowed Dune");         // passes
  }
}
