package fr.univ_lyon1.info.m1.elizagpt.model.messages;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Responsible for storing messages.
 */
public class MessageRepository {
    /**
     * The list of the application's messages.
     *
     * @see Message
     */
    private static final ObservableList<Message> MESSAGES = FXCollections.observableArrayList();

    public static ObservableList<Message> getObservableList() {
        return MESSAGES;
    }

    /**
     * Sends a message to the chat. The message is added to the list of messages and
     * appropriate actions are taken such as notifying observers and sending the message to a bot.
     *
     * @param message the message to be sent
     */
    public void sendMessage(final Message message) {
        MESSAGES.add(message);
    }

    /**
     * Removed a message to the chat. The message is deleted to the list of messages and
     * appropriate actions are taken such as notifying observers and sending the message to a bot.
     *
     * @param message the message to be removed
     */
    public void removeMessage(final Message message) {
        MESSAGES.remove(message);
    }
}
