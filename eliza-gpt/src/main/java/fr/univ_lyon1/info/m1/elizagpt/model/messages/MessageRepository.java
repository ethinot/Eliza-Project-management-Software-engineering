package fr.univ_lyon1.info.m1.elizagpt.model.messages;


import java.util.ArrayList;
import java.util.Collection;

/**
 * Responsible for storing messages.
 */
public class MessageRepository {
    /**
     * The list of the application's messages.
     *
     * @see Message
     */
    private static final Collection<Message> MESSAGES = new ArrayList<>();

    /**
     * Function that check if the collection of messages is empty.
     *
     * @return True if there is no messages stored.
     */
    public static boolean isEmpty() {
        return MESSAGES.isEmpty();
    }

    /**
     * Sends a message to the chat. The message is added to the list of messages and
     * appropriate actions are taken such as notifying observers and sending the message to a bot.
     *
     * @param message the message to be sent
     */
    public void sendMessage(final String message) {
        UserMessage userMessage = new UserMessage(message);
        MESSAGES.add(userMessage);
        // TODO : notify observers (C'est le controller qui la fait ?)
        // TODO : send message to bot
        // ElizaResponseProcessor.processMessage(message);
    }

    /**
     * Function that return the last message of the message repository.
     *
     * @param messageType The type of the last messages among ElizaMessage and UserMessage
     * @param <T> final the of message
     * @return The type of the last messages among ElizaMessage and UserMessage
     */
    public static <T extends Message> T getLastMessage(final Class<T> messageType) {
        if (isEmpty()) {
            return null;
        }

        T lastMessage = null;
        for (Message message : MESSAGES) {
            if (messageType.isInstance(message)) {
                lastMessage = messageType.cast(message);
            }
        }
        return lastMessage;
    }
}
