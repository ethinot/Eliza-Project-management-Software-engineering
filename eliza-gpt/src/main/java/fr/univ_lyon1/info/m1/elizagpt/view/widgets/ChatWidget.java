package fr.univ_lyon1.info.m1.elizagpt.view.widgets;

import fr.univ_lyon1.info.m1.elizagpt.controller.MessageController;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.ElizaMessage;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.UserMessage;
import fr.univ_lyon1.info.m1.elizagpt.view.widgets.components.MessageBox;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * Represents a chat widget that displays the chat history.
 */
public class ChatWidget implements Widget {
    private final ScrollPane dialogScroll;
    private final VBox dialog;

    /**
     * Creates a new instance of the ChatWidget class.
     * Initializes the dialog VBox and dialogScroll ScrollPane.
     * Calls the initScrollPane method to set up the properties of the ScrollPane.
     * Calls the addComponents method to add components to the ChatWidget.
     */
    public ChatWidget() {
        dialog = new VBox(10);
        dialogScroll = new ScrollPane();
        initScrollPane();

        addComponents();

        MessageController.getInstance().addListener(observable -> {
            addMessage(MessageController.getLastMessage());
        });
    }

    private void initScrollPane() {
        dialogScroll.setContent(dialog);
        dialogScroll.setPadding(new Insets(10));
        dialogScroll.vvalueProperty().bind(dialog.heightProperty());
        dialogScroll.setFitToWidth(true);
        dialogScroll.getStyleClass().add("chat-dialog-scroll");
    }

    private void addMessage(final Message message) {
        MessageBox messageBox = new MessageBox(message, dialog);
        dialog.getChildren().add(messageBox.create());
    }

    @Override
    public void addComponents() {
        // TODO
        addMessage(new UserMessage("Hello"));
        addMessage(new ElizaMessage("Hello :)"));
    }

    @Override
    public Node getWidget() {
        return dialogScroll;
    }
}
