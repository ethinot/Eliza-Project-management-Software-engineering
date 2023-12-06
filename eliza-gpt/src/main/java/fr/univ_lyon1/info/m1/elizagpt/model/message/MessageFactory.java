package fr.univ_lyon1.info.m1.elizagpt.model.message;

import fr.univ_lyon1.info.m1.elizagpt.model.message.message_types.Author;
import fr.univ_lyon1.info.m1.elizagpt.model.message.message_types.ElizaMessage;
import fr.univ_lyon1.info.m1.elizagpt.model.message.message_types.UserMessage;

/**
 * This class is responsible for creating Message objects based on the given
 * message text and author. It uses the factory design pattern
 * to encapsulate the message creation logic.
 */
public final class MessageFactory {
    private MessageFactory() {
    }

    /**
     * Creates a message object based on the given message text and author.
     *
     * @param message the text of the message
     * @param author  the author of the message (can be either USER or ELIZA)
     * @return a Message object with the specified text and author
     * @throws IllegalArgumentException if the author is not valid (neither USER nor ELIZA)
     */
    public static Message createMessage(final String message, final Author author) {
        switch (author) {
            case USER:
                return new UserMessage(message);
            case ELIZA:
                return new ElizaMessage(message);
            default:
                throw new IllegalArgumentException("Invalid author");
        }
    }
}
