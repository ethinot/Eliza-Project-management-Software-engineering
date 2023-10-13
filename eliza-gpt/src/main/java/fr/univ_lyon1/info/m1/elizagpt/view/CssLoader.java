package fr.univ_lyon1.info.m1.elizagpt.view;

import javafx.scene.Scene;

import java.util.Objects;

/**
 * This enum facilitates the loading of CSS files.
 */
public enum CssLoader {
    CHAT_WIDGET("ChatWidget.css"),
    CHAT_INPUT_WIDGET("ChatInputWidget.css"),
    SEARCH_INPUT_WIDGET("SearchInputWidget.css");

    private final String cssPath;

    CssLoader(final String cssPath) {
        String pathPrefix = "/style/";
        this.cssPath = pathPrefix + cssPath;
    }

    /**
     * Loads the CSS associated with this enumeration value into the specified JavaFX scene.
     *
     * @param scene The JavaFX scene to apply the style to.
     */
    public void load(final Scene scene) {
        String css = Objects.requireNonNull(CssLoader.class.getResource(cssPath)).toExternalForm();
        scene.getStylesheets().add(css);
    }
}
