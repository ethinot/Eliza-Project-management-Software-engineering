package fr.univ_lyon1.info.m1.elizagpt.model.messages;

public class ElizaMessage extends Message {
    public ElizaMessage(String text) {
        super(text);
    }

    @Override
    public Author getAuthor() {
        return Author.ELIZA;
    }
}
