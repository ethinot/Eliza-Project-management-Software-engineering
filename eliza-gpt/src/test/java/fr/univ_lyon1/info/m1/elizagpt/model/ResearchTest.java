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
        // Initialisez la base de messages avec quelques messages de test
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
        regexpResearch.search();

        // Vérifiez que le contenu du message correspond aux attentes
        assertEquals("Quel temps fait-il aujourd'hui?",
                regexpResearch.getMessageRepositoryResult().get(0).getText());
        // Le résultat devrait contenir deux messages correspondant au motif RegExp
        assertEquals(1, regexpResearch.getMessageRepositoryResult().size());

    }

    /**
     * Testing the substring research.
     *
     */
    @Test
    public void testSubstringResearch() {
        // Test de la recherche par sous-chaîne
        SubstringResearch substringResearch = new SubstringResearch("bien", messageRepository);
        substringResearch.search();

        // Le résultat devrait contenir deux messages contenant la sous-chaîne "bien"
        assertEquals(2, substringResearch.getMessageRepositoryResult().size());
    }
}
