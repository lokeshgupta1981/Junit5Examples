package com.howtodoinjava.junit.runner;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TagFilter;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import static org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectMethod;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;
import static org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder.request;

public class RunCheckoutTests {

    public static void main(String[] args) {
        LauncherDiscoveryRequest byTag = request()
                .selectors(selectPackage("com.howtodoinjava.junit.orders"))
                .filters(TagFilter.includeTags("checkout"))
                .build();

        LauncherDiscoveryRequest byNames = request()
                .selectors(
                        selectClass("com.howtodoinjava.junit.orders.ShippingJUnit4Test"),
                        selectMethod("com.howtodoinjava.junit.orders.OrderValidatorTest#freeShippingFromFiftyDollars"))
                .filters(includeClassNamePatterns(".*Test"))
                .build();

        Launcher launcher = LauncherFactory.create();
        System.out.println("checkout tag: " + run(launcher, byTag));
        System.out.println("by name:      " + run(launcher, byNames));
    }

    static String run(Launcher launcher, LauncherDiscoveryRequest request) {
        SummaryGeneratingListener listener = new SummaryGeneratingListener();
        launcher.execute(request, listener);
        TestExecutionSummary summary = listener.getSummary();
        return summary.getTestsSucceededCount() + " passed, " + summary.getTestsFailedCount() + " failed";
    }
}
