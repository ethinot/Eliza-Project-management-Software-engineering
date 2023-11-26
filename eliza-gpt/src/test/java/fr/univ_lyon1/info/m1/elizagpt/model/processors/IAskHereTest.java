package fr.univ_lyon1.info.m1.elizagpt.model.processors;

import fr.univ_lyon1.info.m1.elizagpt.model.grammar.pattern.PatternProcessor;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;

class IAskHereTest {
    @Test
    void testProcessIAskHere() {
        // Given
        String input = "Comment allez-vous ?";

        // When
        String response = PatternProcessor.process(input);

        // Then
        assertThat(response, anyOf(
                containsString("C'est mon terrain ici, c'est moi qui pose les questions."),
                containsString("Je suis la thérapeute, concentrez-vous sur vous-même."),
                containsString("Je ne peux pas répondre à cette question,"
                        + " c'est vous qui devriez me le dire."),
                containsString("Il est important que vous me parliez de vous, pas de moi.")
        ));
    }
}
