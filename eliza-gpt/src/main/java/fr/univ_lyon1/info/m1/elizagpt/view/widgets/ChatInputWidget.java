package fr.univ_lyon1.info.m1.elizagpt.view.widgets;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * Represents a chat input widget that allows users to enter messages.
 */
public class ChatInputWidget implements Widget {
    private final HBox inputBox;
    private final TextField messageField;
    private final Button sendButton;

    /**
     * Creates a new instance of the ChatInputWidget class.
     * Initializes the inputBox HBox and its components.
     * Calls the addComponents method to add components to the ChatInputWidget.
     */
    public ChatInputWidget() {
        this.inputBox = new HBox(10);
        this.messageField = new TextField();
        this.sendButton = new Button("Send");

        this.inputBox.getStyleClass().add("chat-input-box");
        this.messageField.getStyleClass().add("chat-input-field");
        this.sendButton.getStyleClass().add("chat-input-button");

        this.addComponents();
    }

    @Override
    public void addComponents() {
        inputBox.getChildren().addAll(messageField, sendButton);
        HBox.setHgrow(messageField, Priority.ALWAYS);
    }

    /**
     * Get the message field for user input.
     *
     * @return The message field.
     */
    public TextField getMessageField() {
        return messageField;
    }

    /**
     * Get the send button to submit messages.
     *
     * @return The send button.
     */
    public Button getSendButton() {
        return sendButton;
    }

    @Override
    public Node getWidget() {
        return inputBox;
    }
}
