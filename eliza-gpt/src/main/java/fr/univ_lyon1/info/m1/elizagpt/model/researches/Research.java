package fr.univ_lyon1.info.m1.elizagpt.model.researches;

import fr.univ_lyon1.info.m1.elizagpt.model.messages.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.MessageRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * The Research class represent a research method object.
 * It is an abstract class, so it cannot be instantiated.
 * The class is responsible for storing the researched input and
 * the type of research (regexp/substring).
 * The type is an enum, so it can only be SUBSTRING or REGEXP.
 * This class is extended by the RegexpResearch and SubstringResearch classes.
 */
public abstract class Research {
    private String searchedString;

    private final MessageRepository messageRepository;

    // The searching base. A reference to the app messageRepository.
    private final List<Message> messageRepositoryBase;

    // The result of the research.
    private List<Message> messageRepositoryResult;

    private SearchType searchType;

    protected Research(final String text, final MessageRepository messageRepository) {
        this.searchedString = text;
        this.messageRepository = messageRepository;
        this.messageRepositoryBase = messageRepository.getAllMessages();
        this.messageRepositoryResult = null;
    }

    /**
     * Get the researched text.
     *
     * @return the search text.
     */
    public String getSearchedString() {
        return searchedString;
    }

    public MessageRepository getMessageRepository() {
        return messageRepository;
    }

    public List<Message> getMessageRepositoryBase() {
        return messageRepositoryBase;
    }

    public List<Message> getMessageRepositoryResult() {
        return messageRepositoryResult;
    }

    /**
     * Init the MessageRepositoryResult with an empty ArrayList.
     *
     */
    public void initMessageRepositoryResult() {
        this.messageRepositoryResult = new ArrayList<>();
    }

    /**
     * Get the search type method.
     *
     * @return  the type of the searching method.
     */
    public abstract SearchType getSearchType();

    /**
     * Search method that is implemented in RegexpResearch class and
     * ResearchSubstring class.
     *
     */
    public abstract void search();

    /**
     * Method that undo the research filter.
     *
     */
    public void undoSearch() {
        this.messageRepository.clear();
        this.messageRepository.addACollectionOfMessages(this.messageRepositoryBase);
    }

    @Override
    public String toString() {
        return "Research[type=" + getSearchType() + ", " +  getSearchedString() + "]";
    }
}
