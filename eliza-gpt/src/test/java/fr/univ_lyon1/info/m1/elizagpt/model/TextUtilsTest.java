package fr.univ_lyon1.info.m1.elizagpt.model;

import fr.univ_lyon1.info.m1.elizagpt.model.utils.TextUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Tests for MessageProcessor.
 */
class TextUtilsTest {
    @Test
    void testNormalize() {
        // Then
        assertThat(TextUtils.normalize(" Je suis      heureux "), is("Je suis heureux."));
        assertThat(TextUtils.normalize("Bonjour!"), is("Bonjour!"));
        assertThat(TextUtils.normalize("C'est super   "), is("C'est super."));
        assertThat(TextUtils.normalize("voilà"), is("voilà."));
        assertThat(TextUtils.normalize(" Test..."), is("Test..."));
    }

    @Test
    void testFirstToSecondPerson() {
        // Then
        assertThat(TextUtils.firstToSecondPerson("Je pense à mon chien."),
                is("vous pensez à votre chien."));

        assertThat(TextUtils.firstToSecondPerson("Je suis heureux."),
                is("vous êtes heureux."));

        assertThat(TextUtils.firstToSecondPerson("Je dis bonjour."),
                is("vous dites bonjour."));

        assertThat(TextUtils.firstToSecondPerson("Je vais à la mer."),
                is("vous allez à la mer."));

        assertThat(TextUtils.firstToSecondPerson("Je finis mon travail."),
                is("vous finissez votre travail."));
    }

    @Test
    void testGetStringNoArgs() {
        // Given
        List<String> responses = Arrays.asList("Bonjour!", "Comment ça va?", "Ravi de te revoir!");

        // When
        String result = TextUtils.getString(responses);

        // Then
        assertThat(result, is(in(responses)));
    }

    @Test
    void testGetStringWithOneArg() {
        // Given
        List<String> responses = Arrays.asList("Bonjour %s !", "Comment ça va, %s ?",
                "Ravi de te revoir, %s !");
        String name = "Jerome";

        // When
        String result = TextUtils.getString(responses, name);

        // Then
        assertThat(result, either(is("Bonjour Jerome !"))
                .or(is("Comment ça va, Jerome ?"))
                .or(is("Ravi de te revoir, Jerome !")));
    }
}
