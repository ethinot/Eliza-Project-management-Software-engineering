package fr.univ_lyon1.info.m1.elizagpt.model.researches;

import fr.univ_lyon1.info.m1.elizagpt.model.messages.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.MessageRepository;
import fr.univ_lyon1.info.m1.elizagpt.model.researches.research_types.Research;
import fr.univ_lyon1.info.m1.elizagpt.model.researches.research_types.ResearchBuilder;
import fr.univ_lyon1.info.m1.elizagpt.model.researches.research_types.ResearchType;
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
    private final ObservableList<Research> researchMethods = FXCollections.observableArrayList();
    private final SimpleBooleanProperty isFilterActive = new SimpleBooleanProperty(false);

    /**
     * Constructor of the research repository.
     * It passes the reference of the message repository to the research methods.
     *
     * @param messageRepository a reference to the message repository
     */
    public ResearchRepository(final MessageRepository messageRepository) {
        addResearchMethod(messageRepository, ResearchType.SUBSTRING);
        addResearchMethod(messageRepository, ResearchType.REGEXP);
        addResearchMethod(messageRepository, ResearchType.WORD);
    }

    private void addResearchMethod(final MessageRepository messageRepository,
                                   final ResearchType researchType) {
        Research substring = new ResearchBuilder()
                .setMessageRepository(messageRepository)
                .createResearch(researchType);
        researchMethods.add(substring);
    }

    public ObservableList<Research> getResearchMethods() {
        return researchMethods;
    }

    public SimpleBooleanProperty getFilterStatus() {
        return isFilterActive;
    }

    private Boolean getIsFilterStatus() {
        return isFilterActive.get();
    }

    private void setIsFilterStatus(final Boolean newValue) {
        isFilterActive.set(newValue);
    }

    /**
     * Apply a specific search method on the current message repository.
     *
     * @param searchedString    expression we look up
     * @param researchClass     the implementation of research method
     * @param messageRepository the actual message repository
     */
    public void applySearch(final String searchedString,
                            final Research researchClass,
                            final MessageRepository messageRepository) {
        if (Boolean.FALSE.equals(getIsFilterStatus())) {
            List<Message> foundMessages = researchClass.search(searchedString);
            setIsFilterStatus(true);
            messageRepository.clear();
            messageRepository.addACollectionOfMessages(foundMessages);
        }
    }

    /**
     * Undo a precedent search filter.
     */
    public void undoSearch(final Research researchClass,
                           final MessageRepository messageRepository) {
        List<Message> precedentMessages = researchClass.undoSearch();
        messageRepository.clear();
        messageRepository.addACollectionOfMessages(precedentMessages);
        setIsFilterStatus(false);
    }
}
