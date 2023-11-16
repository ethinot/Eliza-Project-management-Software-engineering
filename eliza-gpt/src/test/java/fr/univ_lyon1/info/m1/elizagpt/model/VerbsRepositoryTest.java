package fr.univ_lyon1.info.m1.elizagpt.model;

import fr.univ_lyon1.info.m1.elizagpt.model.grammar.verb.Verb;
import fr.univ_lyon1.info.m1.elizagpt.model.grammar.verb.VerbsRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VerbsRepositoryTest {

    @Test
    void testVerbsRepositoryContainsVerbs() {
        try {
            VerbsRepository verbsRepository = new VerbsRepository("vocabulary/vocabulary.xml");

            List<Verb> loadedVerbs = verbsRepository.getVerbs();

            for (Verb verb : loadedVerbs) {
                assertNotNull(verb.getFirstSingular());
                assertNotNull(verb.getSecondPlural());
            }

        } catch (Exception e) {
            e.printStackTrace();
            fail("Une erreur s'est produite lors du chargement des verbes. " + e.getMessage());
        }
    }

    @Test
    void testInvalidVerbsRepositoryCreation() {
        try {
            VerbsRepository invalidVerbsRepository = new VerbsRepository("ce/chemin/ne/mene/pas/a/rome");
        } catch (Exception e) {
            assertNotNull(e);
        }
    }
}
