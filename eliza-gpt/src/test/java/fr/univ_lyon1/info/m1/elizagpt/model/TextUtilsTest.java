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
}
