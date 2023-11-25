package fr.univ_lyon1.info.m1.elizagpt.model.researches.research_types;

import fr.univ_lyon1.info.m1.elizagpt.model.messages.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.MessageRepository;

import java.util.List;

/**
 * A class representing a research methode using substring.
 * It extends the Research class.
 */
public class SubstringResearch extends Research {

    /**
     * Construct a SubstringResearch class by using Research one.
     *
     * @param searchString the searched string (user input)
     * @param messageRepository the app messageRepository
     */
    public SubstringResearch(final String searchString, final MessageRepository messageRepository) {
        super(searchString, messageRepository);
    }

    @Override
    public String toString() {
        return "Substring";
    }
    @Override
    public ResearchType getSearchType() {
        return ResearchType.SUBSTRING;
    }

    @Override
    public List<Message> search(final String searchedString,
                                final MessageRepository messageRepository) {
        initMessageRepositoryResult();
        setMessageRepositoryBackup(messageRepository.getAllMessages());
        setSearchedString(searchedString);

        for (Message message : messageRepository.getAllMessages()) {
            if (message.getText().contains(this.getSearchedString())) {
                getMessageRepositoryResult().add(message);
            }
        }
        return getMessageRepositoryResult();
    }
}
