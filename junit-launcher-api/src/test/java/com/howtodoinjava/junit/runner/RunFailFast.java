package com.howtodoinjava.junit.runner;

import org.junit.platform.engine.CancellationToken;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;
import static org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder.request;
import static org.junit.platform.launcher.core.LauncherExecutionRequestBuilder.executionRequest;

public class RunFailFast {

    public static void main(String[] args) {
        CancellationToken cancellationToken = CancellationToken.create();

        TestExecutionListener cancelOnFailure = new TestExecutionListener() {
            @Override
            public void executionFinished(TestIdentifier id, TestExecutionResult result) {
                if (id.isTest() && result.getStatus() == TestExecutionResult.Status.FAILED) {
                    cancellationToken.cancel();
                }
            }
        };

        LauncherDiscoveryRequest discoveryRequest = request()
                .selectors(
                        selectClass("com.howtodoinjava.junit.orders.RefundFailingTest"),
                        selectClass("com.howtodoinjava.junit.orders.OrderValidatorTest"))
                .build();

        SummaryGeneratingListener summaryListener = new SummaryGeneratingListener();
        Launcher launcher = LauncherFactory.create();
        launcher.execute(executionRequest(discoveryRequest)
                .listeners(cancelOnFailure, summaryListener)
                .cancellationToken(cancellationToken)
                .build());

        TestExecutionSummary summary = summaryListener.getSummary();
        System.out.println("Found " + summary.getTestsFoundCount()
                + ", started " + summary.getTestsStartedCount()
                + ", failed " + summary.getTestsFailedCount()
                + ", skipped " + summary.getTestsSkippedCount());
    }
}
