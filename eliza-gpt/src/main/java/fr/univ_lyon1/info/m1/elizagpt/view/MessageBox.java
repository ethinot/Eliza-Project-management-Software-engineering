package fr.univ_lyon1.info.m1.elizagpt.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MessageBox {
    private final String messageText;
    private final String messageStyle;
    private final Pos messagePos;
    private final VBox dialog;

    public MessageBox(String messageText, String messageStyle, Pos messagePos, VBox dialog) {
        this.messageText = messageText;
        this.messageStyle = messageStyle;
        this.messagePos = messagePos;
        this.dialog = dialog;
    }

    public String getMessageText() {
        return this.messageText;
    }

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
