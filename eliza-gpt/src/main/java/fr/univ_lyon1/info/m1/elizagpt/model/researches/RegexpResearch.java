package fr.univ_lyon1.info.m1.elizagpt.model.researches;


import fr.univ_lyon1.info.m1.elizagpt.model.messages.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.MessageRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class representing a research methode using regexp.
 * It extends the Research class.
 */
public class RegexpResearch extends Research {

    /**
     * Constructs a new RegexpResearch object with the specified search text.
     * @param searchString the search text
     * @param messageRepository the message repository
     */
    public RegexpResearch(final String searchString, final MessageRepository messageRepository) {
        super(searchString, messageRepository);
    }

    @Override
    public SearchType getSearchType() {
        return SearchType.REGEXP;
    }

    @Override
    public void search() {
        initMessageRepositoryResult();

        Pattern pattern = Pattern.compile(getSearchedString());

        for (Message message : getMessageRepositoryBase()) {
            Matcher matcher = pattern.matcher(message.getText());

            if (matcher.find()) {
                getMessageRepositoryResult().add(message);
            }
        }
        getMessageRepository().clear();
        getMessageRepository().addACollectionOfMessages(getMessageRepositoryResult());
    }
}
