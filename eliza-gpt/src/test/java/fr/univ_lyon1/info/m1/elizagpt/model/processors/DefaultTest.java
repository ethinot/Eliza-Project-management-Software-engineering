package fr.univ_lyon1.info.m1.elizagpt.model.processors;

import fr.univ_lyon1.info.m1.elizagpt.model.grammar.pattern.PatternProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;

class DefaultTest {
    @BeforeEach
    void setUp() {
        String input = "Oublie mon nom";
        PatternProcessor.process(input);
    }

    @Test
    void testProcessDefaultWithNoName() {
        // Given
        String randomString = Long.toHexString(Double.doubleToLongBits(Math.random()));

        // When
        String response = PatternProcessor.process(randomString);

        // Then
        assertThat(response, anyOf(
                containsString("Il fait beau aujourd'hui, vous ne trouvez pas ?"),
                containsString("Je ne comprends pas."),
                containsString("Parfois, il est préférable de prendre du recul pour mieux avancer."),
                containsString("La vie est pleine de surprises, n'est-ce pas ?"),
                containsString("Ça semble être une situation complexe."),
                containsString("Intéressant, pouvez-vous en dire plus ?"),
                containsString("C'est une perspective unique."),
                containsString("Je comprends. Comment cela vous fait-il sentir ?"),
                containsString("Parlez-moi davantage de vos sentiments à ce sujet."),
                containsString("Cela doit être difficile pour vous."),
                containsString("Comment puis-je vous aider aujourd'hui ?"),
                containsString("Êtes-vous certain de cela ?")
        ));

    }

    @Test
    void testProcessDefaultWithName() {
        // Given
        String input = "Je m'appelle Jerome.";
        PatternProcessor.process(input);
        String randomString = Long.toHexString(Double.doubleToLongBits(Math.random()));

        // When
        String response = PatternProcessor.process(randomString);

        // Then
        assertThat(response, anyOf(
                containsString("Il fait beau aujourd'hui, Jerome, vous ne trouvez pas ?"),
                containsString("Je ne comprends pas, Jerome."),
                containsString("Parfois, Jerome, il est préférable de prendre du recul pour mieux avancer."),
                containsString("La vie est pleine de surprises, n'est-ce pas, Jerome ?"),
                containsString("Ça semble être une situation complexe, Jerome."),
                containsString("Intéressant, Jerome, pouvez-vous en dire plus ?"),
                containsString("C'est une perspective unique, Jerome."),
                containsString("Je comprends, Jerome. Comment cela vous fait-il sentir ?"),
                containsString("Parlez-moi davantage de vos sentiments à ce sujet, Jerome."),
                containsString("Cela doit être difficile pour vous, Jerome."),
                containsString("Comment puis-je vous aider aujourd'hui, Jerome ?"),
                containsString("Êtes-vous certain de cela, Jerome ?")

        ));

    }
}
