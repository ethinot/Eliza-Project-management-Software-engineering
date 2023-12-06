package fr.univ_lyon1.info.m1.elizagpt.model.researches;

import fr.univ_lyon1.info.m1.elizagpt.model.messages.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.MessageRepository;
import fr.univ_lyon1.info.m1.elizagpt.model.researches.research_types.Research;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Responsible for storing research methods.
 */
public class ResearchRepository {

    /**
     * The list of research methods.
     *
     * @see Research
     */
    private static final ObservableList<Research> RESEARCH = FXCollections.observableArrayList();
    private static SimpleBooleanProperty isFilter;

    /**
     * Constructor of the research repository.
     * It passes the reference of the message repository to the research methods.
     *
     */
    public ResearchRepository() {
        isFilter = new SimpleBooleanProperty(false);
    }

    /**
     * Add research method . The research method is added to the list of research and
     * appropriate actions are taken such as notifying observers and apply specific search methods.
     *
     * @param researchMethod the research method to be added
     */
    public void addResearchMethod(final Research researchMethod) {
        RESEARCH.add(researchMethod);
    }

    private Boolean getIsFilterStatus() {
        return isFilter.get();
    }

    private void setIsFilterStatus(final Boolean newValue) {
        isFilter.set(newValue);
    }

    /**
     * Apply a specific search method on the current message repository.
     *
     * @param searchedString expression we look up
     * @param researchClass the implementation of research method
     * @param messageRepository the actual message repository
     */
    public void applySearch(final String searchedString,
                            final Research researchClass,
                            final MessageRepository messageRepository) {
        if (!getIsFilterStatus()) {
            List<Message> foundMessages = researchClass.search(searchedString, messageRepository);
            setIsFilterStatus(true);
            messageRepository.clear();
            messageRepository.addACollectionOfMessages(foundMessages);
        }
    }

    /**
     * Undo a precedent search filter.
     *
     */
    public void undoSearch(final Research researchClass,
                           final MessageRepository messageRepository) {
        List<Message> precedentMessages = researchClass.undoSearch();
        messageRepository.clear();
        messageRepository.addACollectionOfMessages(precedentMessages);
        isFilter.set(false);
    }

    public static ObservableList<Research> getResearchMethods() {
        return RESEARCH;
    }
    public static SimpleBooleanProperty getFilterStatus() {
        return isFilter;
    }
}
