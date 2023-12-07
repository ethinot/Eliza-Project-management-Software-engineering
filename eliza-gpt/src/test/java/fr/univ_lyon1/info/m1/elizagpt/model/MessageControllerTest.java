package fr.univ_lyon1.info.m1.elizagpt.model;

import fr.univ_lyon1.info.m1.elizagpt.controller.MessageController;
import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.message.message_types.ElizaMessage;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageControllerTest {
    private MessageController messageController;

    @BeforeEach
    void setUp()  {
        messageController = new MessageController();
    }

    /**
     * Vérifie que les deux messages d'Eliza sont bien ajoutés.
     */
    @Test
    void testAddDefaultMessages() {
        ObservableList<Message> messages = messageController.getMessages();
        assertTrue(messages.size() >= 2);
        assertInstanceOf(ElizaMessage.class, messages.get(0));
    }

    @Test
    void testSendUserMessage() {
        // Given
        String userMessage = "Bonjour, Eliza.";
        ObservableList<Message> messages = messageController.getMessages();
        int nbMessages = messages.size();

        // When
        messageController.sendUserMessage(userMessage);

        // Then
        assertEquals(nbMessages + 2, messages.size());
        assertTrue(messages.stream().anyMatch(msg -> msg.getText().equals(userMessage)));
    }


    @Test
    void testRemoveMessage() {
        // Given
        String userMessage = "Bonjour, Eliza.";
        ObservableList<Message> messages = messageController.getMessages();
        int nbMessages = messages.size();
        messageController.sendUserMessage(userMessage);
        Message message = messages.get(nbMessages);

        // When
        messageController.removeMessage(message);

        // Then
        assertEquals(nbMessages + 1, messages.size());
        assertTrue(messages.stream().noneMatch(msg -> msg.getText().equals(userMessage)));
    }
}
