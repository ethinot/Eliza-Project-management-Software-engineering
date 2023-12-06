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
     * Creates a new instance of a ResearchStrategy based on the provided ResearchStrategyType.
     *
     * @param type                the type of the research strategy
     * @param messageRepository   the message repository
     * @return the created ResearchStrategy instance
     * @throws IllegalArgumentException if the provided research type is invalid
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
                throw new IllegalArgumentException("Invalid research type");
        }
    }
}
