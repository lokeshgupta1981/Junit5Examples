package com.howtodoinjava.junit5.examples;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
 
public class AppTest {
	
	private AppTest(TestInfo testInfo) {
	    System.out.println("Working on test " + testInfo.getDisplayName());
	}
     
    @BeforeAll
    static void setup(){
        System.out.println("@BeforeAll executed");
    }
     
    @BeforeEach
    void setupThis(){
        System.out.println("@BeforeEach executed");
    }
     
    //@Tag("DEV")
    @Test
    void testCalAdd()
    {
        System.out.println("======testCalAdd EXECUTED=======");
        Assertions.assertEquals( 4 , Calculator.add(2, 2));
    }
    
    //@Tag("DEV")
    @Test
    void testCalSubtract()
    {
        System.out.println("======testCalSubtract EXECUTED=======");
        Assertions.assertEquals( 2 , Calculator.subtract(4, 2));
    }
    
    //@Tag("DEV")
    @Test
    void testCalMultiply()
    {
        System.out.println("======testCalMultiply EXECUTED=======");
        Assertions.assertEquals( 4 , Calculator.multiply(2, 2));
    }
     
    @Tag("PROD")
    @Disabled
    @Test
    void testCalcTwo()
    {
        System.out.println("======TEST TWO EXECUTED=======");
        Assertions.assertEquals( 6 , Calculator.add(2, 4));
    }
     
    @AfterEach
    void tearThis(){
        System.out.println("@AfterEach executed");
    }
     
    @AfterAll
    static void tear(){
        System.out.println("@AfterAll executed");
    }
}
