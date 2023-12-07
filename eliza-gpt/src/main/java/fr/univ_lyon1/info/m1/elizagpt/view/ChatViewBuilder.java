package fr.univ_lyon1.info.m1.elizagpt.view;

import fr.univ_lyon1.info.m1.elizagpt.controller.MessageController;
import javafx.stage.Stage;

/**
 * This class is responsible for building the ChatView by setting
 * its properties and creating an instance of it.
 */
public class ChatViewBuilder {
    private Stage stage = new Stage();
    private int width = 500;
    private int height = 500;
    private MessageController messageController = new MessageController();

    /**
     * Sets the stage for the ChatViewBuilder.
     *
     * @param stage the stage to be set
     * @return the ChatViewBuilder instance
     */
    public ChatViewBuilder setStage(final Stage stage) {
        this.stage = stage;
        return this;
    }

    /**
     * Sets the width of the ChatViewBuilder.
     *
     * @param width the width to be set
     * @return the ChatViewBuilder instance
     */
    public ChatViewBuilder setWidth(final int width) {
        this.width = width;
        return this;
    }

    /**
     * Sets the height of the ChatViewBuilder.
     *
     * @param height the height to be set
     * @return the ChatViewBuilder instance
     */
    public ChatViewBuilder setHeight(final int height) {
        this.height = height;
        return this;
    }

    /**
     * Sets the message controller for the ChatViewBuilder.
     *
     * @param messageController the message controller to be set
     * @return the ChatViewBuilder instance
     */
    public ChatViewBuilder setMessageController(final MessageController messageController) {
        this.messageController = messageController;
        return this;
    }

    /**
     * Builds a ChatView by setting its properties and creating an instance of it.
     */
    public void build() {
        new ChatView(stage, width, height, messageController);
    }
}
