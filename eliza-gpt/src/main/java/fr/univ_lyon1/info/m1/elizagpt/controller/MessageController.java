package fr.univ_lyon1.info.m1.elizagpt.controller;

import fr.univ_lyon1.info.m1.elizagpt.model.grammar.ElizaResponseProcessor;

import java.util.logging.Logger;

/**
 * This class is responsible for sending messages to the model.
 */
public class MessageController {
    // notify the view that a message has been received
    public static void sendElizaMessage(final String message) {
        Logger.getGlobal().info("Eliza message: " + message);
    }

    /**
     * Sends a message to the model.
     *
     * @param message the message to be sent
     */
    public static void sendUserMessage(final String message) {
        ElizaResponseProcessor.process(message);
    }
}
