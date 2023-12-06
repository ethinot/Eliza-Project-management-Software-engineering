package fr.univ_lyon1.info.m1.elizagpt.model.grammar.verb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Verb.
 */
class VerbTest {

    /**
     * This method tests the getter methods getFirstSingular()
     * and getSecondPlural() of the Verb class.
     * It verifies that the values returned by these methods are correct.
     */
    @Test
    void testVerbGetterMethods() {
        // Arrange
        String firstSingular = "vais";
        String secondPlural = "allez";
        Verb verb = new Verb(firstSingular, secondPlural);

        // Act
        String resultFirstSingular = verb.getFirstSingular();
        String resultSecondPlural = verb.getSecondPlural();

        // Assert
        assertEquals(firstSingular, resultFirstSingular);
        assertEquals(secondPlural, resultSecondPlural);
    }
}
