package fr.univ_lyon1.info.m1.elizagpt.model.message.message_types;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;

/**
 * Represents a user message.
 * Extends the {@link Message} class.
 */
public class UserMessage extends Message {
    /**
     * Creates a new user message with the given text.
     *
     * @param text the text of the user message
     */
    public UserMessage(final String text) {
        super(text);
    }

    /**
     * Returns USER as the author of the message.
     *
     * @return the author of the message
     */
    @Override
    public Author getAuthor() {
        return Author.USER;
    }
}
