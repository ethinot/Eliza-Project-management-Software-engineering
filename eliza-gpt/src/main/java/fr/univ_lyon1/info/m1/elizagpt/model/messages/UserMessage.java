package fr.univ_lyon1.info.m1.elizagpt.model.messages;

public class UserMessage extends Message {
    public UserMessage(String text) {
        super(text);
    }

    @Override
    Author getAuthor() {
        return Author.USER;
    }
}
