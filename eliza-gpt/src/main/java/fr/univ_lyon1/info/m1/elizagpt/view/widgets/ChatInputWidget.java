package fr.univ_lyon1.info.m1.elizagpt.view.widgets;

import fr.univ_lyon1.info.m1.elizagpt.controller.MessageController;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * Represents a chat input widget that allows users to enter messages.
 */
public class ChatInputWidget implements Widget {
    private final HBox inputBox;
    private final TextField messageField;
    private final Button sendButton;
    private final Label searchingLabel;
    private final MessageController messageController;

    /**
     * Creates a new instance of the ChatInputWidget class.
     * Initializes the inputBox HBox and its components.
     * Calls the addComponents method to add components to the ChatInputWidget.
     */
    public ChatInputWidget(final MessageController messageController) {
        this.messageController = messageController;

        inputBox = new HBox(10);
        messageField = new TextField();
        messageField.setOnKeyPressed(onEnterKeyPressed());
        sendButton = new Button("Envoyer");
        sendButton.setOnAction(e -> sendMessage());
        searchingLabel = new Label("Recherche en cours...");


        inputBox.getStyleClass().add("chat-input-box");
        messageField.getStyleClass().add("chat-input-field");
        sendButton.getStyleClass().add("chat-input-button");
        searchingLabel.getStyleClass().add("searching-label");

        addComponents();

        listenToFilterChange();
    }

    private void listenToFilterChange() {
        messageController.getIsFilterObservable().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                inputBox.getChildren().clear();
                inputBox.getChildren().add(searchingLabel);
            } else {
                inputBox.getChildren().clear();
                inputBox.getChildren().addAll(messageField, sendButton);
            }
        });
    }

    @Override
    public void addComponents() {
        inputBox.getChildren().addAll(messageField, sendButton);
        HBox.setHgrow(messageField, Priority.ALWAYS);
    }

    @Override
    public Node getWidget() {
        return inputBox;
    }

    private EventHandler<KeyEvent> onEnterKeyPressed() {
        return e -> {
            if (e.getCode().toString().equals("ENTER")) {
                sendMessage();
            }
        };
    }

    private void sendMessage() {
        messageController.sendUserMessage(messageField.getText());
        messageField.clear();
    }
}
