package fr.univ_lyon1.info.m1.elizagpt.model.utils;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.InputStream;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * An XmlLoader is used for loading different types
 * of XML files and create associated list of objet.
 *
 */
public final class XmlLoader {

    private XmlLoader() {

    }

    /**
     * Loads a list of objects from an XML file based on the specified element
     * name and mapping function.
     *
     * @param <T> the type of objects to be loaded
     * @param xmlFilePath the path to the XML file to be loaded
     * @param elementName the name of the XML elements to be processed
     * @param mapper a function that maps XML elements to objects of type T
     * @return list of objects of type T loaded from the XML file
     * @throws NullPointerException if xmlFilePath or elementName is null
     */
    public static <T> List<T> load(final String xmlFilePath, final String elementName,
                                   final Function<Element, T> mapper) {
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
