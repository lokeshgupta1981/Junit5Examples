package com.howtodoinjava.junit.runner;

import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;
import static org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder.request;

public class RunQuick {

    public static void main(String[] args) {
        LauncherDiscoveryRequest discoveryRequest = request()
                .selectors(selectPackage("com.howtodoinjava.junit.orders"))
                .build();

        SummaryGeneratingListener listener = new SummaryGeneratingListener();
        LauncherFactory.create().execute(discoveryRequest, listener);
        long failed = listener.getSummary().getTestsFailedCount();
        System.out.println("failed = " + failed);
    }
}
