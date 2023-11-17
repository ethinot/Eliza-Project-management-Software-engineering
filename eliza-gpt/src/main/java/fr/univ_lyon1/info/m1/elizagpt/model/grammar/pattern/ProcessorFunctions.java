package fr.univ_lyon1.info.m1.elizagpt.model.grammar.pattern;

import fr.univ_lyon1.info.m1.elizagpt.model.utils.TextUtils;
import fr.univ_lyon1.info.m1.elizagpt.model.utils.RandomUtils;
import fr.univ_lyon1.info.m1.elizagpt.model.utils.XmlLoader;
import fr.univ_lyon1.info.m1.elizagpt.model.utils.XmlMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import static fr.univ_lyon1.info.m1.elizagpt.model.grammar.pattern.PatternProcessor.getFirstMatchedString;

class MyNameIsProcessor implements UnaryOperator<String> {
    static List<String> newNameResponses;
    static List<String> alreadyKnownResponses;
    static List<String> updatedResponses;

    static {
        Map<String, List<String>> responses = XmlLoader.loadSingleResponse(
                "responses/processor_responses.xml", "MyNameIsResponse", XmlMapper::mapElementToProcessorResponses);
        newNameResponses = responses.get("newName");
        alreadyKnownResponses = responses.get("alreadyKnown");
        updatedResponses = responses.get("updated");
    }

    @Override
    public String apply(final String s) {
        final String newName = getFirstMatchedString(PatternProcessor.MY_NAME_IS.getPattern(), s);

        if (PatternProcessor.getName() == null) {
            PatternProcessor.setName(newName);
            return TextUtils.getString(newNameResponses, newName);
        } else if (PatternProcessor.getName().equals(newName)) {
            return TextUtils.getString(alreadyKnownResponses, newName);
        } else {
            String oldName = PatternProcessor.getName();
            PatternProcessor.setName(newName);
            return TextUtils.getString(updatedResponses, oldName, newName);
        }
    }
}

class WhatIsMyNameProcessor implements UnaryOperator<String> {
    static List<String> knownResponses;
    static List<String> unknownResponses;

    static {
        Map<String, List<String>> responses = XmlLoader.loadSingleResponse(
                "responses/processor_responses.xml", "WhatIsMyNameResponse", XmlMapper::mapElementToProcessorResponses);
        knownResponses = responses.get("known");
        unknownResponses = responses.get("unknown");
    }

    @Override
    public String apply(final String s) {
        if (PatternProcessor.getName() != null) {
            return TextUtils.getString(knownResponses, PatternProcessor.getName());
        } else {
            return TextUtils.getString(unknownResponses);
        }
    }
}

class WhoIsTheMostProcessor implements UnaryOperator<String> {
    private static final List<String> responses;

    static {
        responses = XmlLoader.loadSingleResponse(
                        "responses/processor_responses.xml",
                        "WhoIsTheMostResponse",
                        XmlMapper::mapElementToProcessorResponses)
                .get("default");
    }

    @Override
    public String apply(final String s) {
        final String adjective = getFirstMatchedString(PatternProcessor.WHO_IS_THE_MOST.getPattern(), s);
        return TextUtils.getString(responses, adjective);
    }
}

class IProcessor implements UnaryOperator<String> {
    private static final List<String> responses;

    static {
        responses = XmlLoader.loadSingleResponse(
                        "responses/processor_responses.xml", "IProcessorResponse", XmlMapper::mapElementToProcessorResponses)
                .get("default");
    }

    @Override
    public String apply(final String s) {
        String statement = TextUtils.firstToSecondPerson(
                Objects.requireNonNull(getFirstMatchedString(PatternProcessor.I.getPattern(), s))
        );

        return TextUtils.getString(responses, statement);
    }
}


class IAskHereProcessor implements UnaryOperator<String> {
    private static final List<String> responses;

    static {
        responses = XmlLoader.loadSingleResponse(
                        "responses/processor_responses.xml", "IAskHereResponse", XmlMapper::mapElementToProcessorResponses)
                .get("default");
    }

    @Override
    public String apply(final String s) {
        return TextUtils.getString(responses);
    }
}


class DefaultResponseProcessor implements UnaryOperator<String> {
    private static final ArrayList<String> DEFAULT_RESPONSES;
    private static final ArrayList<String> RESPONSES_WITH_NAME;

    static {
        List<Map.Entry<String, String>> responseEntries = XmlLoader.load(
                "responses/default_responses.xml", "response", XmlMapper::mapElementToDefaultResponse
        );

        Map<String, String> responsesMap = responseEntries.stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        DEFAULT_RESPONSES = new ArrayList<>(responsesMap.keySet());
        RESPONSES_WITH_NAME = new ArrayList<>(responsesMap.values());
    }

    @Override
    public String apply(final String s) {
        String name = PatternProcessor.getName();
        if (name != null) {
            return getRandomResponseWithName(name);
        } else {
            return getRandomDefaultResponse();
        }
    }

    private String getRandomDefaultResponse() {
        int randomIndex = RandomUtils.nextInt(DEFAULT_RESPONSES.size());
        return DEFAULT_RESPONSES.get(randomIndex);
    }

    private String getRandomResponseWithName(final String name) {
        String template = RESPONSES_WITH_NAME.get(RandomUtils.nextInt(RESPONSES_WITH_NAME.size()));
        return String.format(template, name);
    }
}
