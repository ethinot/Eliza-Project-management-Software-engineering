package fr.univ_lyon1.info.m1.elizagpt.model.grammar;

import fr.univ_lyon1.info.m1.elizagpt.controller.MessageController;
import fr.univ_lyon1.info.m1.elizagpt.model.grammar.pattern.PatternProcessor;
import fr.univ_lyon1.info.m1.elizagpt.model.grammar.verb.VerbsRepository;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.MessageProcessor;
import fr.univ_lyon1.info.m1.elizagpt.model.utils.RandomUtils;

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

    /**
     * Process the input and generate a response.
     *
     * @param input the input to be processed
     */
    public static void process(final String input) {
        // TODO: découper cette fonction
        String normalizedInput = MessageProcessor.normalize(input);

        String response = PatternProcessor.process(normalizedInput);

        if (response != null) {
            replyToUser(response);
            return;
        }

        // Nothing clever to say, answer randomly
        if (RandomUtils.coinToss()) {
            replyToUser("Il faut beau aujourd'hui, vous ne trouvez pas ?");
            return;
        }
        if (RandomUtils.coinToss()) {
            replyToUser("Je ne comprends pas.");
            return;
        }
        if (RandomUtils.coinToss()) {
            replyToUser("Hmmm, hmm ...");
            return;
        }

        // TODO: trouver un meilleur endroit pour stocker le name (ici?)
        String name = PatternProcessor.getName();
        // Default answer
        if (name != null) {
            replyToUser("Qu'est-ce qui vous fait dire cela, " + name + " ?");
        } else {
            replyToUser("Qu'est-ce qui vous fait dire cela ?");
        }
    }

    private static void replyToUser(final String s) {
        MessageController.sendElizaMessage(s);
    }
}
