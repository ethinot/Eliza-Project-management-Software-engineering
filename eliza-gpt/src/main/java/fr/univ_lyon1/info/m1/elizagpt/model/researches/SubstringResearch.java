package fr.univ_lyon1.info.m1.elizagpt.model.researches;

import fr.univ_lyon1.info.m1.elizagpt.model.messages.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.MessageRepository;

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
    public SearchType getSearchType() {
        return SearchType.SUBSTRING;
    }

    @Override
    public void search() {
        initMessageRepositoryResult();

        for (Message message : getMessageRepositoryBase()) {
            if (message.getText().contains(this.getSearchedString())) {
                getMessageRepositoryResult().add(message);
            }
        }
        getMessageRepository().clear();
        getMessageRepository().addACollectionOfMessages(getMessageRepositoryResult());
    }
}
