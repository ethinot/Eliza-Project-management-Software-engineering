package fr.univ_lyon1.info.m1.elizagpt.model.processors;

import fr.univ_lyon1.info.m1.elizagpt.model.grammar.pattern.PatternProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;

class ForgetMyNameTest {
    @BeforeEach
    void setUp() {
        String input = "Je m'appelle Jerome";
        PatternProcessor.process(input);
    }

    @Test
    void testApplyWhenNameIsUnknown() {
        // Given
        String input1 = "Oublie mon nom";
        String input2 = "Quel est mon nom ?";


        // When
        String responseForgetMyName = PatternProcessor.process(input1);
        String responseWhatIsMyName = PatternProcessor.process(input2);

        // Then
        assertThat(responseForgetMyName, anyOf(
                containsString("Mais, qui êtes-vous ?"),
                containsString("Je ne vous connais pas."),
                containsString("Très bien, je vois votre petit jeu.")
        ));

        assertThat(responseWhatIsMyName, anyOf(
                containsString("Je ne sais pas comment vous vous appelez."),
                containsString("Je ne me souviens pas de votre nom."),
                containsString("Je ne connais pas votre nom."),
                containsString("Nous ne nous sommes pas encore présentés."),
                containsString("J'aimerais bien savoir comment vous vous appelez.")
        ));
    }
}
