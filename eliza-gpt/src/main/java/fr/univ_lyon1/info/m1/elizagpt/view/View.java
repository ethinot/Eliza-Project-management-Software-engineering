package fr.univ_lyon1.info.m1.elizagpt.view;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * The View class is an abstract class that represents a
 * graphical view in a JavaFX application.
 * It provides a framework for initializing the stage,
 * setting the scene, and loading CSS styles.
 * Subclasses of View must implement the abstract
 * methods to customize the view's behavior and appearance.
 */
public abstract class View {
    private final Pane rootContainer;

    protected View(final Stage stage, final int width, final int height, final Pane rootContainer) {
        this.rootContainer = rootContainer;
        initialize(stage, width, height);
    }

    private void initialize(final Stage stage, final int width, final int height) {
        stage.setTitle(getName());

        final Scene scene = new Scene(rootContainer, width, height);
        loadCSS(scene);

        stage.setScene(scene);
        stage.show();
    }

    protected abstract void loadCSS(Scene scene);
    abstract String getName();

    abstract void addWidgets();

    protected void addWidget(final Node widget) {
        rootContainer.getChildren().add(widget);
    }
}
