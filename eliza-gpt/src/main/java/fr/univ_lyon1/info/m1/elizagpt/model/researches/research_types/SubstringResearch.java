package fr.univ_lyon1.info.m1.elizagpt.model.researches.research_types;

import fr.univ_lyon1.info.m1.elizagpt.model.messages.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.MessageRepository;
import fr.univ_lyon1.info.m1.elizagpt.model.utils.TextUtils;

import java.util.List;

/**
 * A class representing a research methode using substring.
 * It extends the Research class.
 */
public class SubstringResearch extends Research {

    /**
     * Construct a SubstringResearch class by using Research one.
     *
     * @param messageRepository the app messageRepository
     */
    public SubstringResearch(final MessageRepository messageRepository) {
        super(messageRepository);
    }

    @Override
    public String toString() {
        return "Substring";
    }

    @Override
    public List<Message> search(final String searchedString) {

        initSearch(searchedString);

        for (Message message : getMessageRepository().getAllMessages()) {
            if (TextUtils.isMatch(this.getSearchedQuery(), message.getText())) {
                getSearchResult().add(message);
            }
        }
        return getSearchResult();
    }
}
