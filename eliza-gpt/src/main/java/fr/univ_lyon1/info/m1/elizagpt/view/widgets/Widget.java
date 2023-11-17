package fr.univ_lyon1.info.m1.elizagpt.view.widgets;

import javafx.scene.Node;

/**
 * This interface represents a widget.
 * A widget is an element that contains components and can be added to a view.
 */
public interface Widget {
    /**
     * Adds components to the current widget.
     */
    void addComponents();

    /**
     * Get the widget node.
     *
     * @return The widget node.
     */
    Node getWidget();
}
