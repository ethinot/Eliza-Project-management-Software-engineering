package fr.univ_lyon1.info.m1.elizagpt.model;

import fr.univ_lyon1.info.m1.elizagpt.model.messages.MessageProcessor;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Tests for MessageProcessor.
 */
class MessageProcessorTest {
    @Test
    void testFirstToSecondPerson() {
        // Then
        assertThat(MessageProcessor.firstToSecondPerson("Je pense à mon chien."),
                is("vous pensez à votre chien."));

        assertThat(MessageProcessor.firstToSecondPerson("Je suis heureux."),
                is("vous êtes heureux."));

        assertThat(MessageProcessor.firstToSecondPerson("Je dis bonjour."),
                is("vous dites bonjour."));

        assertThat(MessageProcessor.firstToSecondPerson("Je vais à la mer."),
                is("vous allez à la mer."));

        assertThat(MessageProcessor.firstToSecondPerson("Je finis mon travail."),
                is("vous finissez votre travail."));
    }

    /*    **
     * Not so relevant test, but here to give an example of non-trivial
     * hamcrest assertion.
     *
    @Test
    void testVerbList() {
        assertThat(MessageProcessor.VERBS, hasItem(
                allOf(
                        hasProperty("firstSingular", is("suis")),
                        hasProperty("secondPlural", is("êtes")))));
    }*/
}
