package fr.univ_lyon1.info.m1.elizagpt.model.utils;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * An XmlLoader is used for loading different types
 * of XML files and create associated list of objet.
 *
 */
public final class XmlLoader {
    private static final Map<String, Document> cache = new ConcurrentHashMap<>();

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

        Document document = cache.get(xmlFilePath);
        if (document == null) {
            InputStream xmlFile = XmlLoader.class.getClassLoader().getResourceAsStream(xmlFilePath);
            if (xmlFile == null) {
                Logger.getGlobal().severe("Can't load XML file: " + xmlFilePath);
                return List.of();
            }

            try {
                document = saxBuilder.build(xmlFile);
                cache.put(xmlFilePath, document); // Ajoute le document au cache
            } catch (Exception e) {
                Logger.getGlobal().severe("Can't parse XML file: " + xmlFilePath + " due to error: " + e.getMessage());
                return List.of();
            }
        }

        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.getChildren(elementName);
        return elements.stream().map(mapper).collect(Collectors.toList());
    }

    /**
     * Loads a single response of type Map<String, List<String>> from an XML file.
     *
     * @param xmlFilePath the path to the XML file to be loaded
     * @param elementName the name of the XML element to be processed
     * @param mapper a function that maps XML elements to a Map<String, List<String>>
     * @return a Map<String, List<String>> loaded from the XML file
     */
    public static Map<String, List<String>> loadSingleResponse(final String xmlFilePath, final String elementName,
                                                               final Function<Element, Map<String, List<String>>> mapper) {
        SAXBuilder saxBuilder = new SAXBuilder();

        Document document = cache.get(xmlFilePath);
        if (document == null) {
            InputStream xmlFile = XmlLoader.class.getClassLoader().getResourceAsStream(xmlFilePath);
            if (xmlFile == null) {
                Logger.getGlobal().severe("Can't load XML file: " + xmlFilePath);
                throw new RuntimeException("Can't load XML file: " + xmlFilePath);
            }

            try {
                document = saxBuilder.build(xmlFile);
                cache.put(xmlFilePath, document); // Ajoute le document au cache
            } catch (Exception e) {
                Logger.getGlobal().severe("Can't parse XML file: " + xmlFilePath + " due to error: " + e.getMessage());
                throw new RuntimeException("Can't parse XML file: " + xmlFilePath, e);
            }
        }

        Element rootElement = document.getRootElement();
        Element specificElement = rootElement.getChild(elementName);
        if (specificElement != null) {
            return mapper.apply(specificElement);
        }

        return new HashMap<>();
    }

}
