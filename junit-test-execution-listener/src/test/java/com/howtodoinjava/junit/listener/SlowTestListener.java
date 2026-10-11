package com.howtodoinjava.junit.listener;

import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.TestPlan;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SlowTestListener implements TestExecutionListener {

    private static final long SLOW_MILLIS = 200;

    private final Map<String, Long> startTimes = new ConcurrentHashMap<>();

    @Override
    public void testPlanExecutionStarted(TestPlan testPlan) {
        long tests = testPlan.countTestIdentifiers(TestIdentifier::isTest);
        System.out.println("[listener] test plan with " + tests + " tests started");
    }

    @Override
    public void executionStarted(TestIdentifier testIdentifier) {
        if (testIdentifier.isTest()) {
            startTimes.put(testIdentifier.getUniqueId(), System.nanoTime());
        }
    }

    @Override
    public void executionSkipped(TestIdentifier testIdentifier, String reason) {
        System.out.println("[listener] skipped " + testIdentifier.getDisplayName() + ": " + reason);
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult result) {
        Long start = startTimes.remove(testIdentifier.getUniqueId());
        if (!testIdentifier.isTest() || start == null) {
            return;
        }
        long millis = (System.nanoTime() - start) / 1_000_000;
        System.out.println("[listener] " + testIdentifier.getDisplayName() + " " + result.getStatus()
                + (millis > SLOW_MILLIS ? " SLOW " + millis + " ms" : ""));
    }

    @Override
    public void testPlanExecutionFinished(TestPlan testPlan) {
        System.out.println("[listener] test plan finished");
    }
}
