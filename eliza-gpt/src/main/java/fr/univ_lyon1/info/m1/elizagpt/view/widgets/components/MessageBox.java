package fr.univ_lyon1.info.m1.elizagpt.view.widgets.components;

import fr.univ_lyon1.info.m1.elizagpt.model.messages.Message;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class MessageBox {
    private static final String BASE_STYLE = "-fx-padding: 8px; -fx-margin: 5px; -fx-background-radius: 5px; -fx-background-color: ";
    private static final String USER_COLOR = "#A0E0A0";
    private static final String ELIZA_COLOR = "#A0A0E0";
    private static final String USER_STYLE = BASE_STYLE + USER_COLOR + ";";
    private static final String ELIZA_STYLE = BASE_STYLE + ELIZA_COLOR + ";";
    private static final Pos USER_POS = Pos.CENTER_RIGHT;
    private static final Pos ELIZA_POS = Pos.CENTER_LEFT;
    private final Message message;
    private final VBox dialog;

    public MessageBox(Message message, VBox dialog) {
        this.message = message;
        this.dialog = dialog;
    }

    public HBox create() {
        HBox hBox = createBoxComponent();

        hBox.setOnMouseClicked(e -> removeMessageBox(hBox));

        return hBox;
    }

    private void removeMessageBox(HBox hBox) {
        // TODO : appeler le controller pour supprimer le message
        this.dialog.getChildren().remove(hBox);
    }

    private HBox createBoxComponent() {
        HBox hBox = new HBox();
        final Label label = createLabel();

        hBox.getChildren().add(label);
        hBox.setAlignment(getAlignment());

        return hBox;
    }

    private Label createLabel() {
        Label label = new Label(this.message.getText());
        label.setStyle(getStyle());
        return label;
    }

    private String getStyle() {
        return this.message.isFromUser() ? USER_STYLE : ELIZA_STYLE;
    }

    private Pos getAlignment() {
        return this.message.isFromUser() ? USER_POS : ELIZA_POS;
    }
}
