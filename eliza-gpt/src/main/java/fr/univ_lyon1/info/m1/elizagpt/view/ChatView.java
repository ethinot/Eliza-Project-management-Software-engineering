package fr.univ_lyon1.info.m1.elizagpt.view;

import fr.univ_lyon1.info.m1.elizagpt.view.widgets.ChatWidget;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class ChatView extends View {
    public ChatView(final Stage stage, final int width, final int height) {
        super(stage, width, height, new VBox());

        addWidgets();
    }

    @Override
    protected void loadCSS(Scene scene) {
        String css = Objects.requireNonNull(getClass().getResource("/style/ChatView.css")).toExternalForm();
        scene.getStylesheets().add(css);
    }

    @Override
    String getName() {
        return "ElizaGPT - Chat";
    }

    @Override
    public void addWidgets() {
        ChatWidget chatWidget = new ChatWidget();
        this.rootContainer.getChildren().add(chatWidget.getWidget());
    }
}
