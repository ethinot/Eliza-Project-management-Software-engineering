package fr.univ_lyon1.info.m1.elizagpt.view;

import fr.univ_lyon1.info.m1.elizagpt.controller.MessageController;
import javafx.stage.Stage;

public class ChatViewBuilder {
    private Stage stage = new Stage();
    private int width = 500;
    private int height = 500;
    private MessageController messageController = new MessageController();

    public ChatViewBuilder setStage(Stage stage) {
        this.stage = stage;
        return this;
    }

    public ChatViewBuilder setWidth(int width) {
        this.width = width;
        return this;
    }

    public ChatViewBuilder setHeight(int height) {
        this.height = height;
        return this;
    }

    public ChatViewBuilder setMessageController(MessageController messageController) {
        this.messageController = messageController;
        return this;
    }

    public void build() {
        new ChatView(stage, width, height, messageController);
    }
}
