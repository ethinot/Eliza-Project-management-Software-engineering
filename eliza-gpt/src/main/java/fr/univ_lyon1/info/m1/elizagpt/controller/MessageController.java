package fr.univ_lyon1.info.m1.elizagpt.controller;

import fr.univ_lyon1.info.m1.elizagpt.model.Model;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.ElizaMessage;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.MessageRepository;
import javafx.collections.ObservableList;

import java.io.IOException;

/**
 * This class is responsible for sending messages to the model.
 */
public class MessageController {
    private final Model model = new Model();

    public MessageController() throws IOException {
        addDefaultMessages();
    }

    private void addDefaultMessages() {
        model.messageRepository.sendMessage(new ElizaMessage("Bonjour, je suis Eliza, votre thérapeute virtuelle."));
        model.messageRepository.sendMessage(new ElizaMessage("Comment allez-vous ?"));
    }

    public ObservableList<Message> getMessagesObservableList() {
        return MessageRepository.getObservableList();
    }

    /**
     * Sends a message to the model.
     *
     * @param message the message to be sent
     */
    public void sendUserMessage(final String message) {
        model.elizaResponseProcessor.process(message);
    }

    public void removeMessage(final Message message) {
        model.messageRepository.removeMessage(message);
    }
}
