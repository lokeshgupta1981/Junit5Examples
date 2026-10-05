package com.howtodoinjava.archtests;

import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.freeze.FreezingArchRule;

/**
 * The legacy package already uses field injection. The frozen rule accepts the
 * violations recorded in the archunit_store folder and fails only for new ones.
 */
@AnalyzeClasses(packages = "com.howtodoinjava.legacy")
class FrozenRulesTest {

  @ArchTest
  static final ArchRule noNewFieldInjection =
      FreezingArchRule.freeze(NO_CLASSES_SHOULD_USE_FIELD_INJECTION);
}
