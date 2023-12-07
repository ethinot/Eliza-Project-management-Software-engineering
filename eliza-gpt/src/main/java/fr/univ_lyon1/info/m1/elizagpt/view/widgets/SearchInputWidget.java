package fr.univ_lyon1.info.m1.elizagpt.view.widgets;

import fr.univ_lyon1.info.m1.elizagpt.controller.MessageController;
import fr.univ_lyon1.info.m1.elizagpt.model.research.ResearchStrategy;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.logging.Logger;

/**
 * Represents a search input widget with label, cancel, and search button.
 */
public class SearchInputWidget implements Widget {
    private final HBox inputBox;
    private final Label searchLabel;
    private final TextField searchField;
    private final ComboBox<ResearchStrategy> comboBox;
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
        comboBox = new ComboBox<>();
        searchButton = new Button("Appliquer");
        cancelButton = new Button("Annuler");

        initializeSearchInputs(messageController);
        initializeButtonActions();
        setStyleClasses();
        addComponents();
        listenToFilterChange();
    }

    private void setStyleClasses() {
        inputBox.getStyleClass().add("search-input-box");
        searchLabel.getStyleClass().add("search-input-label");
        searchField.getStyleClass().add("search-input-field");
        searchButton.getStyleClass().add("search-input-button");
        cancelButton.getStyleClass().add("cancel-input-button");
    }

    private void listenToFilterChange() {
        messageController.getFilterStatusProperty()
                .addListener((observable, oldValue, newValue) ->
                        updateUIState(Boolean.TRUE.equals(newValue)
                                ? InputState.SEARCHING : InputState.MESSAGE_INPUT));
    }

    private void updateUIState(final InputState inputState) {
        switch (inputState) {
            case SEARCHING:
                comboBox.setDisable(true);
                inputBox.getChildren().remove(searchButton);
                inputBox.getChildren().add(cancelButton);
                break;
            case MESSAGE_INPUT:
                comboBox.setDisable(false);
                inputBox.getChildren().remove(cancelButton);
                inputBox.getChildren().add(searchButton);
                break;
            default:
                throw new IllegalArgumentException("Unknown state");
        }
    }

    private void initializeButtonActions() {
        searchButton.setOnAction(e -> performSearch());
        cancelButton.setOnAction(e -> performUndoSearch());

        searchField.setOnKeyPressed(this::onEnterKeyPressed);
    }

    private void onEnterKeyPressed(final KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            if (Boolean.TRUE.equals(messageController.getFilterStatusProperty().getValue())) {
                performUndoSearch();
                Logger.getGlobal().info("Undo search");
            } else {
                performSearch();
                Logger.getGlobal().info("Search");
            }
        }
    }

    private void initializeSearchInputs(final MessageController messageController) {
        searchField.setPromptText("Rechercher un message");
        comboBox.setItems(messageController.getResearchStrategies());
        comboBox.getSelectionModel().selectFirst();
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

    private void performSearch() {
        if (comboBox.getValue() != null && searchField.getText() != null) {
            comboBox.getValue().setSearchQuery(searchField.getText());
            messageController.applySearch(searchField.getText(), comboBox.getValue());
        }
    }

    private void performUndoSearch() {
        if (comboBox.getValue() != null) {
            messageController.undoSearch(comboBox.getValue());
        }
    }

    private enum InputState {
        SEARCHING, MESSAGE_INPUT
    }
}
