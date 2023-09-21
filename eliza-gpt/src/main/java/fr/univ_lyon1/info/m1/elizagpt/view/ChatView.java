package fr.univ_lyon1.info.m1.elizagpt.view;

import fr.univ_lyon1.info.m1.elizagpt.view.widgets.ChatWidget;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * The ChatView class represents a view for displaying the chat window.
 * It extends the View class and implements the addWidgets() method.
 */
public class ChatView extends View {
    /**
     * Creates a new ChatView with specified stage, width, and height.
     *
     * @param stage  the stage to display the ChatView in
     * @param width  the width of the ChatView
     * @param height the height of the ChatView
     */
    public ChatView(final Stage stage, final int width, final int height) {
        super(stage, width, height, new VBox());

        addWidgets();
    }

    @Override
    protected void loadCSS(final Scene scene) {
        // TODO : refactor this
        String css = Objects.requireNonNull(getClass()
                .getResource("/style/ChatView.css")).toExternalForm();
        scene.getStylesheets().add(css);
    }

    @Override
    String getName() {
        return "ElizaGPT - Chat";
    }

    @Override
    public void addWidgets() {
        ChatWidget chatWidget = new ChatWidget();
        this.getRootContainer().getChildren().add(chatWidget.getWidget());
    }
}
