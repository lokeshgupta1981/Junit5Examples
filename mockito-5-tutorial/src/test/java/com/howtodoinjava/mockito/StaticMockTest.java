package com.howtodoinjava.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

class StaticMockTest {

  LoanService service = new LoanService(mock(BookRepository.class), mock(Notifier.class));

  @Test
  void mockStaticMethod() {
    try (MockedStatic<LoanIds> ids = mockStatic(LoanIds.class)) {
      ids.when(LoanIds::next).thenReturn("42");

      String receipt = service.receipt("Dune");                   // "Loan 42 for Dune"

      assertEquals("Loan 42 for Dune", receipt);
      ids.verify(LoanIds::next);
    }

    String after = service.receipt("Dune");                       // random id again
    assertNotEquals("Loan 42 for Dune", after);
  }
}
