package fr.univ_lyon1.info.m1.elizagpt.model.utils;

import fr.univ_lyon1.info.m1.elizagpt.model.grammar.verb.Verb;
import org.jdom2.Element;

import java.util.AbstractMap;
import java.util.Map;

public class XmlMapper {
    public static Verb mapElementToVerb(Element element) {
        String firstSingular = element.getChildText("firstSingular");
        String secondPlural = element.getChildText("secondPlural");
        return new Verb(firstSingular, secondPlural);
    }

    public static Map.Entry<String, String> mapElementToResponse(Element element) {
        String defaultResponse = element.getChildText("default");
        String withNameResponse = element.getChildText("withName");
        return new AbstractMap.SimpleEntry<>(defaultResponse, withNameResponse);
    }
}
