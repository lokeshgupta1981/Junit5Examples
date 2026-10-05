package com.howtodoinjava.archtests;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.core.importer.Location;

/** Skips every class file whose path contains /legacy/. */
public class ExcludeLegacyImportOption implements ImportOption {

  @Override
  public boolean includes(Location location) {
    return !location.contains("/legacy/");
  }
}
