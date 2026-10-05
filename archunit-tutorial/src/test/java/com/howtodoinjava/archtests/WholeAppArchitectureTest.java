package com.howtodoinjava.archtests;

import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

/** Imports all of com.howtodoinjava except tests and the legacy package. */
@AnalyzeClasses(packages = "com.howtodoinjava",
    importOptions = {ImportOption.DoNotIncludeTests.class, ExcludeLegacyImportOption.class})
class WholeAppArchitectureTest {

  @ArchTest
  static final ArchRule noSystemOut = NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;
}
