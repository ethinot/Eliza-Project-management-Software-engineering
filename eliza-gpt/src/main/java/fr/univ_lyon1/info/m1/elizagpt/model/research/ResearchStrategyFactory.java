package fr.univ_lyon1.info.m1.elizagpt.model.research;

import fr.univ_lyon1.info.m1.elizagpt.model.message.MessageRepository;
import fr.univ_lyon1.info.m1.elizagpt.model.research.research_strategies.RegexpResearch;
import fr.univ_lyon1.info.m1.elizagpt.model.research.research_strategies.ResearchStrategyType;
import fr.univ_lyon1.info.m1.elizagpt.model.research.research_strategies.SubstringResearch;
import fr.univ_lyon1.info.m1.elizagpt.model.research.research_strategies.WordResearch;

/**
 * The ResearchFactory class is responsible for creating instances
 * of Research subclasses based on the provided research type.
 */
public final class ResearchStrategyFactory {
    private ResearchStrategyFactory() {
    }

    /**
     * Creates a new Research object based on the given research type.
     *
     * @param type              The type of research to create.
     *                          It should be one of the values defined in the ResearchType enum.
     * @param messageRepository The message repository to be used for creating the research.
     * @return A Research object of the specified type.
     * Returns null if an invalid research type is provided.
     */
    public static ResearchStrategy createResearch(final ResearchStrategyType type,
                                                  final MessageRepository messageRepository) {
        switch (type) {
            case SUBSTRING:
                return new SubstringResearch(messageRepository);
            case REGEXP:
                return new RegexpResearch(messageRepository);
            case WORD:
                return new WordResearch(messageRepository);
            default:
                return null;
        }
    }
}
