package fr.univ_lyon1.info.m1.elizagpt.model.grammar.pattern;

import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public enum PatternProcessor {
    MY_NAME_IS(".*Je m'appelle (.*)\\.", new MyNameIsProcessor()),
    WHAT_IS_MY_NAME(".*Quel est mon nom \\?", new WhatIsMyNameProcessor()),
    WHO_IS_THE_MOST("*Qui est le plus (.*) \\?", new WhoIsTheMostProcessor()),
    I("(Je .*)\\.", new IProcessor());

    private static String name = null;
    private final Pattern pattern;

    private final Function<String, String> processor;

    PatternProcessor(String regex, UnaryOperator<String> processor) {
        this.pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        this.processor = processor;
    }

    public static String process(String input) {
        for (PatternProcessor p : PatternProcessor.values()) {
            if (p.matches(input)) {
                return p.applyProcess(input);
            }
        }
        return null;
    }

    static String getName() {
        return name;
    }

    static void setName(String name) {
        PatternProcessor.name = name;
    }

    static String getFirstMatchedString(Pattern pattern, String input) {
        return pattern.matcher(input).group(1);
    }

    private boolean matches(String input) {
        return pattern.matcher(input).matches();
    }

    public Pattern getPattern() {
        return pattern;
    }

    private String applyProcess(String input) {
        return processor.apply(input);
    }
}
