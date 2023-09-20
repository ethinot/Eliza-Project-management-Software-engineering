package fr.univ_lyon1.info.m1.elizagpt.model.messages;

/**
 * A class representing a message from the Eliza chatbot.
 * It extends the Message class.
 */
public class ElizaMessage extends Message {
    /**
     * Constructs a new ElizaMessage object with the specified text.
     *
     * @param text the text of the message
     */
    public ElizaMessage(final String text) {
        super(text);
    }

    @Override
    public Author getAuthor() {
        return Author.ELIZA;
    }
}
