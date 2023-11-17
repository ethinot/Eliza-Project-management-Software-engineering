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

    /**
     * Constructor of the MessageController class.
     *
     * @throws IOException if
     */
    public MessageController() throws IOException {
        addDefaultMessages();
    }

    private void addDefaultMessages() {
        model.getMessageRepository().sendMessage(new ElizaMessage(
                "Bonjour, je suis Eliza, votre thérapeute virtuelle."));
        model.getMessageRepository().sendMessage(new ElizaMessage("Comment allez-vous ?"));
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
        model.getElizaResponseProcessor().process(message);
    }

    /**
     * Remove a message to the model.
     *
     * @param message the message to be sent
     */
    public void removeMessage(final Message message) {
        model.getMessageRepository().removeMessage(message);
    }
}
