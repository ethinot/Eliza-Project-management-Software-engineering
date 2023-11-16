package fr.univ_lyon1.info.m1.elizagpt.model.grammar.verb;

/**
 * Verb class that contain two strings representing the first person of the singular
 * and the second of the plural.
 */
public class Verb {
    private final String firstSingular;
    private final String secondPlural;

    public Verb(final String firstSingular, final String secondPlural) {
        this.firstSingular = firstSingular;
        this.secondPlural = secondPlural;
    }

    public String getFirstSingular() {
        return firstSingular;
    }

    public String getSecondPlural() {
        return secondPlural;
    }
}
