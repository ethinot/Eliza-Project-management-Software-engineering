package fr.univ_lyon1.info.m1.elizagpt.model.research.research_strategies;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.message.MessageRepository;
import fr.univ_lyon1.info.m1.elizagpt.model.research.ResearchStrategy;

import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * A class representing a research methode using regexp.
 * It extends the Research class.
 */
public class RegexpResearch extends ResearchStrategy {

    /**
     * Constructs a new RegexpResearch object with the specified search text.
     *
     * @param messageRepository the message repository
     */
    public RegexpResearch(final MessageRepository messageRepository) {
        super(messageRepository);
    }

    @Override
    public String toString() {
        return "Regexp";
    }

    @Override
    public List<Message> search(final String searchedString) {
        initSearch(searchedString);

        try {
            Pattern pattern = Pattern.compile(getSearchedQuery());
            for (Message message : getMessageRepository().getAllMessages()) {
                Matcher matcher = pattern.matcher(message.getText());

                if (matcher.find()) {
                    getSearchResult().add(message);
                }
            }
        } catch (PatternSyntaxException e) {
            Logger.getLogger(RegexpResearch.class.getName())
                    .severe("Erreur dans le regexp : " + e.getMessage());
        }
        return getSearchResult();
    }
}
