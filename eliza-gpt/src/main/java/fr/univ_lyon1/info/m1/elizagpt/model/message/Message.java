package fr.univ_lyon1.info.m1.elizagpt.model.message;

import fr.univ_lyon1.info.m1.elizagpt.model.message.message_types.Author;

import java.util.UUID;

/**
 * The Message class represents a message object.
 * It is an abstract class, so it cannot be instantiated.
 * The class is responsible for storing the text and the author of the message.
 * The author is an enum, so it can only be USER or ELIZA.
 * This class is extended by the UserMessage and ElizaMessage classes.
 * Using the design pattern factory
 */
public abstract class Message {
    private final String text;
    private final UUID uuid = UUID.randomUUID();

    protected Message(final String text) {
        this.text = text;
    }

    /**
     * Get the text of the message.
     *
     * @return the text of the message.
     */
    public String getText() {
        return text;
    }

    /**
     * Get the author of the message.
     *
     * @return the author of the message.
     */
    public abstract Author getAuthor();

    /**
     * Check if the message is from the user.
     *
     * @return true if the message is from the user, false otherwise.
     */
    public boolean isFromUser() {
        return getAuthor() == Author.USER;
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Message)) {
            return false;
        }

        Message message = (Message) o;

        return uuid.equals(message.uuid);
    }
}
