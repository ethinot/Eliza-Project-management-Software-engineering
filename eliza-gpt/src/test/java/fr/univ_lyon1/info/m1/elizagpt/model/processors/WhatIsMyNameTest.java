package fr.univ_lyon1.info.m1.elizagpt.model.processors;

import fr.univ_lyon1.info.m1.elizagpt.model.grammar.pattern.PatternProcessor;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;

class WhatIsMyNameTest {
    @BeforeEach
    void setUp() {
        String input = "Oublie mon nom";
        PatternProcessor.process(input);
    }

    @Test
    void testApplyWhenNameIsUnknown() {
        // Given
        String input = "Quel est mon nom ?";

        // When
        String response = PatternProcessor.process(input);

        // Then
        assertThat(response, anyOf(
                containsString("Je ne sais pas comment vous vous appelez."),
                containsString("Je ne me souviens pas de votre nom."),
                containsString("Je ne connais pas votre nom."),
                containsString("Nous ne nous sommes pas encore présentés."),
                containsString("J'aimerais bien savoir comment vous vous appelez.")
        ));
    }

    @Test
    void testApplyWhenNameIsKnown() {
        // Given
        String inputSayName = "Je m'appelle Jerome.";
        PatternProcessor.process(inputSayName);
        String inputWhatIsMyName = "Quel est mon nom ?";

        // When
        String response = PatternProcessor.process(inputWhatIsMyName);

        // Then
        assertThat(response, anyOf(
                containsString("Vous vous appelez Jerome."),
                containsString("D'après mes notes, vous vous appelez Jerome."),
                containsString("Je me souviens que vous vous appelez Jerome.")
        ));

    }
}
