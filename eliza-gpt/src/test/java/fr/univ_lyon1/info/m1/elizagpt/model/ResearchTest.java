package fr.univ_lyon1.info.m1.elizagpt.model;

import fr.univ_lyon1.info.m1.elizagpt.model.messages.ElizaMessage;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.MessageRepository;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.UserMessage;
import fr.univ_lyon1.info.m1.elizagpt.model.researches.research_types.RegexpResearch;
import fr.univ_lyon1.info.m1.elizagpt.model.researches.research_types.SubstringResearch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testing the research feature.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ResearchTest {
    private MessageRepository messageRepository;

    /**
     * Set up the testing MessageRepository.
     */
    @BeforeEach
    public void setUp() {
        messageRepository = new MessageRepository();
        messageRepository.clear();
        messageRepository.sendMessage(new ElizaMessage("Bonjour, comment ça va?"));
        messageRepository.sendMessage(new UserMessage("Je vais bien, merci!"));
        messageRepository.sendMessage(new ElizaMessage("Quel temps fait-il aujourd'hui?"));
        messageRepository.sendMessage(new UserMessage(
                "Il ne fait pas beau, j'ai envie de crever!"));
    }

    /**
     * Testing the regexp research.
     */
    @Test
    public void testRegexpResearch() {
        RegexpResearch regexpResearch = new RegexpResearch("Quel\\s+temps", messageRepository);
        regexpResearch.search("Quel\\s+temps", messageRepository);

        assertEquals("Quel temps fait-il aujourd'hui?",
                regexpResearch.getMessageRepositoryResult().get(0).getText());
        assertEquals(1, regexpResearch.getMessageRepositoryResult().size());

        regexpResearch.undoSearch();
        assertEquals(4, messageRepository.getAllMessages().size());
    }

    /**
     * Testing the substring research.
     */
    @Test
    public void testSubstringResearch() {
        SubstringResearch substringResearch = new SubstringResearch("j'ai ENVIE de creVer",
                messageRepository);
        substringResearch.search("j'ai ENVIE de creVer", messageRepository);

        assertEquals("Il ne fait pas beau, j'ai envie de crever!",
                substringResearch.getMessageRepositoryResult().get(0).getText());

        substringResearch.undoSearch();
        assertEquals(4, messageRepository.getAllMessages().size());

        substringResearch.search("je ne match avec personne (snif)", messageRepository);
        assertEquals(0, substringResearch.getMessageRepositoryResult().size());
    }
}
