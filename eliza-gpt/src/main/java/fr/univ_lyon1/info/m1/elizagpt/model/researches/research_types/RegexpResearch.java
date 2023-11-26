package fr.univ_lyon1.info.m1.elizagpt.model.researches.research_types;


import fr.univ_lyon1.info.m1.elizagpt.model.messages.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.MessageRepository;

import java.util.List;
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
    public String toString() {
        return "Regexp";
    }
    @Override
    public ResearchType getSearchType() {
        return ResearchType.REGEXP;
    }

    @Override
    public List<Message> search(final String searchedString,
                                final MessageRepository messageRepository) {

        initResult(messageRepository, searchedString);

        Pattern pattern = Pattern.compile(getSearchedString());

        for (Message message : messageRepository.getAllMessages()) {
            Matcher matcher = pattern.matcher(message.getText());

            if (matcher.find()) {
                getMessageRepositoryResult().add(message);
            }
        }
        return getMessageRepositoryResult();
    }
}
