package fr.univ_lyon1.info.m1.elizagpt.model.grammar.verb;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
    public VerbsRepository(final String xmlFilePath) throws IOException {
        SAXBuilder saxBuilder = new SAXBuilder();
        try {
            InputStream xmlFile = getClass().getClassLoader().getResourceAsStream(xmlFilePath);
            if (xmlFile == null) {
                throw new IOException("Fichier XML non trouve");
            }
            Document document = saxBuilder.build(xmlFile);
            Element rootElement = document.getRootElement();
            List<Element> verbElements = rootElement.getChildren("verb");

            verbs = new ArrayList<>();

            for (Element verbElement : verbElements) {
                String firstSingular = verbElement.getChildText("firstSingular");
                String secondPlural = verbElement.getChildText("secondPlural");
                verbs.add(new Verb(firstSingular, secondPlural));
            }
        } catch (Exception e) {
            throw new IOException("Erreur lors du chargement du fichier XML : " + e.getMessage());
        }
    }

    public List<Verb> getVerbs() {
        return verbs;
    }
}
