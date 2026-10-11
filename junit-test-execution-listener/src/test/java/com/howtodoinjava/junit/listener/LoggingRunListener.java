package com.howtodoinjava.junit.listener;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class LoggingRunListener extends RunListener {

    @Override
    public void testRunStarted(Description description) {
        System.out.println("Tests to run: " + description.testCount());
    }

    @Override
    public void testStarted(Description description) {
        System.out.println("Started:  " + description.getMethodName());
    }

    @Override
    public void testFailure(Failure failure) {
        System.out.println("Failed:   " + failure.getDescription().getMethodName() + " -> " + failure.getMessage());
    }

    @Override
    public void testIgnored(Description description) {
        System.out.println("Ignored:  " + description.getMethodName());
    }

    @Override
    public void testFinished(Description description) {
        System.out.println("Finished: " + description.getMethodName());
    }

    @Override
    public void testRunFinished(Result result) {
        System.out.println("Run: " + result.getRunCount() + ", failed: " + result.getFailureCount()
                + ", ignored: " + result.getIgnoreCount());
    }
}
