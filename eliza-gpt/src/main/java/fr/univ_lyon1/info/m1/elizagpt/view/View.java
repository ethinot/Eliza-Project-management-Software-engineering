package fr.univ_lyon1.info.m1.elizagpt.view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class View {
    protected final Pane rootContainer;

    protected View(final Stage stage, final int width, final int height, Pane rootContainer) {
        this.rootContainer = rootContainer;
        initialize(stage, width, height);
    }

    private void initialize(Stage stage, int width, int height) {
        stage.setTitle(getName());

        final Scene scene = new Scene(rootContainer, width, height);
        loadCSS(scene);
        
        stage.setScene(scene);
        stage.show();
    }

    protected abstract void loadCSS(Scene scene);

    abstract String getName();

    abstract void addWidgets();
}
