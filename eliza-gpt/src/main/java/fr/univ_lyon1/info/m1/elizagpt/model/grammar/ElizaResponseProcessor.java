package fr.univ_lyon1.info.m1.elizagpt.model.grammar;

import fr.univ_lyon1.info.m1.elizagpt.model.grammar.pattern.PatternProcessor;
import fr.univ_lyon1.info.m1.elizagpt.model.grammar.verb.Verb;
import fr.univ_lyon1.info.m1.elizagpt.model.grammar.verb.VerbsRepository;
import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.message.MessageFactory;
import fr.univ_lyon1.info.m1.elizagpt.model.message.message_types.Author;
import fr.univ_lyon1.info.m1.elizagpt.model.message.MessageRepository;
import fr.univ_lyon1.info.m1.elizagpt.model.utils.TextUtils;

/**
 * Main class for build and store Eliza responses.
 */
public class ElizaResponseProcessor {
    private final MessageRepository messageRepository;
    private static final VerbsRepository VERBS_REPOSITORY =
            new VerbsRepository("conjugation/french-verbs.xml");

    /**
     * Build instances required by the class.
     *
     */
    public ElizaResponseProcessor(final MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * Process the input and generate a response.
     *
     * @param input the input to be processed
     */
    public void process(final String input) {
        Message userMessage = MessageFactory.createMessage(input, Author.USER);
        messageRepository.sendMessage(userMessage);

        String response = generateResponse(input);

        if (response != null) {
            replyToUser(response);
        }
    }

    private static String generateResponse(final String input) {
        String normalizedInput = TextUtils.normalize(input);
        return PatternProcessor.process(normalizedInput);
    }

    private void replyToUser(final String s) {
        Message elizaMessage = MessageFactory.createMessage(s, Author.ELIZA);
        messageRepository.sendMessage(elizaMessage);
    }

    /**
     *
     * Transforms the given text from first person to second person.
     * This method first transforms the verbs in the text and then transforms possessive adjectives.
     *
     * @param text the text to be transformed
     * @return the transformed text in second person
     */
    public static String firstToSecondPerson(final String text) {
        String transformedVerbText = transformVerbs(text);
        return transformPossesiveAdj(transformedVerbText);
    }

    private static String transformVerbs(final String text) {
        String transformedText = text
                .replaceAll("[Jj]e ([a-z]*)e ", "vous $1ez ");
        for (Verb v : VERBS_REPOSITORY.getVerbs()) {
            transformedText = transformedText.replaceAll(
                    "[Jj]e " + v.getFirstSingular(),
                    "vous " + v.getSecondPlural());
        }
        return transformedText
                .replaceAll("[Jj]e ([a-z]*)s ", "vous $1ssez ");
    }

    private static String transformPossesiveAdj(final String text) {
        return text.replace("mon ", "votre ")
                .replace("ma ", "votre ")
                .replace("mes ", "vos ")
                .replace("moi", "vous");
    }
}
