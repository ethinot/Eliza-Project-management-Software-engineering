package fr.univ_lyon1.info.m1.elizagpt.view.widgets;

import fr.univ_lyon1.info.m1.elizagpt.model.messages.Message;
import fr.univ_lyon1.info.m1.elizagpt.view.widgets.components.MessageBox;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ChatWidget implements Widget {
    private final ScrollPane dialogScroll;
    private final VBox dialog;

    public ChatWidget() {
        this.dialog = new VBox(10);
        this.dialogScroll = new ScrollPane();
        this.initScrollPane();

        this.addComponents();
    }

    private void initScrollPane() {
        dialogScroll.setContent(dialog);
        dialogScroll.vvalueProperty().bind(dialog.heightProperty());
        dialogScroll.setFitToWidth(true);
    }

    private void addMessage(Message message) {
        MessageBox messageBox = new MessageBox(message, dialog);
        dialog.getChildren().add(messageBox.create());
    }

    @Override
    public void addComponents() {
        // TODO
    }

    @Override
    public Node getWidget() {
        return dialogScroll;
    }
}
