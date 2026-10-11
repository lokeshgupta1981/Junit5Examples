package com.howtodoinjava.junit.runner;

import com.howtodoinjava.junit.orders.ShippingJUnit4Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class RunWithJUnitCore {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ShippingJUnit4Test.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure);
        }
        System.out.println("Run: " + result.getRunCount()
                + ", failed: " + result.getFailureCount()
                + ", ignored: " + result.getIgnoreCount()
                + ", successful: " + result.wasSuccessful());
    }
}
