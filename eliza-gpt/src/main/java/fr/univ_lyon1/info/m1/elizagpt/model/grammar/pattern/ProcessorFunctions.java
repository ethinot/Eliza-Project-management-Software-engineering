package fr.univ_lyon1.info.m1.elizagpt.model.grammar.pattern;

import fr.univ_lyon1.info.m1.elizagpt.model.message.NameState;
import fr.univ_lyon1.info.m1.elizagpt.model.utils.RandomUtils;
import fr.univ_lyon1.info.m1.elizagpt.model.utils.TextUtils;
import fr.univ_lyon1.info.m1.elizagpt.model.utils.XmlLoader;
import fr.univ_lyon1.info.m1.elizagpt.model.utils.XmlMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import static fr.univ_lyon1.info.m1.elizagpt.model.grammar.pattern.PatternProcessor.getFirstMatchedString;

abstract class ProcessorBase implements UnaryOperator<String> {
    private final Map<String, List<String>> responses;

    ProcessorBase(final String responseElementName) {
        responses = XmlLoader.loadSingleResponse(
                "responses/processor_responses.xml",
                responseElementName,
                XmlMapper::mapElementToProcessorResponses);
    }

    protected List<String> getResponsesFor(final String key) {
        return this.responses.get(key);
    }

    protected List<String> getDefaultResponses() {
        return this.getResponsesFor("default");
    }
}

class MyNameIsProcessor extends ProcessorBase {
    private final List<String> newNameResponses;
    private final List<String> alreadyKnownResponses;
    private final List<String> updatedResponses;

    MyNameIsProcessor() {
        super("MyNameIsResponse");
        this.newNameResponses = super.getResponsesFor("newName");
        this.alreadyKnownResponses = super.getResponsesFor("alreadyKnown");
        this.updatedResponses = super.getResponsesFor("updated");
    }

    private static String extractName(final String s) {
        return getFirstMatchedString(PatternProcessor.MY_NAME_IS.getPattern(), s);
    }

    @Override
    public String apply(final String s) {
        final String newName = extractName(s);

        return processNameChange(newName);
    }

    private String processNameChange(final String newName) {
        if (NameState.getName() == null) {
            return processNewName(newName);
        } else if (NameState.getName().equals(newName)) {
            return processKnownName(newName);
        } else {
            return processUpdatedName(newName);
        }
    }

    private String processUpdatedName(final String newName) {
        String oldName = NameState.getName();
        NameState.setName(newName);
        return TextUtils.getString(updatedResponses, oldName, newName);
    }

    private String processKnownName(final String newName) {
        return TextUtils.getString(alreadyKnownResponses, newName);
    }

    private String processNewName(final String newName) {
        NameState.setName(newName);
        return TextUtils.getString(newNameResponses, newName);
    }
}

class WhatIsMyNameProcessor extends ProcessorBase {
    private final List<String> knownResponses;
    private final List<String> unknownResponses;

    WhatIsMyNameProcessor() {
        super("WhatIsMyNameResponse");
        knownResponses = super.getResponsesFor("known");
        unknownResponses = super.getResponsesFor("unknown");
    }

    @Override
    public String apply(final String s) {
        if (NameState.getName() != null) {
            return TextUtils.getString(knownResponses, NameState.getName());
        } else {
            return TextUtils.getString(unknownResponses);
        }
    }
}

class ForgetMyNameProcessor extends ProcessorBase {
    ForgetMyNameProcessor() {
        super("ForgetMyNameResponse");
    }

    @Override
    public String apply(final String s) {
        NameState.setName(null);
        return TextUtils.getString(getDefaultResponses());
    }
}

class WhoIsTheMostProcessor extends ProcessorBase {
    WhoIsTheMostProcessor() {
        super("WhoIsTheMostResponse");
        super.getDefaultResponses();
    }

    @Override
    public String apply(final String s) {
        final String adjective = getFirstMatchedString(
                PatternProcessor.WHO_IS_THE_MOST.getPattern(), s);
        return TextUtils.getString(getDefaultResponses(), adjective);
    }
}

class ByeProcessor extends ProcessorBase {
    private final List<String> knowResponses;
    private final List<String> unknownResponses;

    ByeProcessor() {
        super("ByeResponse");
        knowResponses = super.getResponsesFor("known");
        unknownResponses = super.getResponsesFor("unknown");
    }

    @Override
    public String apply(final String s) {
        if (NameState.getName() == null) {
            return TextUtils.getString(unknownResponses);
        } else {
            return TextUtils.getString(knowResponses, NameState.getName());
        }
    }
}

class IProcessor extends ProcessorBase {
    IProcessor() {
        super("IResponse");
        super.getDefaultResponses();
    }

    @Override
    public String apply(final String s) {
        String statement = TextUtils.firstToSecondPerson(
                Objects.requireNonNull(getFirstMatchedString(PatternProcessor.I.getPattern(), s))
        );

        return TextUtils.getString(getDefaultResponses(), statement);
    }
}

class IAskHereProcessor extends ProcessorBase {
    IAskHereProcessor() {
        super("IAskHereResponse");
        super.getDefaultResponses();
    }

    @Override
    public String apply(final String s) {
        return TextUtils.getString(getDefaultResponses());
    }
}

class DefaultResponseProcessor implements UnaryOperator<String> {
    private static final ArrayList<String> DEFAULT_RESPONSES;
    private static final ArrayList<String> RESPONSES_WITH_NAME;

    static {
        List<Map.Entry<String, String>> responseEntries = XmlLoader.load(
                "responses/default_responses.xml",
                "response",
                XmlMapper::mapElementToDefaultResponse
        );

        Map<String, String> responsesMap = responseEntries.stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        DEFAULT_RESPONSES = new ArrayList<>(responsesMap.keySet());
        RESPONSES_WITH_NAME = new ArrayList<>(responsesMap.values());
    }

    @Override
    public String apply(final String s) {
        String name = NameState.getName();
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
