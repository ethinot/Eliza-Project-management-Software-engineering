package fr.univ_lyon1.info.m1.elizagpt.controller;

import fr.univ_lyon1.info.m1.elizagpt.Singleton;
import fr.univ_lyon1.info.m1.elizagpt.model.grammar.ElizaResponseProcessor;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.ElizaMessage;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.UserMessage;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for sending messages to the model.
 */
public class MessageController implements Observable, Singleton {
    private static final List<InvalidationListener> LISTENERS = new ArrayList<>();

    private static MessageController instance;

    // TODO: use the MessageRepository class instead
    private static Message lastMessage;

    /**
     * Gets the singleton instance of the MessageController class.
     *
     * @return The singleton instance of the MessageController class.
     */
    public static MessageController getInstance() {
        if (instance == null) {
            instance = new MessageController();
        }
        return instance;
    }

    /**
     * Sends a message to Eliza.
     *
     * @param message The message to be sent to Eliza.
     */
    public static void sendElizaMessage(final String message) {
        lastMessage = new ElizaMessage(message);
        notifyListeners();
    }

    public static Message getLastMessage() {
        return lastMessage;
    }

    /**
     * Sends a message to the model.
     *
     * @param message the message to be sent
     */
    public static void sendUserMessage(final String message) {
        lastMessage = new UserMessage(message);
        notifyListeners();
        ElizaResponseProcessor.process(message);
    }

    private static void notifyListeners() {
        for (InvalidationListener listener : LISTENERS) {
            listener.invalidated(MessageController.getInstance());
        }
    }

    @Override
    public void addListener(final InvalidationListener listener) {
        LISTENERS.add(listener);
    }

    @Override
    public void removeListener(final InvalidationListener listener) {
        LISTENERS.remove(listener);
    }
}
