package fr.univ_lyon1.info.m1.elizagpt.model.researches;

import fr.univ_lyon1.info.m1.elizagpt.model.messages.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.MessageRepository;
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
    private final SubstringResearch substringResearch;
    private final RegexpResearch regexpResearch;
    private Boolean isFilter;

    /**
     * Constructor of the research repository.
     * It passes the reference of the message repository to the research methods.
     *
     * @param messageRepository a reference to the message repository
     */
    public ResearchRepository(final MessageRepository messageRepository) {
        this.substringResearch = new SubstringResearch(null, messageRepository);
        this.regexpResearch = new RegexpResearch(null, messageRepository);
        isFilter = false;
    }

    private Boolean getFilterStatus() {
        return isFilter;
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
        if (isFilter == false) {
            List<Message> foundMessages = researchClass.search(searchedString, messageRepository);
            isFilter = true;
            if (!foundMessages.isEmpty()) {
                messageRepository.clear();
                messageRepository.addACollectionOfMessages(foundMessages);
            }
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
        isFilter = false;
    }

    public static ObservableList<Research> getResearchMethods() {
        return RESEARCH;
    }
}
