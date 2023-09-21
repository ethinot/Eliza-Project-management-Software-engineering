package fr.univ_lyon1.info.m1.elizagpt.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Represents a message box that displays a message with specified style and alignment.
 */
public class MessageBox {
    private final String messageText;
    private final String messageStyle;
    private final Pos messagePos;
    private final VBox dialog;

    /**
     * Constructs a MessageBox object with the given message, style, position, and dialog.
     *
     * @param messageText  the text of the message
     * @param messageStyle the style of the message
     * @param messagePos   the position of the message
     * @param dialog       the dialog containing the message
     */
    public MessageBox(final String messageText,
                      final String messageStyle,
                      final Pos messagePos,
                      final VBox dialog) {
        this.messageText = messageText;
        this.messageStyle = messageStyle;
        this.messagePos = messagePos;
        this.dialog = dialog;
    }

    /**
     * Returns the text of the message stored in the MessageBox object.
     *
     * @return the text of the message
     */
    public String getMessageText() {
        return this.messageText;
    }

    /**
     * Creates and returns the HBox message container
     * with the specified style, content, and position.
     *
     * @return the created HBox message container
     */
    public HBox createMessageContainer() {
        HBox hBox = new HBox();
        final Label label = new Label(this.messageText);
        hBox.getChildren().add(label);
        label.setStyle(this.messageStyle);
        hBox.setAlignment(this.messagePos);
        hBox.setOnMouseClicked(e -> {
            dialog.getChildren().remove(hBox);
        });

        return hBox;
    }
}
