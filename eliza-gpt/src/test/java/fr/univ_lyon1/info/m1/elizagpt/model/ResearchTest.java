package fr.univ_lyon1.info.m1.elizagpt.model;

import fr.univ_lyon1.info.m1.elizagpt.model.messages.ElizaMessage;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.MessageRepository;
import fr.univ_lyon1.info.m1.elizagpt.model.messages.UserMessage;
import fr.univ_lyon1.info.m1.elizagpt.model.researches.research_types.Research;
import fr.univ_lyon1.info.m1.elizagpt.model.researches.research_types.ResearchBuilder;
import fr.univ_lyon1.info.m1.elizagpt.model.researches.research_types.ResearchType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testing the research feature.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ResearchTest {
    private MessageRepository messageRepository;
    private ResearchBuilder researchBuilder;

    /**
     * Set up the testing MessageRepository.
     */
    @BeforeEach
    public void setUp() {
        researchBuilder = new ResearchBuilder();
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
    void testRegexpResearch() {
        Research regexpResearch = researchBuilder
                .setText("Quel\\s+temps")
                .setMessageRepository(messageRepository)
                .createResearch(ResearchType.REGEXP);

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
    void testSubstringResearch() {
        Research substringResearch = researchBuilder
                .setText("j'ai ENVIE de creVer")
                .setMessageRepository(messageRepository)
                .createResearch(ResearchType.SUBSTRING);

        substringResearch.search("j'ai ENVIE de creVer", messageRepository);

        assertEquals("Il ne fait pas beau, j'ai envie de crever!",
                substringResearch.getMessageRepositoryResult().get(0).getText());

        substringResearch.undoSearch();
        assertEquals(4, messageRepository.getAllMessages().size());

        substringResearch.search("je ne match avec personne (snif)", messageRepository);
        assertEquals(0, substringResearch.getMessageRepositoryResult().size());
    }

    /**
     * Testing the word research.
     */
    @Test
    void testWordResearch() {
        Research wordResearch = researchBuilder
                .setText("envie")
                .setMessageRepository(messageRepository)
                .createResearch(ResearchType.WORD);

        wordResearch.search("envie", messageRepository);

        assertEquals("Il ne fait pas beau, j'ai envie de crever!",
                wordResearch.getMessageRepositoryResult().get(0).getText());
        assertEquals(1, wordResearch.getMessageRepositoryResult().size());

        wordResearch.undoSearch();
        assertEquals(4, messageRepository.getAllMessages().size());

        wordResearch.search("envi", messageRepository);
        assertEquals(0, wordResearch.getMessageRepositoryResult().size());
    }
}
