package com.howtodoinjava.junit5.examples;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectPackages;

@SelectPackages("com.howtodoinjava.junit5.examples")
public class ExpectedExceptionTest 
{
	@Test
	void testExpectedException() {

		NumberFormatException thrown = Assertions.assertThrows(NumberFormatException.class, () -> {
			Integer.parseInt("One");
		}, "NumberFormatException was expected");
		
		Assertions.assertEquals("For input string: \"One\"", thrown.getMessage());
	}

	@Test
	void testExpectedExceptionWithParentType() {

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Integer.parseInt("One");
		});
	}
	
	/*@Test
	void testExpectedExceptionFail() {
	 
		NumberFormatException thrown = Assertions
					.assertThrows(NumberFormatException.class, () -> {
						Integer.parseInt("1");
					}, "NumberFormatException error was expected");
		
		Assertions.assertEquals("Some expected message", thrown.getMessage());
	}*/
}
