package fr.univ_lyon1.info.m1.elizagpt.view.widgets;

import fr.univ_lyon1.info.m1.elizagpt.controller.MessageController;
import fr.univ_lyon1.info.m1.elizagpt.model.researches.research_types.Research;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * Represents a search input widget with label, cancel, and search button.
 */
public class SearchInputWidget implements Widget {
    private final HBox inputBox;
    private final Label searchLabel;
    private final TextField searchField;
    private final ComboBox<Research> comboBox;
    private final Button searchButton;
    private final Button cancelButton;
    private final MessageController messageController;

    /**
     * Creates a new instance of the SearchInputWidget class.
     * Initializes the inputBox HBox and its components.
     * Calls the addComponents method to add components to the SearchInputWidget.
     */
    public SearchInputWidget(final MessageController messageController) {
        this.messageController = messageController;
        inputBox = new HBox(10);
        searchLabel = new Label("Rechercher");
        searchField = new TextField();
        searchField.setPromptText("Rechercher un message");
        comboBox = new ComboBox<>();
        comboBox.setItems(messageController.getResearchObservableList());
        searchButton = new Button("Appliquer");
        searchButton.setOnAction(event -> onSearchButtonClicked());
        cancelButton = new Button("Annuler");
        cancelButton.setOnAction(event -> onCancelButtonClicked());

        inputBox.getStyleClass().add("search-input-box");
        searchLabel.getStyleClass().add("search-input-label");
        searchField.getStyleClass().add("search-input-field");
        searchButton.getStyleClass().add("search-input-button");
        cancelButton.getStyleClass().add("cancel-input-button");

        addComponents();
    }

    @Override
    public void addComponents() {
        HBox.setHgrow(searchField, Priority.ALWAYS);
        inputBox.setAlignment(Pos.CENTER_LEFT);
        inputBox.setPadding(new Insets(5, 5, 5, 5));


        inputBox.getChildren().addAll(searchLabel, searchField, comboBox, searchButton);
    }

    @Override
    public Node getWidget() {
        return inputBox;
    }

    private void onSearchButtonClicked() {
        if (comboBox.getValue() != null && searchField.getText() != null) {
            inputBox.getChildren().remove(searchButton);
            inputBox.getChildren().add(cancelButton);
            comboBox.getValue().setSearchedString(searchField.getText());
            messageController.search(searchField.getText(), comboBox.getValue());
        }
    }

    private void onCancelButtonClicked() {
        if (comboBox.getValue() != null) {
            inputBox.getChildren().remove(cancelButton);
            inputBox.getChildren().add(searchButton);
            messageController.undoSearch(comboBox.getValue());
        }
    }
}
