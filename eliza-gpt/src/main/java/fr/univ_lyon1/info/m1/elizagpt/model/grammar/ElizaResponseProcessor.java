package fr.univ_lyon1.info.m1.elizagpt.model.grammar;

import fr.univ_lyon1.info.m1.elizagpt.model.grammar.pattern.PatternProcessor;
import fr.univ_lyon1.info.m1.elizagpt.model.grammar.verb.Verb;
import fr.univ_lyon1.info.m1.elizagpt.model.grammar.verb.VerbsRepository;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.ElizaMessage;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.MessageRepository;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.UserMessage;
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
        UserMessage userMessage = new UserMessage(input);
        messageRepository.sendMessage(userMessage);

        String normalizedInput = TextUtils.normalize(input);

        String response = PatternProcessor.process(normalizedInput);

        if (response != null) {
            replyToUser(response);
        }
    }

    private void replyToUser(final String s) {
        ElizaMessage elizaMessage = new ElizaMessage(s);
        messageRepository.sendMessage(elizaMessage);
    }

    /**
     * Turn a 1st-person sentence (Je ...) into a plural 2nd person (Vous ...).
     * The result is not capitalized to allow forming a new sentence.
     * <p>
     * TODO: does not deal with all 3rd group verbs.
     *
     * @param text The 1st-person sentence.
     * @return The 2nd-person sentence.
     */
    public static String firstToSecondPerson(final String text) {
        String processedText = text
                .replaceAll("[Jj]e ([a-z]*)e ", "vous $1ez ");
        for (Verb v : VERBS_REPOSITORY.getVerbs()) {
            processedText = processedText.replaceAll(
                    "[Jj]e " + v.getFirstSingular(),
                    "vous " + v.getSecondPlural());
        }

        processedText = processedText
                .replaceAll("[Jj]e ([a-z]*)s ", "vous $1ssez ")
                .replace("mon ", "votre ")
                .replace("ma ", "votre ")
                .replace("mes ", "vos ")
                .replace("moi", "vous");
        return processedText;
    }
}
