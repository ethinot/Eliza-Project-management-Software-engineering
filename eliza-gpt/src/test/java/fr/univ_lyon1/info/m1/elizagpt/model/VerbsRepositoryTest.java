package fr.univ_lyon1.info.m1.elizagpt.model;

import fr.univ_lyon1.info.m1.elizagpt.model.grammar.verb.VerbsRepository;
import fr.univ_lyon1.info.m1.elizagpt.model.grammar.verb.Verb;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;



class VerbsRepositoryTest {

    @Test
     void testVerbsRepository() {
        try {

            VerbsRepository verbsRepository = new VerbsRepository(Objects.requireNonNull(getClass()
                    .getResource("/vocabulary/vocabulary.xml")).toExternalForm());
            List<Verb> loadedVerbs = verbsRepository.getVerbs();

            assertNotNull(loadedVerbs);
            assertFalse(loadedVerbs.isEmpty());

        } catch (IOException e) {
            e.printStackTrace();
            fail("Une exception s'est produite lors du chargement des verbes. " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
