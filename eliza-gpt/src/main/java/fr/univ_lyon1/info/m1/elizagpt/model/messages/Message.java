package fr.univ_lyon1.info.m1.elizagpt.model.messages;

public abstract class Message {
    private final String text;

    protected Message(String text) {
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
