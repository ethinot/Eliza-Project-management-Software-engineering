package fr.univ_lyon1.info.m1.elizagpt.view;

import fr.univ_lyon1.info.m1.elizagpt.model.messages.MessageProcessor;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Main class of the View (GUI) of the application.
 */
public class JfxView {
    static final String BASE_STYLE = "-fx-padding: 8px; "
            + "-fx-margin: 5px; "
            + "-fx-background-radius: 5px;";
    static final String USER_STYLE = "-fx-background-color: #A0E0A0; " + BASE_STYLE;
    static final String ELIZA_STYLE = "-fx-background-color: #A0A0E0; " + BASE_STYLE;
    private final VBox dialog;
    private final Random random = new Random();
    private final MessageProcessor processor = new MessageProcessor();
    private TextField text = null;
    private TextField searchText = null;
    private Label searchTextLabel = null;

    /**
     * Create the main view of the application.
     */
    public JfxView(final Stage stage, final int width, final int height) {
        stage.setTitle("Eliza GPT");

        final VBox root = new VBox(10);

        final Pane search = createSearchWidget();
        root.getChildren().add(search);

        ScrollPane dialogScroll = new ScrollPane();
        dialog = new VBox(10);
        dialogScroll.setContent(dialog);
        // scroll to bottom by default:
        dialogScroll.vvalueProperty().bind(dialog.heightProperty());
        root.getChildren().add(dialogScroll);
        dialogScroll.setFitToWidth(true);

        final Pane input = createInputWidget();
        root.getChildren().add(input);
        replyToUser("Bonjour");

        // Everything's ready: add it to the scene and display it
        final Scene scene = new Scene(root, width, height);
        stage.setScene(scene);
        text.requestFocus();
        stage.show();
    }

    private void replyToUser(final String text) {
        MessageBox messageBox = new MessageBox(text, USER_STYLE, Pos.BASELINE_LEFT, dialog);
        dialog.getChildren().add(messageBox.createMessageContainer());
    }

    private void sendMessage(final String text) {
        MessageBox messageBox = new MessageBox(text, ELIZA_STYLE, Pos.BASELINE_RIGHT, dialog);
        dialog.getChildren().add(messageBox.createMessageContainer());

        String normalizedText = processor.normalize(text);

        Pattern pattern;
        Matcher matcher;

        // First, try to answer specifically to what the user said
        pattern = Pattern.compile(".*Je m'appelle (.*)\\.", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(normalizedText);
        if (matcher.matches()) {
            replyToUser("Bonjour " + matcher.group(1) + ".");
            return;
        }
        pattern = Pattern.compile("Quel est mon nom \\?", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(normalizedText);
        if (matcher.matches()) {
            if (getName() != null) {
                replyToUser("Votre nom est " + getName() + ".");
            } else {
                replyToUser("Je ne connais pas votre nom.");
            }
            return;
        }
        pattern = Pattern.compile("Qui est le plus (.*) \\?", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(normalizedText);
        if (matcher.matches()) {
            replyToUser("Le plus " + matcher.group(1)
                    + " est bien sûr votre enseignant de MIF01 !");
            return;
        }
        pattern = Pattern.compile("(Je .*)\\.", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(normalizedText);
        if (matcher.matches()) {
            final String startQuestion = processor.pickRandom(new String[]{
                    "Pourquoi dites-vous que ",
                    "Pourquoi pensez-vous que ",
                    "Êtes-vous sûr que ",
            });
            replyToUser(startQuestion + processor.firstToSecondPerson(matcher.group(1)) + " ?");
            return;
        }
        // If the sentence finish with an ?
        pattern = Pattern.compile(".*\\?", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(normalizedText);
        if (matcher.matches()) {
            replyToUser("C'est mon terrain, c'est moi qui pose les questions.");
            return;
        }
        // Nothing clever to say, answer randomly
        if (random.nextBoolean()) {
            replyToUser("Il faut beau aujourd'hui, vous ne trouvez pas ?");
            return;
        }
        if (random.nextBoolean()) {
            replyToUser("Je ne comprends pas.");
            return;
        }
        if (random.nextBoolean()) {
            replyToUser("Hmmm, hmm ...");
            return;
        }
        // Default answer
        if (getName() != null) {
            replyToUser("Qu'est-ce qui vous fait dire cela, " + getName() + " ?");
        } else {
            replyToUser("Qu'est-ce qui vous fait dire cela ?");
        }
    }

    /**
     * Extract the name of the user from the dialog.
     * TODO: this totally breaks the MVC pattern, never, ever, EVER do that.
     *
     * @return
     */
    private String getName() {
        for (Node hBox : dialog.getChildren()) {
            for (Node label : ((HBox) hBox).getChildren()) {
                if (label.getStyle().equals("-fx-background-color: #A0E0A0;")) {
                    String text = ((Label) label).getText();
                    Pattern pattern = Pattern.compile("Je m'appelle (.*)\\.",
                            Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(text);
                    if (matcher.matches()) {
                        return matcher.group(1);
                    }
                }
            }
        }
        return null;
    }

    private Pane createSearchWidget() {
        final HBox firstLine = new HBox();
        final HBox secondLine = new HBox();
        firstLine.setAlignment(Pos.BASELINE_LEFT);
        secondLine.setAlignment(Pos.BASELINE_LEFT);
        searchText = new TextField();
        searchText.setOnAction(e -> {
            searchText(searchText);
        });
        firstLine.getChildren().add(searchText);
        final Button send = new Button("Search");
        send.setOnAction(e -> {
            searchText(searchText);
        });
        searchTextLabel = new Label();
        final Button undo = new Button("Undo search");
        undo.setOnAction(e -> {
            throw new UnsupportedOperationException("TODO: implement undo for search");
        });
        secondLine.getChildren().addAll(send, searchTextLabel, undo);
        final VBox input = new VBox();
        input.getChildren().addAll(firstLine, secondLine);
        return input;
    }

    private void searchText(final TextField text) {
        String currentSearchText = text.getText();
        if (currentSearchText == null) {
            searchTextLabel.setText("No active search");
            return;
        } else {
            searchTextLabel.setText("Searching for: " + currentSearchText);
        }
        List<HBox> toDelete = new ArrayList<>();
        for (Node hBox : dialog.getChildren()) {
            for (Node label : ((HBox) hBox).getChildren()) {
                String t = ((Label) label).getText();
                // Use the search text as a regexp:
                Pattern pattern = Pattern.compile(currentSearchText, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(t);
                if (!matcher.find()) {
                    toDelete.add((HBox) hBox);
                }
            }
        }
        dialog.getChildren().removeAll(toDelete);
        text.setText("");
    }

    private Pane createInputWidget() {
        final Pane input = new HBox();
        text = new TextField();
        text.setOnAction(e -> {
            sendMessage(text.getText());
            text.setText("");
        });
        final Button send = new Button("Send");
        send.setOnAction(e -> {
            sendMessage(text.getText());
            text.setText("");
        });
        input.getChildren().addAll(text, send);
        return input;
    }
}
