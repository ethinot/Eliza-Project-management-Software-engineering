package fr.univ_lyon1.info.m1.elizagpt.model;

import fr.univ_lyon1.info.m1.elizagpt.model.messages.ElizaMessage;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.MessageRepository;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.UserMessage;
import fr.univ_lyon1.info.m1.elizagpt.model.researches.RegexpResearch;
import fr.univ_lyon1.info.m1.elizagpt.model.researches.SubstringResearch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testing the research feature.
 *
 */
public class ResearchTest {
    private MessageRepository messageRepository;
    /**
     * Set up the testing MessageRepository.
     *
     */
    @BeforeEach
    public void setUp() {
        messageRepository = new MessageRepository();
        messageRepository.sendMessage(new ElizaMessage("Bonjour, comment ça va?"));
        messageRepository.sendMessage(new UserMessage("Je vais bien, merci!"));
        messageRepository.sendMessage(new ElizaMessage("Quel temps fait-il aujourd'hui?"));
        messageRepository.sendMessage(new
                UserMessage("Il ne fait pas bien beau, j'ai envie de crever !"));
    }

    /**
     * Testing the regexp research.
     *
     */
    @Test
    public void testRegexpResearch() {
        // Test de la recherche RegExp
        RegexpResearch regexpResearch = new RegexpResearch("Quel\\s+temps", messageRepository);
        regexpResearch.search("Quel\\s+temps", messageRepository);

        // Vérifiez que le contenu du message correspond aux attentes
        assertEquals("Quel temps fait-il aujourd'hui?",
                regexpResearch.getMessageRepositoryResult().get(0).getText());
        // Le résultat devrait contenir deux messages correspondant au motif RegExp
        assertEquals(1, regexpResearch.getMessageRepositoryResult().size());
        regexpResearch.undoSearch();
        assertEquals(4, messageRepository.getAllMessages().size());

        SubstringResearch substringResearch = new SubstringResearch("j'ai envie de crever",
                messageRepository);
        substringResearch.search("j'ai envie de crever", messageRepository);
        assertEquals(1, substringResearch.getMessageRepositoryResult().size());
    }
}
