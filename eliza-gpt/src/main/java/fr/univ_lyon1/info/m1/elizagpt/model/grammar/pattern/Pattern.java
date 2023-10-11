package fr.univ_lyon1.info.m1.elizagpt.model.grammar.pattern;

/**
 *  Pattern class is used to describe regex pattern use to interpret user inputs.
 */
public class Pattern {
    private String regex;

    /**
     * Constructor by value that takes string parameter to build an instance.
     *
     * @param regex
     */
    public Pattern(final String regex) {
        this.regex = regex;
    }
}
