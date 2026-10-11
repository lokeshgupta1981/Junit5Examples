package com.howtodoinjava.junit.listener;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

public class FailureReportWatcher implements TestWatcher {

    @Override
    public void testSuccessful(ExtensionContext context) {
        System.out.println("[watcher] passed " + context.getDisplayName());
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println("[watcher] FAILED " + context.getRequiredTestMethod().getName()
                + " in " + context.getRequiredTestClass().getSimpleName() + ": " + cause.getMessage());
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        System.out.println("[watcher] disabled " + context.getDisplayName() + ": " + reason.orElse("no reason"));
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        System.out.println("[watcher] aborted " + context.getDisplayName());
    }
}
