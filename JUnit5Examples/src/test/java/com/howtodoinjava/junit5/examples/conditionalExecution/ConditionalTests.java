package com.howtodoinjava.junit5.examples.conditionalExecution;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledForJreRange;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

public class ConditionalTests {
	@Test
	@EnabledOnOs(OS.MAC)
	void testOnMacOs() {
	    assertTrue(true);
	}

	@Test
	@DisabledOnOs(OS.WINDOWS)
	void doNotTestOnWindows() {
		assertTrue(true);
	}
	
	@Test
	@DisabledOnJre(JRE.JAVA_8)
	void onlyOnJava8() {
		assertTrue(true);
	}

	@Test
	@EnabledOnJre({ JRE.JAVA_17, JRE.JAVA_18 })
	void onJava17Or18() {
		assertTrue(true);
	}
	
	@Test
	@DisabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_11)
	void notFromJava8to11() {
		assertTrue(true);
	}
	
	@Test
	@EnabledForJreRange(min = JRE.JAVA_12, max = JRE.JAVA_18)
	void fromJava12to18() {
		assertTrue(true);
	}
	
	@Test
	@EnabledIfSystemProperty(named = "any.system.property", matches = "value-regex*")
	public void onlyIfPropertyValueIsFound() {
		assertTrue(true);
	}
	
	@Test
	@EnabledIfEnvironmentVariable(named = "ENV", matches = ".*development.*")
	public void executeOnlyInDevEnvironment() {
		assertTrue(true);
	}

	@Test
	@DisabledIfEnvironmentVariable(named = "ENV", matches = ".*prod.*")
	public void disabledOnProdEnvironment() {
		assertTrue(true);
	}
	
	@Test
	@EnabledIf("customConditionalFunction")
	void enabled() {
	    assertTrue(true);
	}

	@Test
	@DisabledIf("customConditionalFunction")
	void disabled() {
	    assertTrue(true);
	}

	boolean customConditionalFunction() {
	    return true;
	}
}
