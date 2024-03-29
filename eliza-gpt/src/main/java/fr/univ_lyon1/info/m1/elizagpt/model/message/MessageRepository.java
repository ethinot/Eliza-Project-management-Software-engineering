package fr.univ_lyon1.info.m1.elizagpt.model.message;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for storing messages.
 */
public class MessageRepository {
    /**
     * The list of the application's messages.
     *
     * @see Message
     */
    private final ObservableList<Message> messages = FXCollections.observableArrayList();

    public ObservableList<Message> getMessages() {
        return messages;
    }

    /**
     * Retrieves a list containing all messages in the chat.
     *
     * @return A list of all messages.
     */
    public List<Message> getAllMessages() {
        return new ArrayList<>(messages);
    }

    /**
     * Sends a message to the chat. The message is added to the list of messages and
     * appropriate actions are taken such as notifying observers and sending the message to a bot.
     *
     * @param message the message to be sent
     */
    public void addMessage(final Message message) {
        messages.add(message);
    }

    /**
     * Removed a message to the chat. The message is deleted to the list of messages and
     * appropriate actions are taken such as notifying observers and sending the message to a bot.
     *
     * @param message the message to be removed
     */
    public void deleteMessage(final Message message) {
        messages.remove(message);
    }


    /**
     * Clears all messages in the chat.
     */
    public void clear() {
        messages.clear();
    }

    /**
     * Adds a collection of messages to the chat.
     *
     * @param messages The list of messages to be added.
     */
    public void addMessages(final List<Message> messages) {
        this.messages.addAll(messages);
    }
}
