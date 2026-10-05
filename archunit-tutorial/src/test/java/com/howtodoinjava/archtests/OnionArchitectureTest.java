package com.howtodoinjava.archtests;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = "com.howtodoinjava.mealplanner",
    importOptions = ImportOption.DoNotIncludeTests.class)
class OnionArchitectureTest {

  @ArchTest
  static final ArchRule onion = onionArchitecture()
      .domainModels("..domain.model..")
      .domainServices("..domain.service..")
      .applicationServices("..application..")
      .adapter("rest", "..adapter.rest..")
      .adapter("persistence", "..adapter.persistence..");
}
