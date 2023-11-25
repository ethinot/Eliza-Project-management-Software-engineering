package fr.univ_lyon1.info.m1.elizagpt.controller;

import fr.univ_lyon1.info.m1.elizagpt.model.Model;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.ElizaMessage;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.MessageRepository;
import fr.univ_lyon1.info.m1.elizagpt.model.researches.research_types.RegexpResearch;
import fr.univ_lyon1.info.m1.elizagpt.model.researches.research_types.Research;
import fr.univ_lyon1.info.m1.elizagpt.model.researches.ResearchRepository;
import fr.univ_lyon1.info.m1.elizagpt.model.researches.research_types.SubstringResearch;
import javafx.collections.ObservableList;

import java.io.IOException;

/**
 * This class is responsible for sending messages to the model.
 */
public class MessageController {
    private final Model model = new Model();

    /**
     * Constructor of the MessageController class.
     *
     * @throws IOException if
     */
    public MessageController() throws IOException {
        addDefaultMessages();
        addResearchMethod();
    }

    private void addDefaultMessages() {
        model.getMessageRepository().sendMessage(new ElizaMessage(
                "Bonjour, je suis Eliza, votre thérapeute virtuelle."));
        model.getMessageRepository().sendMessage(new ElizaMessage("Comment allez-vous ?"));
    }

    private void addResearchMethod() {
        model.getResearchRepository().addResearchMethod(new
                SubstringResearch(null, model.getMessageRepository()));
        model.getResearchRepository().addResearchMethod(new
                RegexpResearch(null, model.getMessageRepository()));
    }

    public ObservableList<Message> getMessagesObservableList() {
        return MessageRepository.getObservableList();
    }

    public ObservableList<Research> getResearchObservableList() {
        return ResearchRepository.getResearchMethods();
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
     * @param researchClass the research class implementation
     */
    public void search(final String searchedString, final Research researchClass) {
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
    public void undoSearch(final Research researchClass) {
        model.getResearchRepository().undoSearch(
                researchClass,
                model.getMessageRepository());
    }
}
