package fr.univ_lyon1.info.m1.elizagpt.model.processors;

import fr.univ_lyon1.info.m1.elizagpt.model.grammar.pattern.PatternProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;

class ByeTest {
    @BeforeEach
    void setUp() {
        String input = "Oublie mon nom";
        PatternProcessor.process(input);
    }

    @Test
    void testProcessByeUnknown() {
        // Given
        String input = "Au revoir";

        // When
        String response = PatternProcessor.process(input);

        // Then
        assertThat(response, anyOf(
                containsString("Peut-être que vous me direz votre"
                        + " nom la prochaine fois... Au revoir !"),
                containsString("Au revoir, à bientôt !"),
                containsString("Je suis contente d'avoir pu vous aider. Au revoir !"),
                containsString("Ravie d'avoir pu vous aider. A bientôt !"),
                containsString("C'était un plaisir de discuter avec vous. A bientôt !")
        ));
    }

    @Test
    void testProcessByeKnown() {
        // Given
        String input1 = "Je m'appelle Jerome.";
        String input2 = "Au revoir";

        // When
        PatternProcessor.process(input1);
        String response = PatternProcessor.process(input2);

        // Then
        assertThat(response, anyOf(
                containsString("Au revoir Jerome, à bientôt !"),
                containsString("Je suis contente d'avoir pu vous aider, Jerome. Au revoir !"),
                containsString("Ravie d'avoir pu vous aider, Jerome. A bientôt !"),
                containsString("C'était un plaisir de discuter avec vous, Jerome. A bientôt !")
        ));
    }
}
