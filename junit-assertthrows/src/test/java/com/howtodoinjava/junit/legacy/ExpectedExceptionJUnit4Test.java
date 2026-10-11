package com.howtodoinjava.junit.legacy;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

// JUnit 4 test, run by the JUnit Vintage engine
public class ExpectedExceptionJUnit4Test {

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  @Test(expected = NumberFormatException.class)
  public void expectedAttribute() {
    int number = Integer.parseInt("One");
  }

  @Test
  public void testExpectedException() {
    expectedException.expect(NumberFormatException.class);
    expectedException.expectMessage("For input string: \"One\"");
    int number = Integer.parseInt("One");
  }

  @Test
  public void testExpectedExceptionWithParentType() {
    expectedException.expect(IllegalArgumentException.class);
    int number = Integer.parseInt("One");
  }
}
