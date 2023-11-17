package fr.univ_lyon1.info.m1.elizagpt;

import fr.univ_lyon1.info.m1.elizagpt.controller.MessageController;
import fr.univ_lyon1.info.m1.elizagpt.view.ChatView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class for the application (structure imposed by JavaFX).
 */
public class ElizaGptMain extends Application {

    /**
     * A main method in case the user launches the application using
     * App as the main class.
     */
    public static void main(final String[] args) {
        Application.launch(args);
    }

    /**
     * With javafx, start() is called when the application is launched.
     */
    @Override
    public void start(final Stage stage) throws IOException {
        MessageController messageController = new MessageController();

        new ChatView(stage, 800, 600, messageController);
        new ChatView(new Stage(), 800, 600, messageController);
    }
}
