package fr.univ_lyon1.info.m1.elizagpt.model;

import fr.univ_lyon1.info.m1.elizagpt.model.grammar.ElizaResponseProcessor;
import fr.univ_lyon1.info.m1.elizagpt.model.message.MessageRepository;
import fr.univ_lyon1.info.m1.elizagpt.model.research.ResearchStrategyRepository;

/**
 * The model main class that create the storage of messages application and
 * the Eliza's response processor.
 */
public class Model {
    private final MessageRepository messageRepository;
    private final ElizaResponseProcessor elizaResponseProcessor;
    private final ResearchStrategyRepository researchRepository;

    /**
     * Constructor that create two instances. One is storing all the application messages,
     * and the other contain all the logique for generating a message.
     */
    public Model() {
        this.messageRepository = new MessageRepository();
        this.elizaResponseProcessor = new ElizaResponseProcessor(messageRepository);
        this.researchRepository = new ResearchStrategyRepository(messageRepository);
    }

    public MessageRepository getMessageRepository() {
        return messageRepository;
    }

    public ElizaResponseProcessor getElizaResponseProcessor() {
        return elizaResponseProcessor;
    }

    public ResearchStrategyRepository getResearchRepository() {
        return researchRepository;
    }
}
