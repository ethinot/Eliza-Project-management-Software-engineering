package fr.univ_lyon1.info.m1.elizagpt.model.grammar.verb;

import fr.univ_lyon1.info.m1.elizagpt.model.utils.XmlLoader;
import fr.univ_lyon1.info.m1.elizagpt.model.utils.XmlMapper;

import java.util.List;

/**
 * Class that contain a list of verbs used by Eliza's response.
 */
public class VerbsRepository {
    private final List<Verb> verbs;

    /**
     * List of 3rd group verbs and their correspondance from 1st person singular
     * (Je) to 2nd person plural (Vous).
     * Verbs are load from XML file
     */
    public VerbsRepository(final String xmlFilePath) {
        this.verbs = XmlLoader.load(xmlFilePath, "verb", XmlMapper::mapElementToVerb);
    }

    public List<Verb> getVerbs() {
        return verbs;
    }
}
