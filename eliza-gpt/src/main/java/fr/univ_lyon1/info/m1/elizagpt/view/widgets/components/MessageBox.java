package fr.univ_lyon1.info.m1.elizagpt.view.widgets.components;

import fr.univ_lyon1.info.m1.elizagpt.controller.MessageController;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.Message;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


/**
 * Represents a message box that displays a message with specified style and alignment.
 */
public class MessageBox implements Component {
    private static final Pos USER_POS = Pos.CENTER_RIGHT;
    private static final Pos ELIZA_POS = Pos.CENTER_LEFT;
    private final Message message;
    private final MessageController messageController;

    /**
     * Creates a new instance of the MessageBox class.
     *
     * @param message           The Message object associated with the MessageBox.
     * @param messageController the MessageBox will be displayed in.
     */
    public MessageBox(final Message message, final MessageController messageController) {
        this.message = message;
        this.messageController = messageController;
    }

    /**
     * Creates a new HBox object to represent a MessageBox.
     *
     * @return The created HBox object.
     */
    @Override
    public HBox create() {
        HBox hBox = createBoxComponent();
        // Todo : ajouter la petite croix moche pour delete
        // Todo : Changer la manière de remove (method remove... cannot be applied to given types)
        //hBox.setOnMouseClicked(e -> removeMessageBox(hBox));

        return hBox;
    }

    private void removeMessageBox() {
        messageController.removeMessage(message);
    }

    private HBox createBoxComponent() {
        HBox hBox = new HBox();
        final Label label = createLabel();

        hBox.getChildren().add(label);
        hBox.setAlignment(getAlignment());

        return hBox;
    }

    private Label createLabel() {
        Label label = new Label(message.getText());
        addCSS(label);
        return label;
    }

    private void addCSS(final Label label) {
        label.getStyleClass().add("message-box");
        label.getStyleClass().add(message.isFromUser()
                ? "user-message-box" : "eliza-message-box");
    }

    private Pos getAlignment() {
        return message.isFromUser() ? USER_POS : ELIZA_POS;
    }
}
