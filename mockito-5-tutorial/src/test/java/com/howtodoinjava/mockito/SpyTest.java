package com.howtodoinjava.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

class SpyTest {

  @Test
  void mockVsSpy() {
    FeeCalculator mocked = mock(FeeCalculator.class);             // final class, works in Mockito 5
    FeeCalculator spied = spy(new FeeCalculator());

    int mockFee = mocked.lateFee(3);                              // 0, a mock runs no real code
    int spyFee = spied.lateFee(3);                                // 6, the real method: 3 days * 2

    doReturn(5).when(spied).perDay();                             // replace only perDay()
    int newFee = spied.lateFee(3);                                // 15, real lateFee() calls stubbed perDay()

    assertEquals(0, mockFee);
    assertEquals(6, spyFee);
    assertEquals(15, newFee);
  }

  @Test
  void whenOnSpyCallsRealMethod() {
    FeeCalculator calculator = spy(new FeeCalculator());
    when(calculator.lateFee(3)).thenReturn(100);                  // real lateFee(3) runs once here

    int fee = calculator.lateFee(3);                              // 100

    assertEquals(100, fee);
    verify(calculator, times(2)).lateFee(3);                      // 2 calls, one from when()
  }
}
