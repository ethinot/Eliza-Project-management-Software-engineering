package fr.univ_lyon1.info.m1.elizagpt.model.research;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.message.MessageRepository;
import fr.univ_lyon1.info.m1.elizagpt.model.research.research_strategies.ResearchStrategyType;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Responsible for storing research methods.
 */
public class ResearchStrategyRepository {

    /**
     * The list of research methods.
     *
     * @see ResearchStrategy
     */
    private final ObservableList<ResearchStrategy> researchMethods =
            FXCollections.observableArrayList();
    private final SimpleBooleanProperty isFilterActive = new SimpleBooleanProperty(false);

    /**
     * Constructor of the research repository.
     * It passes the reference of the message repository to the research methods.
     *
     * @param messageRepository a reference to the message repository
     */
    public ResearchStrategyRepository(final MessageRepository messageRepository) {
        addResearchMethod(messageRepository, ResearchStrategyType.SUBSTRING);
        addResearchMethod(messageRepository, ResearchStrategyType.REGEXP);
        addResearchMethod(messageRepository, ResearchStrategyType.WORD);
    }

    private void addResearchMethod(final MessageRepository messageRepository,
                                   final ResearchStrategyType researchType) {
        ResearchStrategy researchStrategy = ResearchStrategyFactory
                .createResearch(researchType, messageRepository);
        researchMethods.add(researchStrategy);
    }

    public ObservableList<ResearchStrategy> getResearchMethods() {
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
                            final ResearchStrategy researchClass,
                            final MessageRepository messageRepository) {
        if (Boolean.FALSE.equals(getIsFilterStatus())) {
            List<Message> foundMessages = researchClass.search(searchedString);
            setIsFilterStatus(true);
            messageRepository.clear();
            messageRepository.addMessages(foundMessages);
        }
    }

    /**
     * Undo a precedent search filter.
     */
    public void undoSearch(final ResearchStrategy researchClass,
                           final MessageRepository messageRepository) {
        messageRepository.clear();
        messageRepository.addMessages(researchClass.getOriginalMessages());
        setIsFilterStatus(false);
    }
}
