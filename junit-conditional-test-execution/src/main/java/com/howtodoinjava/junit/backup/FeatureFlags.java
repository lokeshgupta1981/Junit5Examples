package com.howtodoinjava.junit.backup;

public final class FeatureFlags {

  private FeatureFlags() {
  }

  public static boolean incrementalBackupEnabled() {
    return Boolean.parseBoolean(System.getProperty("feature.incremental", "false"));
  }
}
