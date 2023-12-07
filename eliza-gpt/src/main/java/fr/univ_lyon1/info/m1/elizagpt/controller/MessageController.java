package fr.univ_lyon1.info.m1.elizagpt.controller;

import fr.univ_lyon1.info.m1.elizagpt.model.Model;
import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.message.MessageFactory;
import fr.univ_lyon1.info.m1.elizagpt.model.message.message_types.Author;
import fr.univ_lyon1.info.m1.elizagpt.model.research.ResearchStrategy;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;

/**
 * This class is responsible for sending messages to the model.
 */
public class MessageController {
    private final Model model = new Model();

    /**
     * Constructor of the MessageController class.
     */
    public MessageController() {
        addElizaGreeting();
    }

    private void addElizaGreeting() {
        addElizaMessage("Bonjour, je suis Eliza, votre thérapeute virtuelle.");
        addElizaMessage("Comment allez-vous ?");
    }

    private void addElizaMessage(final String message) {
        model.getMessageRepository().addMessage(MessageFactory.createMessage(
                Author.ELIZA, message));
    }


    public ObservableList<Message> getMessages() {
        return model.getMessageRepository().getMessages();
    }

    public ObservableList<ResearchStrategy> getResearchStrategies() {
        return model.getResearchRepository().getResearchMethods();
    }

    public SimpleBooleanProperty getFilterStatusProperty() {
        return model.getResearchRepository().getFilterStatus();
    }

    /**
     * Sends a message to the model.
     *
     * @param message the message to be sent
     */
    public void sendUserMessage(final String message) {
        model.getElizaResponseProcessor().process(message);
    }

    /**
     * Remove a message to the model.
     *
     * @param message the message to be sent
     */
    public void removeMessage(final Message message) {
        model.getMessageRepository().deleteMessage(message);
    }

    /**
     * Apply search methode.
     *
     * @param searchedString the searched string
     * @param researchClass  the research class implementation
     */
    public void applySearch(final String searchedString, final ResearchStrategy researchClass) {
        model.getResearchRepository().applySearch(
                searchedString,
                researchClass,
                model.getMessageRepository());
    }

    /**
     * Undo search filter.
     *
     * @param researchClass the research class implementation
     */
    public void undoSearch(final ResearchStrategy researchClass) {
        model.getResearchRepository().undoSearch(
                researchClass,
                model.getMessageRepository());
    }
}
