package fr.univ_lyon1.info.m1.elizagpt.model.grammar;

import fr.univ_lyon1.info.m1.elizagpt.model.grammar.pattern.PatternProcessor;
import fr.univ_lyon1.info.m1.elizagpt.model.grammar.verb.VerbsRepository;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.ElizaMessage;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.MessageProcessor;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.MessageRepository;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.UserMessage;

/**
 * Main class for build and store Eliza responses.
 */
public class ElizaResponseProcessor {
    private final MessageRepository messageRepository;

    /**
     * Build instances required by the class.
     *
     */
    public ElizaResponseProcessor(final MessageRepository messageRepository) {
        new VerbsRepository("vocabulary/vocabulary.xml");
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

        String normalizedInput = MessageProcessor.normalize(input);

        String response = PatternProcessor.process(normalizedInput);

        if (response != null) {
            replyToUser(response);
        }
    }

    private void replyToUser(final String s) {
        ElizaMessage elizaMessage = new ElizaMessage(s);
        messageRepository.sendMessage(elizaMessage);
    }
}
