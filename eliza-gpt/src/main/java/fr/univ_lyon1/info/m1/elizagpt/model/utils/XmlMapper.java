package fr.univ_lyon1.info.m1.elizagpt.model.utils;

import fr.univ_lyon1.info.m1.elizagpt.model.grammar.verb.Verb;
import org.jdom2.Element;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * An XmlMapper that parsing different types of values and create the associated object.
 *
 */
public final class XmlMapper {

    private XmlMapper() {

    }

    /**
     * Function that mapping an element form an XML file to a Verb object.
     *
     * @param element an element from the XML file
     * @return a Verb objet
     */
    public static Verb mapElementToVerb(final Element element) {
        String firstSingular = element.getChildText("firstSingular");
        String secondPlural = element.getChildText("secondPlural");
        return new Verb(firstSingular, secondPlural);
    }

    /**
     * Function that mapping an element form an XML file to a pair of responses.
     *
     * @param element an element from the XML file
     * @return an unmodifiable pair with default and withName responses
     */
    public static Map.Entry<String, String> mapElementToDefaultResponse(final Element element) {
        String defaultResponse = element.getChildText("default");
        String withNameResponse = element.getChildText("withName");
        return new AbstractMap.SimpleEntry<>(defaultResponse, withNameResponse);
    }

    public static Map<String, List<String>> mapElementToProcessorResponses(final Element element) {
        Map<String, List<String>> responses = new HashMap<>();
        for (Element child : element.getChildren()) {
            List<String> childResponses = child.getChildren("response").stream()
                    .map(Element::getText)
                    .collect(Collectors.toList());
            responses.put(child.getName(), childResponses);
        }
        return responses;
    }

}
