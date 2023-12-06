package fr.univ_lyon1.info.m1.elizagpt.model;

import fr.univ_lyon1.info.m1.elizagpt.model.message.message_types.ElizaMessage;
import fr.univ_lyon1.info.m1.elizagpt.model.message.MessageRepository;
import fr.univ_lyon1.info.m1.elizagpt.model.message.message_types.UserMessage;
import fr.univ_lyon1.info.m1.elizagpt.model.research.ResearchStrategy;
import fr.univ_lyon1.info.m1.elizagpt.model.research.ResearchStrategyFactory;
import fr.univ_lyon1.info.m1.elizagpt.model.research.research_strategies.ResearchStrategyType;
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
    void testRegexpResearch() {
        ResearchStrategy regexpResearch = ResearchStrategyFactory
                .createResearch(ResearchStrategyType.REGEXP,
                messageRepository);

        regexpResearch.search("Quel\\s+temps");

        assertEquals("Quel temps fait-il aujourd'hui?",
                regexpResearch.getSearchResult().get(0).getText());
        assertEquals(1, regexpResearch.getSearchResult().size());

        regexpResearch.getOriginalMessages();
        assertEquals(4, messageRepository.getAllMessages().size());
    }

    /**
     * Testing the substring research.
     */
    @Test
    void testSubstringResearch() {
        ResearchStrategy substringResearch = ResearchStrategyFactory
                .createResearch(ResearchStrategyType.SUBSTRING,
                messageRepository);

        substringResearch.search("j'ai ENVIE de creVer");

        assertEquals("Il ne fait pas beau, j'ai envie de crever!",
                substringResearch.getSearchResult().get(0).getText());

        substringResearch.getOriginalMessages();
        assertEquals(4, messageRepository.getAllMessages().size());

        substringResearch.search("je ne match avec personne (snif)");
        assertEquals(0, substringResearch.getSearchResult().size());
    }

    /**
     * Testing the word research.
     */
    @Test
    void testWordResearch() {
        ResearchStrategy wordResearch = ResearchStrategyFactory
                .createResearch(ResearchStrategyType.WORD,
                messageRepository);

        wordResearch.search("envie");

        assertEquals("Il ne fait pas beau, j'ai envie de crever!",
                wordResearch.getSearchResult().get(0).getText());
        assertEquals(1, wordResearch.getSearchResult().size());

        assertEquals(4, messageRepository.getAllMessages().size());

        wordResearch.search("envi");
        assertEquals(0, wordResearch.getSearchResult().size());
    }
}
