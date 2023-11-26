package fr.univ_lyon1.info.m1.elizagpt.model.processors;

import fr.univ_lyon1.info.m1.elizagpt.model.grammar.pattern.PatternProcessor;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;

class ITest {
    @Test
    void testProcessMyNameIsNew() {
        // Given
        String input = "Je suis un étudiant en informatique.";

        // When
        String response = PatternProcessor.process(input);

        // Then
        assertThat(response, anyOf(
                containsString("Pourquoi dites-vous que vous êtes un étudiant en informatique ?"),
                containsString("Vous pensez vraiment que vous êtes un étudiant en informatique ?"),
                containsString("Mais pourquoi vous êtes un étudiant en informatique ?"),
                containsString("Je ne suis pas sûre de comprendre pourquoi vous dites que vous êtes"
                        + " un étudiant en informatique."),
                containsString("Je ne suis pas sûre de comprendre ce que vous voulez dire"
                        + " par vous êtes un étudiant en informatique.")
        ));
    }
}
