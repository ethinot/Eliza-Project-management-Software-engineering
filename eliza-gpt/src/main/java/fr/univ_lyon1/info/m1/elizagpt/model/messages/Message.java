package fr.univ_lyon1.info.m1.elizagpt.model.messages;

/**
 * The Message class represents a message object.
 * It is an abstract class, so it cannot be instantiated.
 * The class is responsible for storing the text and the author of the message.
 * The author is an enum, so it can only be USER or ELIZA.
 * This class is extended by the UserMessage and ElizaMessage classes.
 */
public abstract class Message {
    private final String text;

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

    abstract Author getAuthor();

    /**
     * Check if the message is from the user.
     *
     * @return true if the message is from the user, false otherwise.
     */
    public boolean isFromUser() {
        return getAuthor() == Author.USER;
    }
}
