package fr.univ_lyon1.info.m1.elizagpt.view;

import fr.univ_lyon1.info.m1.elizagpt.controller.MessageController;
import fr.univ_lyon1.info.m1.elizagpt.view.widgets.ChatInputWidget;
import fr.univ_lyon1.info.m1.elizagpt.view.widgets.ChatWidget;
import fr.univ_lyon1.info.m1.elizagpt.view.widgets.SearchInputWidget;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The ChatView class represents a view for displaying the chat window.
 * It extends the View class and implements the addWidgets() method.
 */
public class ChatView extends View {
    private final MessageController messageController;

    /**
     * Creates a new ChatView with specified stage, width, and height.
     *
     * @param stage             the stage to display the ChatView in
     * @param width             the width of the ChatView
     * @param height            the height of the ChatView
     * @param messageController the message controller
     */
    public ChatView(final Stage stage, final int width, final int height,
                    final MessageController messageController) {
        super(stage, width, height, new VBox());

        this.messageController = messageController;
        addWidgets();
    }

    private static void applyChatLayout(final SearchInputWidget searchWidget,
                                        final ChatWidget chatWidget,
                                        final ChatInputWidget inputWidget) {
        VBox.setMargin(searchWidget.getWidget(), new Insets(10, 10, 10, 10));
        VBox.setVgrow(chatWidget.getWidget(), Priority.ALWAYS); // Occupe tout l'espace vertical
        VBox.setMargin(inputWidget.getWidget(), new Insets(10, 10, 10, 10));
    }

    @Override
    protected void loadCSS(final Scene scene) {
        CssLoader.CHAT_WIDGET.load(scene);
        CssLoader.CHAT_INPUT_WIDGET.load(scene);
        CssLoader.SEARCH_INPUT_WIDGET.load(scene);
    }

    @Override
    String getName() {
        return "ElizaGPT - Chat";
    }

    @Override
    public void addWidgets() {
        SearchInputWidget searchWidget = new SearchInputWidget(messageController);
        ChatWidget chatWidget = new ChatWidget(messageController);
        ChatInputWidget inputWidget = new ChatInputWidget(messageController);

        applyChatLayout(searchWidget, chatWidget, inputWidget);

        addWidget(searchWidget.getWidget());
        addWidget(chatWidget.getWidget());
        addWidget(inputWidget.getWidget());
    }
}
