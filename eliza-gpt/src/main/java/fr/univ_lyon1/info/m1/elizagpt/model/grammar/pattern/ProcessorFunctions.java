package fr.univ_lyon1.info.m1.elizagpt.model.grammar.pattern;

import fr.univ_lyon1.info.m1.elizagpt.model.messages.MessageProcessor;

import java.util.function.UnaryOperator;

import static fr.univ_lyon1.info.m1.elizagpt.model.grammar.pattern.PatternProcessor.getFirstMatchedString;

class MyNameIsProcessor implements UnaryOperator<String> {
    @Override
    public String apply(String s) {
        final String newName = getFirstMatchedString(PatternProcessor.MY_NAME_IS.getPattern(), s);
        if (PatternProcessor.getName() == null) {
            PatternProcessor.setName(newName);
            return "Bonjour " + newName + " !";
        } else if (PatternProcessor.getName().equals(newName)) {
            return "Je sais déjà que vous vous appelez " + newName + ".";
        } else {
            String oldName = PatternProcessor.getName();
            PatternProcessor.setName(newName);
            return "Je sais maintenant que vous vous appelez " + newName + " et non plus " + oldName + ".";
        }
    }
}

class WhatIsMyNameProcessor implements UnaryOperator<String> {
    @Override
    public String apply(String s) {
        if (PatternProcessor.getName() != null) {
            return "Votre nom est " + PatternProcessor.getName() + ".";
        } else {
            return "Je ne connais pas votre nom.";
        }
    }
}

class WhoIsTheMostProcessor implements UnaryOperator<String> {
    @Override
    public String apply(String s) {
        return "Le plus " + getFirstMatchedString(PatternProcessor.WHO_IS_THE_MOST.getPattern(), s) + " est bien sûr votre enseignant de MIF01 !";
    }
}

class IProcessor implements UnaryOperator<String> {
    @Override
    public String apply(String s) {
        final String startQuestion = MessageProcessor.pickRandom(new String[]{"Pourquoi dites-vous que ", "Pourquoi pensez-vous que ", "Êtes-vous sûr que ",});

        return startQuestion + MessageProcessor.firstToSecondPerson(getFirstMatchedString(PatternProcessor.I.getPattern(), s)) + " ?";
    }
}
