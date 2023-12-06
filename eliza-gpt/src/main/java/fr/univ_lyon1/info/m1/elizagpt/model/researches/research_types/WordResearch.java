package fr.univ_lyon1.info.m1.elizagpt.model.researches.research_types;

import fr.univ_lyon1.info.m1.elizagpt.model.messages.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.MessageRepository;
import fr.univ_lyon1.info.m1.elizagpt.model.utils.TextUtils;

import java.util.List;

/**
 * A class representing a research methode using full string match.
 * It extends the Research class.
 */
public class WordResearch extends Research {

    /**
     * Construct a FullStringResearch class by using Research one.
     *
     * @param searchString the searched word string (user input)
     * @param messageRepository the app messageRepository
     */
    public WordResearch(final String searchString, final MessageRepository messageRepository) {
        super(searchString, messageRepository);
    }

    @Override
    public String toString() {
        return "Word";
    }


    @Override
    public ResearchType getSearchType() {
        return ResearchType.WORD;
    }


    @Override
    public List<Message> search(final String searchedString,
                                final MessageRepository messageRepository) {

        initResult(messageRepository, searchedString);

        for (Message message : messageRepository.getAllMessages()) {
            if (TextUtils.containsWholeWord(this.getSearchedString(), message.getText())) {
                getMessageRepositoryResult().add(message);
            }
        }
        return getMessageRepositoryResult();
    }
}