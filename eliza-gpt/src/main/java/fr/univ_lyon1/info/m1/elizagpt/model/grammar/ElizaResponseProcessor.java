package fr.univ_lyon1.info.m1.elizagpt.model.grammar;

import fr.univ_lyon1.info.m1.elizagpt.model.grammar.pattern.PatternProcessor;
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
        addMessage(input, Author.USER);
        String response = generateResponse(input);

        if (response != null) {
            addMessage(response, Author.ELIZA);
        }
    }

    private static String generateResponse(final String input) {
        String normalizedInput = TextUtils.normalize(input);
        return PatternProcessor.process(normalizedInput);
    }

    private void addMessage(final String text, final Author author) {
        Message message = MessageFactory.createMessage(text, author);
        messageRepository.addMessage(message);
    }
}
