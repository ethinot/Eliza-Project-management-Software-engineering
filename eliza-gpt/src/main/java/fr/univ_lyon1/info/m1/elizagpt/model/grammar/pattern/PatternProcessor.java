package fr.univ_lyon1.info.m1.elizagpt.model.grammar.pattern;

import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that enable to process each pattern.
 */
public enum PatternProcessor {
    MY_NAME_IS(".*Je m'appelle (.*)\\.", new MyNameIsProcessor()),
    WHAT_IS_MY_NAME(".*Quel est mon nom \\?", new WhatIsMyNameProcessor()),
    WHO_IS_THE_MOST(".*Qui est le plus (.*) \\?", new WhoIsTheMostProcessor()),
    I("(Je .*)\\.", new IProcessor()),
    I_ASK_HERE(".*\\?", new IAskHereProcessor()),
    DEFAULT_RESPONSE(".*", new DefaultResponseProcessor());

    private static String name = null;
    private final Pattern pattern;

    private final Function<String, String> processor;

    PatternProcessor(final String regex, final UnaryOperator<String> processor) {
        this.pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        this.processor = processor;
    }

    /**
     * Processes the input string using the appropriate pattern processor.
     *
     * @param input the input string to be processed
     * @return the processed string, or null if no pattern processor matches the input
     */
    public static String process(final String input) {
        for (PatternProcessor p : PatternProcessor.values()) {
            if (p.matches(input)) {
                return p.applyProcess(input);
            }
        }
        return null;
    }

    public static String getName() {
        return name;
    }

    static void setName(final String name) {
        PatternProcessor.name = name;
    }

    static String getFirstMatchedString(final Pattern pattern, final String input) {
        Matcher matcher = pattern.matcher(input);
        boolean found = matcher.find();

        if (found) {
            return matcher.group(1);
        } else {
            return null;
        }
    }

    private boolean matches(final String input) {
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public Pattern getPattern() {
        return pattern;
    }

    private String applyProcess(final String input) {
        return processor.apply(input);
    }
}
