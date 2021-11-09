package com.howtodoinjava.junit5.examples;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectPackages;

@SelectPackages("com.howtodoinjava.junit5.examples")
public class ExpectedExceptionTest 
{
	@Test
	void testExpectedException() {

		Assertions.assertThrows(NumberFormatException.class, () -> {
			Integer.parseInt("One");
		});

	}

	@Test
	void testExpectedExceptionWithSameType() {

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Integer.parseInt("One");
		});
	}
	
	/*@Test
	void testExpectedExceptionFail() {
	 
	  Assertions.assertThrows(IllegalArgumentException.class, () -> {
	    Integer.parseInt("1");
	  });
	 
	}*/
}
