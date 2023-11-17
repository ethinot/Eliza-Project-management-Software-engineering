package fr.univ_lyon1.info.m1.elizagpt.view.widgets;

import fr.univ_lyon1.info.m1.elizagpt.controller.MessageController;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.Message;
import fr.univ_lyon1.info.m1.elizagpt.view.widgets.components.MessageBox;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a chat widget that displays the chat history.
 */
public class ChatWidget implements Widget {
    private final Map<Message, Node> messageNodeMap = new HashMap<>();
    private final ScrollPane dialogScroll;
    private final VBox dialog;
    private final MessageController messageController;

    /**
     * Creates a new instance of the ChatWidget class.
     * Initializes the dialog VBox and dialogScroll ScrollPane.
     * Calls the initScrollPane method to set up the properties of the ScrollPane.
     * Calls the addComponents method to add components to the ChatWidget.
     */
    public ChatWidget(final MessageController messageController) {
        this.messageController = messageController;

        dialog = new VBox(10);
        dialogScroll = new ScrollPane();

        initScrollPane();
        addComponents();
        initMessages();

        listenToMessagesChanges();
    }

    private void listenToMessagesChanges() {
        this.messageController.getMessagesObservableList().addListener(this::processMessageChange);
    }

    private void processMessageChange(final ListChangeListener.Change<? extends Message> c) {
        while (c.next()) {
            if (c.wasAdded()) {
                for (Message message : c.getAddedSubList()) {
                    addMessage(message);
                }
            }

            if (c.wasRemoved()) {
                for (Message message : c.getRemoved()) {
                    Node node = messageNodeMap.get(message);
                    dialog.getChildren().remove(node);
                }
            }
        }
    }

    private void initMessages() {
        this.messageController.getMessagesObservableList().forEach(this::addMessage);
    }

    private void initScrollPane() {
        dialogScroll.setContent(dialog);
        dialogScroll.setPadding(new Insets(10));
        dialogScroll.vvalueProperty().bind(dialog.heightProperty());
        dialogScroll.setFitToWidth(true);
        dialogScroll.getStyleClass().add("chat-dialog-scroll");
    }

    private void addMessage(final Message message) {
        MessageBox messageBox = new MessageBox(message, messageController);

        Node node = messageBox.create();
        messageNodeMap.put(message, node);
        dialog.getChildren().add(node);
    }

    @Override
    public void addComponents() {
    }

    @Override
    public Node getWidget() {
        return dialogScroll;
    }
}
