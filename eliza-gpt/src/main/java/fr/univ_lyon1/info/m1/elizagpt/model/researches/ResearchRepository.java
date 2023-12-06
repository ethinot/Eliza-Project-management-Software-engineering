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
    private static final ObservableList<Research> RESEARCH = FXCollections.observableArrayList();
    private static final SimpleBooleanProperty IS_FILTER = new SimpleBooleanProperty(false);

    /**
     * Constructor of the research repository.
     * It passes the reference of the message repository to the research methods.
     *
     * @param messageRepository a reference to the message repository
     */
    public ResearchRepository(final MessageRepository messageRepository) {
        ResearchBuilder researchBuilder = new ResearchBuilder();
        Research substring = researchBuilder
                .setMessageRepository(messageRepository)
                .createResearch(ResearchType.SUBSTRING);

        Research regex = researchBuilder
                .setMessageRepository(messageRepository)
                .createResearch(ResearchType.REGEXP);

        Research word = researchBuilder
                .setMessageRepository(messageRepository)
                .createResearch(ResearchType.WORD);

        RESEARCH.add(substring);
        RESEARCH.add(regex);
        RESEARCH.add(word);
    }

    private Boolean getIsFilterStatus() {
        return IS_FILTER.get();
    }

    private void setIsFilterStatus(final Boolean newValue) {
        IS_FILTER.set(newValue);
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
        if (Boolean.FALSE.equals(getIsFilterStatus())) {
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
        setIsFilterStatus(false);
    }

    public static ObservableList<Research> getResearchMethods() {
        return RESEARCH;
    }
    public static SimpleBooleanProperty getFilterStatus() {
        return IS_FILTER;
    }
}
