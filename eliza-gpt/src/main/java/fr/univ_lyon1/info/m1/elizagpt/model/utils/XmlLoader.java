package fr.univ_lyon1.info.m1.elizagpt.model.utils;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.InputStream;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class XmlLoader {
    public static <T> List<T> load(String xmlFilePath, String elementName, Function<Element, T> mapper) {
        SAXBuilder saxBuilder = new SAXBuilder();
        InputStream xmlFile = XmlLoader.class.getClassLoader().getResourceAsStream(xmlFilePath);
        if (xmlFile == null) {
            Logger.getGlobal().severe("Can't load XML file: " + xmlFilePath);
        }

        Document document = null;
        try {
            document = saxBuilder.build(xmlFile);
        } catch (Exception e) {
            Logger.getGlobal().severe("Can't parse XML file: " + xmlFilePath);
        }

        if (document == null) {
            return List.of();
        }
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.getChildren(elementName);

        return elements.stream().map(mapper).collect(Collectors.toList());
    }
}
