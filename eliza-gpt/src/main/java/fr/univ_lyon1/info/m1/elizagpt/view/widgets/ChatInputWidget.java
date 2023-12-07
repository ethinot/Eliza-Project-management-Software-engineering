package fr.univ_lyon1.info.m1.elizagpt.view.widgets;

import fr.univ_lyon1.info.m1.elizagpt.controller.MessageController;
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
    public static final int HBOX_SPACING = 10;
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

        inputBox = new HBox(HBOX_SPACING);
        messageField = new TextField();
        sendButton = new Button("Envoyer");
        searchingLabel = new Label("Recherche en cours...");

        setEventListeners();
        setStyleClasses();
        addComponents();
        listenToFilterChange();
    }


    private void setStyleClasses() {
        inputBox.getStyleClass().add("chat-input-box");
        messageField.getStyleClass().add("chat-input-field");
        sendButton.getStyleClass().add("chat-input-button");
        searchingLabel.getStyleClass().add("searching-label");
    }

    private void listenToFilterChange() {
        messageController.getFilterStatusProperty()
                .addListener((observable, oldValue, newValue) ->
                        updateUIState(Boolean.TRUE.equals(newValue)
                                ? InputState.SEARCHING : InputState.MESSAGE_INPUT));
    }

    private void setEventListeners() {
        messageField.setOnKeyPressed(this::onEnterKeyPressed);
        sendButton.setOnAction(e -> sendMessage());
    }

    private void updateUIState(final InputState newState) {
        inputBox.getChildren().clear();
        switch (newState) {
            case SEARCHING:
                inputBox.getChildren().add(searchingLabel);
                break;
            case MESSAGE_INPUT:
                inputBox.getChildren().addAll(messageField, sendButton);
                break;
            default:
                throw new IllegalArgumentException("Unknown state");
        }
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

    private void onEnterKeyPressed(final KeyEvent e) {
        if (e.getCode().toString().equals("ENTER")) {
            sendMessage();
        }
    }

    private void sendMessage() {
        messageController.sendUserMessage(messageField.getText());
        messageField.clear();
    }

    private enum InputState {
        SEARCHING, MESSAGE_INPUT
    }
}
