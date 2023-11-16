package fr.univ_lyon1.info.m1.elizagpt.model.grammar.pattern;

import fr.univ_lyon1.info.m1.elizagpt.model.messages.MessageProcessor;
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
    @Override
    public String apply(final String s) {
        final String newName = getFirstMatchedString(PatternProcessor.MY_NAME_IS.getPattern(), s);
        if (PatternProcessor.getName() == null) {
            PatternProcessor.setName(newName);
            return "Bonjour " + newName + " !";
        } else if (PatternProcessor.getName().equals(newName)) {
            return "Je sais déjà que vous vous appelez " + newName + ".";
        } else {
            String oldName = PatternProcessor.getName();
            PatternProcessor.setName(newName);
            return "Je sais maintenant que vous vous appelez "
                    + newName + " et non plus " + oldName + ".";
        }
    }
}

class WhatIsMyNameProcessor implements UnaryOperator<String> {
    @Override
    public String apply(final String s) {
        if (PatternProcessor.getName() != null) {
            return "Votre nom est " + PatternProcessor.getName() + ".";
        } else {
            return "Je ne connais pas votre nom.";
        }
    }
}

class WhoIsTheMostProcessor implements UnaryOperator<String> {
    @Override
    public String apply(final String s) {
        return "Le plus " + getFirstMatchedString(
                PatternProcessor.WHO_IS_THE_MOST.getPattern(), s)
                + " est bien sûr votre enseignant de MIF01 !";
    }
}

class IProcessor implements UnaryOperator<String> {
    @Override
    public String apply(final String s) {
        final String startQuestion = RandomUtils.pickArrayRandom(
                new String[]{"Pourquoi dites-vous que ",
                        "Pourquoi pensez-vous que ",
                        "Êtes-vous sûr que ",
                });

        return startQuestion + TextUtils.firstToSecondPerson(Objects.requireNonNull(
                getFirstMatchedString(PatternProcessor.I.getPattern(), s))).concat(" ?");
    }
}

class IAskHereProcessor implements UnaryOperator<String> {
    @Override
    public String apply(final String s) {
        return "C'est mon terrain ici, c'est moi qui pose les questions.";
    }
}

class DefaultResponseProcessor implements UnaryOperator<String> {
    private static final ArrayList<String> DEFAULT_RESPONSES;
    private static final ArrayList<String> RESPONSES_WITH_NAME;

    static {
        List<Map.Entry<String, String>> responseEntries = XmlLoader.load(
                "responses/default_responses.xml", "response", XmlMapper::mapElementToResponse
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

    private String getRandomResponseWithName(String name) {
        String template = RESPONSES_WITH_NAME.get(RandomUtils.nextInt(RESPONSES_WITH_NAME.size()));
        return String.format(template, name);
    }
}
