package fr.univ_lyon1.info.m1.elizagpt.model.grammar;

import fr.univ_lyon1.info.m1.elizagpt.model.grammar.verb.VerbsRepository;
import java.io.IOException;

/**
 * Main class for build and store Eliza responses.
 */
public class ElizaResponseProcessor {
    private final VerbsRepository verbsRepository;

    /**
     * Build instances required by the class.
     *
     * @throws IOException exception throw if the file can't open.
     */
    public ElizaResponseProcessor() throws IOException {
        verbsRepository = new VerbsRepository("./verb/vocabulary.xml");
    }
}
