package fr.univ_lyon1.info.m1.elizagpt.controller;

import fr.univ_lyon1.info.m1.elizagpt.model.Model;
import fr.univ_lyon1.info.m1.elizagpt.model.message.message_types.ElizaMessage;
import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
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
        addDefaultMessages();
    }

    private void addDefaultMessages() {
        model.getMessageRepository().sendMessage(new ElizaMessage(
                "Bonjour, je suis Eliza, votre thérapeute virtuelle."));
        model.getMessageRepository().sendMessage(new ElizaMessage("Comment allez-vous ?"));
    }


    public ObservableList<Message> getMessagesObservableList() {
        return model.getMessageRepository().getObservableList();
    }

    public ObservableList<ResearchStrategy> getResearchObservableList() {
        return model.getResearchRepository().getResearchMethods();
    }

    public SimpleBooleanProperty getIsFilterObservable() {
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
        model.getMessageRepository().removeMessage(message);
    }

    /**
     * Apply search methode.
     *
     * @param searchedString the searched string
     * @param researchClass  the research class implementation
     */
    public void search(final String searchedString, final ResearchStrategy researchClass) {
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
