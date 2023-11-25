package fr.univ_lyon1.info.m1.elizagpt.model.processors;

import fr.univ_lyon1.info.m1.elizagpt.model.grammar.pattern.PatternProcessor;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;

class WhoIsTheMostTest {
    @Test
    void testProcessWhoIsTheMost() {
        // Given
        String input = "Qui est le plus grand ?";

        // When
        String response = PatternProcessor.process(input);

        // Then
        assertThat(response, anyOf(
                containsString("Le plus grand est bien sûr votre enseignant de MIF01 !"),
                containsString("Je ne sais pas qui est le plus grand, peut-être votre enseignant de MIF01 ?"),
                containsString("Je ne sais pas qui est le plus grand, mais je suis sûr que ce n'est pas votre enseignant de MIF01 !"),
                containsString("D'après moi, le plus grand est votre enseignant de MIF01 !")
        ));
    }
}
