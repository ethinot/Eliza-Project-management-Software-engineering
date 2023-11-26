package fr.univ_lyon1.info.m1.elizagpt.model.processors;

import fr.univ_lyon1.info.m1.elizagpt.model.grammar.pattern.PatternProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;

class MyNameIsTest {
    @BeforeEach
    void setUp() {
        String input = "Oublie mon nom";
        PatternProcessor.process(input);
    }

    @Test
    void testProcessMyNameIsNew() {
        // Given
        String input = "Je m'appelle Jerome.";

        // When
        String response = PatternProcessor.process(input);

        // Then
        assertThat(response, anyOf(
                containsString("Bonjour Jerome !"),
                containsString("Ravie de vous rencontrer, Jerome."),
                containsString("Je suis heureuse de faire votre connaissance, Jerome."),
                containsString("Enchantée, Jerome, je suis Eliza.")
        ));
    }

    @Test
    void testProcessMyNameIsUpdated() {
        // Given
        String input1 = "Je m'appelle Jerome.";
        PatternProcessor.process(input1);
        String input2 = "Je m'appelle Hervé.";

        // When
        String response = PatternProcessor.process(input2);

        // Then
        assertThat(response, anyOf(
                containsString("Je sais maintenant que vous vous appelez"
                        + " Hervé et non plus Jerome."),
                containsString("Je me souviendrai que vous vous appelez Hervé et non plus Jerome."),
                containsString("Je vais mettre à jour mon carnet d'adresses, Hervé."),
                containsString("Je vous connaissais sous le nom de Jerome,"
                        + " mais je sais maintenant que vous vous appelez Hervé.")
        ));
    }

    @Test
    void testProcessMyNameIsAlreadyKnown() {
        // Given
        String input = "Je m'appelle Hervé.";

        // When
        PatternProcessor.process(input);
        String response = PatternProcessor.process(input);

        // Then
        assertThat(response, anyOf(
                containsString("Je sais déjà que vous vous appelez Hervé."),
                containsString("Hervé, nous nous sommes déjà rencontrés."),
                containsString("Je me souviens que vous vous appelez Hervé."),
                containsString("Je me souviens de vous, Hervé."),
                containsString("On a déjà fait connaissance, Hervé.")
        ));
    }
}
