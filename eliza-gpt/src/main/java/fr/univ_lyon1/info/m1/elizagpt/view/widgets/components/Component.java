package fr.univ_lyon1.info.m1.elizagpt.view.widgets.components;

import javafx.scene.layout.Pane;

/**
 * Interface representing a component.
 */
public interface Component {
    /**
     * Creates and returns a Pane containing the component.
     *
     * @return the newly created component
     */
    Pane create();
}
