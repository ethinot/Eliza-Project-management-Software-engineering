package fr.univ_lyon1.info.m1.elizagpt.model;

import fr.univ_lyon1.info.m1.elizagpt.model.utils.TextUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void testIsMatch() {
        assertTrue(TextUtils.isMatch("test", "Testing match function"));
        assertFalse(TextUtils.isMatch("xyz", "Testing match function"));
        assertTrue(TextUtils.isMatch("MATCH", "Testing match function"));
        assertFalse(TextUtils.isMatch("NOT", "Testing match function"));
    }

    @Test
    void testContainsWholeWord() {
        // Given
        String word = "heureux";
        String message1 = "Je suis heureux";
        String message2 = "L'heurisitude est important - Victor Hugo";
        String message3 = "A Lyon 1, heureusement que mathieu Moy fait partie de nos professeurs !";

        // When
        boolean result1 = TextUtils.containsWholeWord(word, message1);
        boolean result2 = TextUtils.containsWholeWord(word, message2);
        boolean result3 = TextUtils.containsWholeWord(word, message3);

        // Then
        assertThat(result1, is(true));
        assertThat(result2, is(false));
        assertThat(result3, is(false));
    }
}
