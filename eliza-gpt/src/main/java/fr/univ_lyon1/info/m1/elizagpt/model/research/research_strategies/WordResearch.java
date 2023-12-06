package fr.univ_lyon1.info.m1.elizagpt.model.research.research_strategies;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.message.MessageRepository;
import fr.univ_lyon1.info.m1.elizagpt.model.research.ResearchStrategy;
import fr.univ_lyon1.info.m1.elizagpt.model.utils.TextUtils;

import java.util.List;

/**
 * A class representing a research methode using full string match.
 * It extends the Research class.
 */
public class WordResearch extends ResearchStrategy {

    /**
     * Construct a FullStringResearch class by using Research one.
     *
     * @param messageRepository the app messageRepository
     */
    public WordResearch(final MessageRepository messageRepository) {
        super(messageRepository);
    }

    @Override
    public String toString() {
        return "Word";
    }

    @Override
    public List<Message> search(final String searchedString) {
        initSearch(searchedString);

        for (Message message : getMessageRepository().getAllMessages()) {
            if (TextUtils.containsWholeWord(this.getSearchedQuery(), message.getText())) {
                getSearchResult().add(message);
            }
        }
        return getSearchResult();
    }
}
