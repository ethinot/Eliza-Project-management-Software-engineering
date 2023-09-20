package fr.univ_lyon1.info.m1.elizagpt.model.messages;


import java.util.ArrayList;
import java.util.Collection;

public class MessageRepository {
    private static final Collection<Message> messages = new ArrayList<>();


    public void sendMessage(String message) {
        UserMessage userMessage = new UserMessage(message);
        messages.add(userMessage);
        // TODO : notify observers

        // TODO : send message to bot
        // ElizaResponseProcessor.processMessage(message);
    }
}