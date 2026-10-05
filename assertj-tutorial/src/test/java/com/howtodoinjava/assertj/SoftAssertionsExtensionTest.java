package com.howtodoinjava.assertj;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SoftAssertionsExtension.class)
class SoftAssertionsExtensionTest {

  @InjectSoftAssertions
  SoftAssertions softly;

  @Test
  void omelette() {
    Recipe omelette = Recipes.OMELETTE;

    softly.assertThat(omelette.name()).isEqualTo("Omelette");
    softly.assertThat(omelette.minutes()).isLessThanOrEqualTo(10);
    softly.assertThat(omelette.tags()).containsExactly("breakfast", "eggs");
    // no assertAll(): the extension calls it after the test method
  }

  @Test
  void asParameter(SoftAssertions params) {
    params.assertThat(Recipes.PANCAKES.tags()).hasSize(2);
  }
}
