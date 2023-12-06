package fr.univ_lyon1.info.m1.elizagpt.model.researches.research_types;

import fr.univ_lyon1.info.m1.elizagpt.model.messages.MessageRepository;

/**
 * The ResearchBuilder class is responsible for creating instances
 * of Research subclasses based on the provided research type.
 */
public class ResearchBuilder {
    private String text;
    private MessageRepository messageRepository;

    /**
     * Sets the text to be used for research.
     *
     * @param text The text to be set for research.
     * @return The ResearchBuilder instance with the updated text.
     */
    public ResearchBuilder setText(final String text) {
        this.text = text;
        return this;
    }

    /**
     * Sets the message repository for the ResearchBuilder instance.
     *
     * @param messageRepository The message repository to be set.
     * @return The ResearchBuilder instance.
     */
    public ResearchBuilder setMessageRepository(final MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
        return this;
    }

    /**
     * Creates a new Research object based on the given research type.
     *
     * @param type The type of research to create.
     *             It should be one of the values defined in the ResearchType enum.
     * @return A Research object of the specified type.
     *         Returns null if an invalid research type is provided.
     */
    public Research createResearch(final ResearchType type) {
        switch (type) {
            case SUBSTRING:
                return new SubstringResearch(text, messageRepository);
            case REGEXP:
                return new RegexpResearch(text, messageRepository);
            case WORD:
                return new WordResearch(text, messageRepository);
            default:
                return null;
        }
    }
}
