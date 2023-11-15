package fr.univ_lyon1.info.m1.elizagpt.model;

import fr.univ_lyon1.info.m1.elizagpt.model.grammar.ElizaResponseProcessor;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.MessageRepository;

import java.io.IOException;

public class Model {
    public final MessageRepository messageRepository;
    public final ElizaResponseProcessor elizaResponseProcessor;

    public Model() throws IOException {
        this.messageRepository = new MessageRepository();
        this.elizaResponseProcessor = new ElizaResponseProcessor(messageRepository);
    }
}
