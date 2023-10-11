package fr.univ_lyon1.info.m1.elizagpt.view;

import javafx.scene.Scene;

import java.util.Objects;

public enum CssLoader {
    CHAT_WIDGET("ChatWidget.css"),
    CHAT_INPUT_WIDGET("ChatInputWidget.css"),
    SEARCH_INPUT_WIDGET("SearchInputWidget.css");

    private final String cssPath;

    CssLoader(String cssPath) {
        String pathPrefix = "/style/";
        this.cssPath = pathPrefix + cssPath;
    }

    public void load(Scene scene) {
        String css = Objects.requireNonNull(CssLoader.class.getResource(cssPath)).toExternalForm();
        scene.getStylesheets().add(css);
    }
}
