package fr.univ_lyon1.info.m1.elizagpt.model.researches.research_types;

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
    private String searchedString = "";
    // The searching base. A reference to the app messageRepository.

    /**
     * This variable represents a backup of the message repository.
     * <p>
     * The backup is stored in order to restore the repository to its original state
     * after applying certain research filters.
     */
    private List<Message> messageRepositoryBackup;

    /**
     * This private variable represents the result of a message repository search.
     */
    private List<Message> messageRepositoryResult;

    protected Research(final MessageRepository messageRepository) {
        this.messageRepositoryBackup = messageRepository.getAllMessages();
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

    /**
     * Set the researched text.
     *
     * @param searchedString the searched text.
     */
    public void setSearchedString(final String searchedString) {
        this.searchedString = searchedString;
    }

    public void setMessageRepositoryBackup(final List<Message> messages) {
        messageRepositoryBackup = messages;
    }

    public List<Message> getMessageRepositoryResult() {
        return messageRepositoryResult;
    }

    /**
     * Init the MessageRepositoryResult with an empty ArrayList.
     * Update the backup of messages repository and researched string.
     */
    public void initResult(final MessageRepository messageRepository, final String searchedString) {
        this.messageRepositoryResult = new ArrayList<>();
        setMessageRepositoryBackup(messageRepository.getAllMessages());
        setSearchedString(searchedString);
    }

    /**
     * Search method that is implemented in RegexpResearch class and
     * ResearchSubstring class.
     */
    public abstract List<Message> search(String searchedString,
                                         MessageRepository messageRepository);

    /**
     * Method that undo the research filter.
     */
    public List<Message> undoSearch() {
        return messageRepositoryBackup;
    }
}
