package fr.univ_lyon1.info.m1.elizagpt.model.research;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.message.MessageRepository;

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
public abstract class ResearchStrategy {
    private final MessageRepository messageRepository;
    // The searching base. A reference to the app messageRepository.
    private String searchedQuery = "";
    /**
     * This variable represents a backup of the message repository.
     * The backup is stored in order to restore the repository to its original state
     * after applying certain research filters.
     */
    private List<Message> originalMessages;
    /**
     * This private variable represents the result of a message repository search.
     */
    private List<Message> searchResult;

    protected ResearchStrategy(final MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
        this.originalMessages = fetchAllMessages();
        this.searchResult = null;
    }

    private List<Message> fetchAllMessages() {
        return getMessageRepository().getAllMessages();
    }

    /**
     * Get the researched text.
     *
     * @return the search text.
     */
    protected String getSearchedQuery() {
        return searchedQuery;
    }

    /**
     * Set the researched text.
     *
     * @param searchedString the searched text.
     */
    public void setSearchQuery(final String searchedString) {
        this.searchedQuery = searchedString;
    }

    private void saveOriginalMessages(final List<Message> messages) {
        originalMessages = messages;
    }

    public List<Message> getSearchResult() {
        return searchResult;
    }

    /**
     * Init the MessageRepositoryResult with an empty ArrayList.
     * Update the backup of messages repository and researched string.
     */
    protected void initSearch(final String searchedString) {
        this.searchResult = new ArrayList<>();
        saveOriginalMessages(fetchAllMessages());
        setSearchQuery(searchedString);
    }

    /**
     * Search method that is implemented in RegexpResearch class and
     * ResearchSubstring class.
     */
    public abstract List<Message> search(String searchedString);

    /**
     * Method that undo the research filter.
     */
    public List<Message> getOriginalMessages() {
        return originalMessages;
    }

    public MessageRepository getMessageRepository() {
        return this.messageRepository;
    }
}
